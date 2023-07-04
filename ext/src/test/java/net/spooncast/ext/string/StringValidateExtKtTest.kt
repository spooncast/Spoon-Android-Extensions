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
            [THEN : 유효한 IP Address]""")
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
            assertTrue(ipAddress.isValidFormat(ipAddressRegex))
        }

        @ParameterizedTest(name = """
            [GIVEN : IP Address 유효성 확인]
            [WHEN : IP Address : {0}]
            [THEN : 유효하지 않은 IP Address]""")
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
            assertFalse(ipAddress.isValidFormat(ipAddressRegex))
        }
    }

    @DisplayName("isValidWebsocketUrl Test")
    @Nested
    inner class IsValidWebsocketUrlTest {
        @ParameterizedTest
        @ValueSource(
            strings = [
                "ws://example.com",
                "wss://kr-ex.example.com",
                "wss://example.com/123456789",
                "wss://example.com:1234",
                "ws://subdomain.example.com",
                "wss://subdomain.example.com",
                "ws://subdomain.example.com:1234",
                "wss://subdomain.example.com:1234"
            ]
        )
        fun `test valid websocket urls`(url: String) {
            assertTrue(url.isValidFormat(webSocketUrlRegex), "failed validation: $url")
        }

        @ParameterizedTest
        @ValueSource(
            strings = [
                "http://example.com",
                "ws://example.com:",
                "ws://-example.com:",
                "wss://example.com:",
                "ws://example.com:123456789",
                "ws://example."
            ]
        )
        fun `test invalid websocket urls`(url: String) {
            assertFalse(url.isValidFormat(webSocketUrlRegex), "failed validation: $url")
        }
    }
}
