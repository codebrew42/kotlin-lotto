package controller

import model.Lotto
import view.InputView
import view.OutputView

class Controller {
    fun run(inputView: InputView, outputView: OutputView) {
        val purchaseAmount = inputView.getPurchaseAmount()
        val lotto = Lotto(purchaseAmount)
        outputView.displayPurchaseAmount(lotto)
        outputView.displayNumberOfLottoTickets(lotto)
        lotto.generateTickets()
        outputView.displayTickets(lotto)
        val winningNumbers = inputView.getWinningNumbers()
        println(winningNumbers)
    }
}