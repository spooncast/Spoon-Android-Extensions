package net.spooncast.ext.common.datetime

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TimeTest {
    @Test
    fun isBetweenHoursTest() {
        val time = 1686887614664 // Fri Jun 16 12:53:34 KST 2023
        assertFalse(isBetweenHours(time, 20, 24))
        assertTrue(isBetweenHours(time, 12, 13))
    }
}