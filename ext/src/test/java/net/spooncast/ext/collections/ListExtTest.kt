package net.spooncast.ext.collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ListExtTest {

    @Nested
    @DisplayName("replace() test")
    inner class ReplaceTest {

        private val list = listOf(
            "text 1",
            "text 2",
            "text 3",
            "text 4",
            "text 5",
        )

        @DisplayName("정상 케이스")
        @Test
        fun replaceTest() {
            val expected = listOf(
                "text 1",
                "text 2",
                "text 3",
                "new text",
                "text 5",
            )
            assertEquals(expected, list.replace("new text", 3))
        }

        @DisplayName("유효하지 않은 index 케이스")
        @ParameterizedTest
        @ValueSource(ints = [-1, 5])
        fun `replaceTest - invalid index`(invalidIdx: Int) {
            val expected = listOf(
                "text 1",
                "text 2",
                "text 3",
                "text 4",
                "text 5",
            )
            assertEquals(expected, list.replace("new text", invalidIdx))
        }
    }
}