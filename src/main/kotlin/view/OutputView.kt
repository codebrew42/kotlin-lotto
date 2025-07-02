package view

import lotto.Lotto

class OutputView {
    fun displayPurchaseAmount(lotto: Lotto) {
        println("${lotto.purchaseAmount}")
    }

    companion object {
    }
}