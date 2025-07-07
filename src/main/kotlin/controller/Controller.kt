package controller

import model.Lotto
import model.Statistics
import model.WinningCombination
import view.InputView
import view.OutputView

class Controller {
    fun run(
        inputView: InputView,
        outputView: OutputView,
    ) {
        val lotto = handleLottoPurchase(inputView, outputView)
        val winningCombination = handleWinningCombination(inputView, outputView)
        handleResultDisplay(lotto, winningCombination, outputView)
    }

    private fun handleLottoPurchase(
        inputView: InputView,
        outputView: OutputView,
    ): Lotto {
        val purchaseAmount = inputView.getPurchaseAmount()
        val lotto = Lotto(purchaseAmount)
        outputView.displayPurchaseAmount(lotto)
        outputView.displayNumberOfLottoTickets(lotto)
        lotto.generateTickets()
        outputView.displayTickets(lotto)
        return lotto
    }

    private fun handleWinningCombination(
        inputView: InputView,
        outputView: OutputView,
    ): WinningCombination {
        val winningCombination = inputView.getWinningCombination()
        outputView.displayWinningNumbers(winningCombination.winningNumbers.numbers)
        outputView.displayBonusNumber(winningCombination.bonusNumber)
        return winningCombination
    }

    private fun handleResultDisplay(
        lotto: Lotto,
        winningCombination: WinningCombination,
        outputView: OutputView,
    ) {
        val matchResult = Statistics.calculateMatchResults(lotto, winningCombination)
        outputView.displayMatchResults(matchResult)
        val winningStatistic = Statistics.calculateWinningStatistic(lotto.purchaseAmount, matchResult)
        outputView.displayWinningStatistic(winningStatistic)
    }
}
