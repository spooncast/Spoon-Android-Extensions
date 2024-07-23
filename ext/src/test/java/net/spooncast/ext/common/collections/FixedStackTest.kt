package net.spooncast.ext.common.collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FixedStackTest {

    @Test
    fun constructTest() {
        val stack = FixedStack(3, initialValues = listOf(1, 2, 3))

        assertEquals(3, stack[0])
        assertEquals(2, stack[1])
        assertEquals(1, stack[2])
    }

    @Test
    fun `addAndGetTest - no shift`() {
        val stack = FixedStack<Int>(3)

        stack.add(1)
        stack.add(2)
        assertEquals(2, stack[0])
        assertEquals(1, stack[1])

        stack.add(3)
        assertEquals(3, stack[0])
        assertEquals(2, stack[1])
        assertEquals(1, stack[2])

        stack.add(4)
        assertEquals(3, stack[0])
        assertEquals(2, stack[1])
        assertEquals(1, stack[2])
    }

    @Test
    fun `addAndGetTest - shift`() {
        val stack = FixedStack<Int>(3, shiftWhenOverflow = true)

        stack.add(1)
        stack.add(2)
        assertEquals(2, stack[0])
        assertEquals(1, stack[1])

        stack.add(3)
        assertEquals(3, stack[0])
        assertEquals(2, stack[1])
        assertEquals(1, stack[2])

        stack.add(4)
        assertEquals(4, stack[0])
        assertEquals(3, stack[1])
        assertEquals(2, stack[2])
    }

    @Test
    fun takeTest() {
        val stack = FixedStack(3, initialValues = listOf(1, 2, 3))

        assertEquals(listOf(3), stack.take(1))
        assertEquals(listOf(3, 2), stack.take(2))
        assertEquals(listOf(3, 2, 1), stack.take(3))
    }
}