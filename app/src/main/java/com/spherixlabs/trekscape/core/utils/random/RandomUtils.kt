package com.spherixlabs.trekscape.core.utils.random

/**
 * [RandomUtils] is a utility class that provides methods for generating random data.
 * */
object RandomUtils {
    /**
     * Is a list of characters that can be used to generate a random string.
     * It contains both lowercase and uppercase letters and digits.
     * */
    val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    /**
     * It generates a random string of some the desired length based on/using the [charPool].
     *
     * @param stringLength [Int] is the desired length of the random string.
     * @return [String] is the generated random string of the desired input length.
     * */
    fun getRandomString(
        stringLength: Int
    ) = List(stringLength) { charPool.random() }.joinToString("")
}