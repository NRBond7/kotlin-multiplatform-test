package sample

expect object Platform {
   val name: String
}

fun hello(): String = "Hello from ${sample.Platform.name}"

class Proxy {
    fun proxyHello() = hello()
}

fun main() {
    println(hello())
}

fun transformInput(input: String): String = "$input blah"