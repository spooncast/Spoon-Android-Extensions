package net.spooncast.ext.time

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

const val MINUTE_1 = 60 * 1_000L
const val HOUR_1 = MINUTE_1 * 60
const val DAY_1 = HOUR_1 * 24

const val TIME_FORMAT_HH_MM_SS = "%d:%02d:%02d"
const val TIME_FORMAT_HH_MM_SS_START_ZERO = "%01d:%02d:%02d"

const val TIME_FORMAT_MM_SS = "%d:%02d"
const val TIME_FORMAT_MM_SS_START_ZERO = "%02d:%02d"

/**
 * @sample 2023년 7월 4일
 * @see <a href="https://developer.android.com/reference/android/text/format/DateUtils#formatDateTime(android.content.Context,%20long,%20int)">official document</a>
 */
val yearDateFlags = DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_ABBREV_MONTH or DateUtils.FORMAT_SHOW_DATE

/**
 * @sample 7월 4일
 * @see <a href="https://developer.android.com/reference/android/text/format/DateUtils#formatDateTime(android.content.Context,%20long,%20int)">official document</a>
 */
val dateFlags = DateUtils.FORMAT_ABBREV_MONTH or DateUtils.FORMAT_SHOW_DATE

/**
 * 초 단위 시간을 입력으로 받아 주어진 포맷의 String 객체로 반환하는 함수
 *
 * @param durationSeconds 초 단위 시간 (ms 아님)
 * @param hhmmssForm 입력 받은 시간이 1시간을 넘어갈 경우 사용될 포맷
 * @param mmssForm 입력 받은 시간이 1시간 미만일 경우 사용될 포맷
 */
fun getDurationTimeFormat(
    durationSeconds: Float,
    hhmmssForm: String = TIME_FORMAT_HH_MM_SS,
    mmssForm: String = TIME_FORMAT_MM_SS
): String {
    return if (durationSeconds <= 0) {
        "00:00"
    } else {
        val h = (durationSeconds / 3600).toInt()
        val m = ((durationSeconds - h * 3600) / 60).toInt()
        val s = (durationSeconds - (h * 3600 + m * 60)).toInt()
        if (h > 0) {
            String.format(Locale.ENGLISH, hhmmssForm, h, m, s)
        } else {
            String.format(Locale.ENGLISH, mmssForm, m, s)
        }
    }
}

/**
 * targetTime과의 시간 차이를 반환하는 함수
 *
 * @param targetTime 비교하고자 하는 시간, default는 현재 시간
 * @param timeUnit 반환할 시간 차이의 포맷으로 MS 단위, 초단위, 하루 단위 시간 차이를 반환
 */
fun Long.getDiffTime(
    targetTime: Long = System.currentTimeMillis(),
    timeUnit: TimeUnit = TimeUnit.MILLISECONDS
): Long {
    val diff = targetTime - this
    return when (timeUnit) {
        TimeUnit.SECONDS -> diff / 1_000L
        TimeUnit.DAYS -> diff / DAY_1
        else -> diff
    }
}
