package jolt

import kotlin.test.Test

class NativesTest {
    @Test
    fun testNatives() {
        val i = TestNatives().addTen(5)
        println("val = $i")
    }
}
