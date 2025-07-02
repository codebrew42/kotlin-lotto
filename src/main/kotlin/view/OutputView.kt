package view

import model.Lotto

class OutputView {
    fun displayPurchaseAmount(lotto: Lotto) {
        println("${lotto.purchaseAmount}")
    }

    fun displayNumberOfLottoTickets(lotto: Lotto) {
        println("You have purchased ${lotto.numberOfTickets} tickets.")
    }

    fun displayTickets(lotto: Lotto) {
        for (i in 0 until lotto.numberOfTickets) {
            println(lotto.tickets[i])
        }
    }

    companion object {
    }
}