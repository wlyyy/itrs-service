package org.abomb4.kotlin

/**
 *
 * @author abomb4
 */

class Kt {

    fun main(): Unit = throw KtException()

    fun test() = {
        println("a")

        val art1 = Art("xxx", 100)

        art1.title
    }
}

class KtException(message: String = "", cause: Throwable? = null) : RuntimeException(message, cause)
data class Art(
        val title: String,
        val years: Int
)