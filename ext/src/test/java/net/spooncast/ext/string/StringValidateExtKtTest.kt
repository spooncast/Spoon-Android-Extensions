package net.spooncast.ext.string

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class StringValidateExtKtTest {

    @DisplayName("isValidIpAddress Test")
    @Nested
    inner class IsValidIpAddressTest {
        @ParameterizedTest(name = """
            [GIVEN : IP Address 유효성 확인]
            [WHEN : IP Address : {0}]
            [THEN : 유효한 IP Address]
        """)
        @ValueSource(
            strings = [
                "192.168.1.1",
                "192.168.1.0",
                "0.0.0.0",
                "255.255.255.255",
                "127.0.0.1"
            ]
        )
        fun validTest(ipAddress: String) {
            assertTrue(ipAddress.isValidIpAddress())
        }

        @ParameterizedTest(name = """
            [GIVEN : IP Address 유효성 확인]
            [WHEN : IP Address : {0}]
            [THEN : 유효하지 않은 IP Address]
        """)
        @ValueSource(
            strings = [
                "256.168.1.1",
                "192.168.1.256",
                "192.168.1",
                "",
                "192.168.1.1.",
                "192.168.1.1.1"
            ]
        )
        fun inValidTest(ipAddress: String) {
            assertFalse(ipAddress.isValidIpAddress())
        }
    }
}
