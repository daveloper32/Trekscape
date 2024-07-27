package com.spherixlabs.trekscape.core.data.network.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import javax.inject.Inject
import javax.net.SocketFactory

class NetworkProvider @Inject constructor(
    private val context : Context
) {

    /**
     * Checks if there is a real connection on the available network (WIFI, CELLULAR, ETHERNET, ETC).
     * First, verifying if there are some network capabilities.
     * And second, if theres are some network capabilities, verifying if the network it's actually
     * connected and can make a request or have real access/connection to internet.
     *
     * @return [Boolean] true if there is a connection, false otherwise.
     *
     * Note: It requires the [Manifest.permission.ACCESS_NETWORK_STATE] permission to work.
     * */
    @SuppressLint("MissingPermission")
    suspend fun isConnected(): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager
            .getNetworkCapabilities(network) ?: return false
        val isConnected: Boolean = when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)     -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return if (isConnected) {
            checkInternetConnection(network.socketFactory)
        } else {
            false
        }
    }

    /**
     * Verifies if there is some real connection on the available network (WIFI, CELLULAR, ETHERNET,
     * ETC) making a request to a Google server and wait for a result in less than 1.5 seconds
     *
     * @param socketFactory [SocketFactory] used to create a socket
     * @return [Boolean] true if there is a connection, false otherwise
     * */
    private suspend fun checkInternetConnection(
        socketFactory: SocketFactory
    ): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                val socket: Socket = socketFactory
                    .createSocket() ?: throw IOException("Socket is null")
                socket.connect(
                    InetSocketAddress(
                        SOCKET_HOST_NAME,
                        SOCKET_PORT
                    ),
                    SOCKET_TIMEOUT
                )
                socket.close()
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    companion object {
        const val SOCKET_HOST_NAME = "8.8.8.8"
        const val SOCKET_PORT      = 53
        const val SOCKET_TIMEOUT   = 1500
    }
}