package com.spherixlabs.trekscape.core.utils.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import androidx.compose.ui.graphics.toArgb
import com.spherixlabs.trekscape.core.data.network.utils.NetworkProvider
import com.spherixlabs.trekscape.core.presentation.ui.theme.PrimaryColor
import com.spherixlabs.trekscape.core.utils.strings.StringUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

/**
 * [BitmapUtils] is a utility class that provides methods for easily handling bitmap utilities.
 * */
object BitmapUtils {
    /**
     * This function converts an image url to a [Bitmap] object.
     *
     * @param imageUrl [String] The url of the image.
     * @param context [Context] The context of the application.
     * @return [Bitmap] The converted [Bitmap] object.
     * */
    suspend fun fromImageUrlToBitmap(
        imageUrl : String,
        context  : Context,
    ): Bitmap? {
        return try {
            if (!StringUtils.isValidUrl(imageUrl)) {
                return null
            }
            val networkProvider: NetworkProvider = NetworkProvider(context)
            if (!networkProvider.isConnected()) {
                return null
            }
            val url: URL = URL(imageUrl)
            val bitmap: Bitmap = withContext(Dispatchers.IO) {
                val inputStream = url.openStream()
                BitmapFactory
                    .decodeStream(
                        inputStream
                    )
            }?: return null
            val bitmapRounded: Bitmap = cropBitmapToCircleWithBorder(
                bitmap = bitmap,
                borderColor = PrimaryColor.C400.toArgb()
            ) ?: return null
            val bitmapScaled: Bitmap = Bitmap.createScaledBitmap(
                bitmapRounded,
                120,
                120,
                true
            )
            bitmapScaled
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * This function crops a [Bitmap] to a circle.
     *
     * @param bitmap [Bitmap] The bitmap to crop.
     * @return [Bitmap] The cropped bitmap.
     * */
    fun cropBitmapToCircle(
        bitmap : Bitmap,
    ): Bitmap? {
        return try {
            val size: Int = Math.min(bitmap.width, bitmap.height)
            val dtsBitmap: Bitmap = Bitmap.createBitmap(
                size,
                size,
                Bitmap.Config.ARGB_8888
            )
            val canvas: Canvas = Canvas(dtsBitmap)
            val paint: Paint = Paint().apply {
                isAntiAlias = true
                style = Paint.Style.FILL
            }
            val rect: Rect = Rect(
                0,
                0,
                size,
                size
            )
            val rectF: RectF = RectF(rect)
            canvas.drawOval(rectF, paint)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            val left : Float = ((size - bitmap.width) / 2).toFloat()
            val top : Float = ((size - bitmap.height) / 2).toFloat()
            canvas.drawBitmap(
                bitmap,
                left,
                top,
                paint
            )
            bitmap.recycle()
            dtsBitmap
        } catch (e: Exception) {
            null
        }
    }

    /**
     * This function crops a [Bitmap] to a circle.
     *
     * @param bitmap [Bitmap] The bitmap to crop.
     * @param borderWidth [Float] The width of the border.
     * @param borderColor [Int] The color of the border.
     * @return [Bitmap] The cropped bitmap.
     * */
    fun cropBitmapToCircleWithBorder(
        bitmap      : Bitmap,
        borderWidth : Float = 10f,
        borderColor : Int   = Color.BLACK,
    ): Bitmap? {
        return try {
            val size: Int = Math.min(bitmap.width, bitmap.height)
            val dtsBitmap: Bitmap = Bitmap.createBitmap(
                size,
                size,
                Bitmap.Config.ARGB_8888
            )
            val canvas: Canvas = Canvas(dtsBitmap)
            val paint: Paint = Paint().apply {
                isAntiAlias = true
                style = Paint.Style.FILL
            }
            val rect: Rect = Rect(
                0,
                0,
                size,
                size
            )
            val rectF: RectF = RectF(rect)
            canvas.drawOval(rectF, paint)
            val borderPaint = Paint().apply {
                isAntiAlias = true
                style = Paint.Style.STROKE
                strokeWidth = borderWidth
                color = borderColor
            }
            val circleBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
            val circleCanvas = Canvas(circleBitmap)
            val left : Float = ((size - bitmap.width) / 2).toFloat()
            val top : Float = ((size - bitmap.height) / 2).toFloat()
            circleCanvas.drawBitmap(
                bitmap,
                left,
                top,
                null
            )
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(
                circleBitmap,
                0f,
                0f,
                paint
            )
            canvas.drawOval(rectF, borderPaint)
            bitmap.recycle()
            circleBitmap.recycle()
            dtsBitmap
        } catch (e: Exception) {
            null
        }
    }
}