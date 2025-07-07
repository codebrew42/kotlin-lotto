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
        val lotto = createLottoWithRetries(inputView)
        outputView.displayPurchaseAmount(lotto)
        outputView.displayNumberOfLottoTickets(lotto)
        lotto.generateTickets()
        outputView.displayTickets(lotto)
        return lotto
    }

    private fun createLottoWithRetries(
        inputView: InputView,
        maxAttempts: Int = 3,
    ): Lotto {
        var lastException: IllegalArgumentException? = null
        for (i in 1..maxAttempts) {
            try {
                val purchaseAmount = inputView.getPurchaseAmount()
                return Lotto(purchaseAmount)
            } catch (e: IllegalArgumentException) {
                println("${e.message}")
                lastException = e
            }
        }
        throw lastException!!
    }

    private fun handleWinningCombination(
        inputView: InputView,
        outputView: OutputView,
    ): WinningCombination {
        val winningTicket = inputView.getWinningTicket()
        outputView.displayWinningNumbers(winningTicket.numbers)
        val bonusNumber = inputView.getBonusNumber(winningTicket)
        outputView.displayBonusNumber(bonusNumber)
        return WinningCombination(winningTicket, bonusNumber)
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
