package net.spooncast.ext.common.collections

import android.util.Log
import java.util.LinkedList

/**
 * @property maxSize Stack의 최대 크기
 * @property shiftWhenOverflow Stack이 가득찬 상태에서 추가 add가 들어올 때 shiftWhenOverflow 여부에 따라 오래된 값을 밀어내고 채워나갈지 결정
 * @param initialValues Stack의 초기 데이터
 */
class FixedStack<T>(
    private val maxSize: Int,
    private val shiftWhenOverflow: Boolean = false,
    initialValues: List<T> = emptyList()
) {

    private val data: LinkedList<T> = LinkedList<T>()

    init {
        if (initialValues.isNotEmpty()) {
            data.addAll(initialValues.take(maxSize).reversed())
        }
    }

    @Synchronized
    fun add(element: T): Boolean {
        return when (data.size < maxSize) {
            true -> {
                data.addFirst(element)
                true
            }

            else -> {
                if (shiftWhenOverflow) {
                    data.removeLast()
                    data.addFirst(element)
                    true
                } else {
                    Log.w("FixedQueue", "[spoon][FixedQueue] add - failed: size over the maxSize($maxSize)")
                    false
                }
            }
        }
    }

    @Synchronized
    operator fun get(index: Int = 0): T? = data[index]

    @Synchronized
    fun take(size: Int): List<T> = data.take(size)
}