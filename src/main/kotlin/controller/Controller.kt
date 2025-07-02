package controller

import model.Lotto
import model.Statistics
import view.InputView
import view.OutputView

class Controller {
    fun run(inputView: InputView, outputView: OutputView) {
        val lotto = handleLottoPurchase(inputView, outputView)
        val (winningNumbers, bonusNumber) = handleWinningNumbers(inputView, outputView)
        handleResultDisplay(lotto, winningNumbers, bonusNumber, outputView)
    }

    private fun handleLottoPurchase(inputView: InputView, outputView: OutputView): Lotto {
        val purchaseAmount = inputView.getPurchaseAmount()
        val lotto = Lotto(purchaseAmount)
        outputView.displayPurchaseAmount(lotto)
        outputView.displayNumberOfLottoTickets(lotto)
        lotto.generateTickets()
        outputView.displayTickets(lotto)
        return lotto
    }

    private fun handleWinningNumbers(inputView: InputView, outputView: OutputView): Pair<List<Int>, Int> {
        val winningNumbers = inputView.getWinningNumbers()
        outputView.displayWinningNumbers(winningNumbers)
        val bonusNumber = inputView.getBonusNumber(winningNumbers)
        outputView.displayBonusNumber(bonusNumber)
        return Pair(winningNumbers, bonusNumber)
    }

    private fun handleResultDisplay(
        lotto: Lotto,
        winningNumbers: List<Int>,
        bonusNumber: Int,
        outputView: OutputView
    ) {
        val matchResult = Statistics.calculateMatchResults(lotto, winningNumbers, bonusNumber)
        outputView.displayMatchResults(matchResult)
        val winningStatistic = Statistics.calculateWinningStatistic(lotto.purchaseAmount, matchResult)
        outputView.displayWinningStatistic(winningStatistic)
    }
}