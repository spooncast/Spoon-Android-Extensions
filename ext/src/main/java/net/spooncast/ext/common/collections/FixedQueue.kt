package net.spooncast.ext.common.collections

import android.util.Log
import java.util.LinkedList

/**
 * @property maxSize Queue의 최대 크기
 * @property shiftWhenOverflow Queue가 가득찬 상태에서 추가 add가 들어올 때 shiftWhenOverflow 여부에 따라 오래된 값을 밀어내고 채워나갈지 결정
 * @param initialValues Queue의 초기 데이터
 */
class FixedQueue<T>(
    private val maxSize: Int,
    private val shiftWhenOverflow: Boolean = false,
    initialValues: List<T> = emptyList()
) : LinkedList<T>() {

    init {
        if (initialValues.isNotEmpty()) {
            super.addAll(initialValues.take(maxSize))
        }
    }

    @Synchronized
    override fun add(element: T): Boolean {
        return when (size < maxSize) {
            true -> {
                super.add(element)
                true
            }

            else -> {
                if (shiftWhenOverflow) {
                    super.removeFirst()
                    super.add(element)
                    true
                } else {
                    Log.w("FixedQueue", "[spoon][FixedQueue] add - failed: size over the maxSize($maxSize)")
                    false
                }
            }
        }
    }

}