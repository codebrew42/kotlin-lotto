package controller

import lotto.Lotto
import view.InputView

class Controller {
    fun run(inputView: InputView) {
        val purchaseAmount = inputView.getPurchaseAmount()
        val lotto = Lotto(purchaseAmount)
    }
}