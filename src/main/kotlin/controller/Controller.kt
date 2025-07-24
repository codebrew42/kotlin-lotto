package controller

import model.Lotto
import model.Statistics
import model.Ticket
import model.WinningCombination
import view.ErrorMessages
import view.InputView
import view.OutputView

class Controller {
    fun run(
        inputView: InputView,
        outputView: OutputView,
    ) {
        val lotto = setNumbersOfEachTickets(inputView, outputView)
        fillNumbersToEachTickets(lotto, inputView, outputView)
        outputView.displayLottoTicketsInfo(lotto)
        val winningCombination = handleWinningCombination(inputView, outputView)
        handleResultDisplay(lotto, winningCombination, outputView)
    }

    private fun setNumbersOfEachTickets(
        inputView: InputView,
        outputView: OutputView,
    ): Lotto {
        val purchaseAmount = getPurchaseAmountWithRetry(inputView, outputView)
        val manualCount = getManualTicketCountWithRetry(inputView, outputView)
        return Lotto(purchaseAmount, manualCount)
    }

    private fun getPurchaseAmountWithRetry(
        inputView: InputView,
        outputView: OutputView,
    ): Int {
        val maxAttempts = 3
        repeat(maxAttempts) {
            try {
                val amount = inputView.getPurchaseAmount()
                outputView.displayPurchaseAmount(Lotto(amount, 0))
                return amount
            } catch (e: IllegalArgumentException) {
                outputView.displayError(e.message ?: "Unknown error")
            }
        }
        throw IllegalArgumentException(ErrorMessages.INPUT_TOO_MANY_ATTEMPT.message)
    }

    private fun getManualTicketCountWithRetry(
        inputView: InputView,
        outputView: OutputView,
    ): Int {
        val maxAttempts = 3
        repeat(maxAttempts) {
            try {
                val count = inputView.getNumberOfManualTickets()
                outputView.displaySingleNumber(count)
                return count
            } catch (e: IllegalArgumentException) {
                outputView.displayError(e.message ?: "Unknown error")
            }
        }
        throw IllegalArgumentException("Too many failed attempts. Please restart the program.")
    }

    private fun fillNumbersToEachTickets(
        lotto: Lotto,
        inputView: InputView,
        outputView: OutputView,
    ) {
        generateManualTickets(lotto, inputView, outputView)
        outputView.displayManualTickets(lotto)
        fillAutomaticTickets(lotto)
    }

    private fun generateManualTickets(
        lotto: Lotto,
        inputView: InputView,
        outputView: OutputView,
    ) {
        if (lotto.numberOfManualTickets > 0) {
            outputView.displayManualTicketsPrompt()
            repeat(lotto.numberOfManualTickets) {
                val numbers = inputView.getManualTicketNumbers()
                lotto.tickets.add(Ticket(numbers))
            }
        }
    }

    private fun fillAutomaticTickets(lotto: Lotto) {
        val autoCount = lotto.numberOfAutomaticTickets
        for (i in 0 until autoCount) {
            lotto.tickets.add(lotto.createTicket())
        }
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
