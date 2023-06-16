package net.spooncast.ext.common.datetime

import java.util.Calendar

/**
 * 특정 시간이 어떤 시간 대에 속하는지 확인하는 함수
 *
 * @param timeMillis 시간, ms 단위
 * @param startHour 범위 시간 대의 시작 시간
 * @param endHour 범위 시간 대의 종료 시간
 *
 * ex:
 * 현재 시간이 20시 ~ 24시 범위에 속하는지 확인
 * isBetweenHours(startHour = 20, endHour = 24)
 */
fun isBetweenHours(
    timeMillis: Long = System.currentTimeMillis(),
    startHour: Int,
    endHour: Int
): Boolean {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeMillis
    val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
    return hourOfDay in startHour until endHour
}