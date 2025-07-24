package model

class LottoNumber(private val value: Int) {
    init {
        require(value in MIN_VALUE..MAX_VALUE) {
            "Lotto number must be between $MIN_VALUE and $MAX_VALUE"
        }
    }

    fun getValue(): Int = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LottoNumber) return false
        return value == other.value
    }

    override fun hashCode(): Int = value

    override fun toString(): String = value.toString()

    operator fun compareTo(other: LottoNumber): Int = value.compareTo(other.value)

    companion object {
        const val MIN_VALUE = 1
        const val MAX_VALUE = 45

        fun from(value: Int): LottoNumber = LottoNumber(value)
    }
}
