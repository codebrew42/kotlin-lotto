package view

import lotto.Lotto

class OutputView {
    fun displayPurchaseAmount(lotto: Lotto) {
        println("${lotto.purchaseAmount}")
    }

    fun displayNumberOfLottoTickets(lotto: Lotto) {
        println("You have purchased ${lotto.numberOfTickets} tickets.")
    }

    companion object {
    }
}