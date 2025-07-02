package lotto

class Lotto(purchaseAmount: Int) {
    init {
        require(purchaseAmount % PURCHASE_AMOUNT_UNIT == 0) { ERROR_INVALID_UNIT }
    }

    companion object {
        const val PURCHASE_AMOUNT_UNIT = 1000
        const val ERROR_INVALID_UNIT = "[ERROR] purchase amount must be divisible by 1000"
    }
}