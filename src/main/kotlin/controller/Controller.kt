package controller

import lotto.Lotto
import view.InputView
import view.OutputView

class Controller {
    fun run(inputView: InputView, outputView: OutputView) {
        val purchaseAmount = inputView.getPurchaseAmount()
        val lotto = Lotto(purchaseAmount)
        outputView.displayPurchaseAmount(lotto)
    }
}