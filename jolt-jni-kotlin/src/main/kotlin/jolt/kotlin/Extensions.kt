package jolt.kotlin

import jolt.JoltNative

fun <T : JoltNative, R> T.use(block: (T) -> R): R {
    val result = block(this)
    delete()
    return result
}
