package controller

import model.Lotto
import model.Statistics
import model.Ticket
import model.WinningCombination
import view.InputView
import view.OutputView

class Controller {
    fun run(
        inputView: InputView,
        outputView: OutputView,
    ) {
        val lotto = setNumbersOfEachTickets(inputView, outputView)
        fillNumbersToEachTickets(lotto, inputView, outputView)
        outputView.displayNumberOfLottoTickets(lotto)
        outputView.displayTickets(lotto)

        //    lotto = fillAutomaticTickets()
        // val winningCombination = handleWinningCombination(inputView, outputView)
        // handleResultDisplay(lotto, winningCombination, outputView)
    }

    private fun setNumbersOfEachTickets(
        inputView: InputView,
        outputView: OutputView,
    ): Lotto {
        val maxAttempts = 3
        var lastException: IllegalArgumentException? = null
        for (i in 1..maxAttempts) {
            try {
                val purchaseAmount = inputView.getPurchaseAmount()
                val lotto = Lotto(purchaseAmount)
                outputView.displayPurchaseAmount(lotto)
                val manualCount = inputView.getNumberOfManualTickets()
                lotto.numberOfManualTickets = manualCount
                outputView.displaySingleNumber(lotto.numberOfManualTickets)
                lotto.numberOfAutomaticTickets = lotto.numberOfTotalTickets - manualCount
                return lotto
            } catch (e: IllegalArgumentException) {
                println(e.message ?: "Unknown error")
                lastException = e
            }
        }
        throw lastException ?: IllegalArgumentException("Unknown error")
    }

    private fun fillNumbersToEachTickets(
        lotto: Lotto,
        inputView: InputView,
        outputView: OutputView,
    ) {
        generateManualTickets(lotto, inputView)
        // Display only manual tickets (no brackets)
        if (lotto.numberOfManualTickets > 0) {
            for (i in 0 until lotto.numberOfManualTickets) {
                outputView.displayTicketWithoutBrackets(lotto.tickets[i])
            }
            println()
        }
        fillAutomaticTickets(lotto)
        // Display all tickets (manual + automatic, no brackets)
    }

    private fun generateManualTickets(
        lotto: Lotto,
        inputView: InputView,
    ) {
        if (lotto.numberOfManualTickets > 0) {
            println(view.PromptMessages.GET_MANUAL_TICKETS_NUMBERS.message)
            repeat(lotto.numberOfManualTickets) {
                val numbers = inputView.getUserInputAsListOfInt("", 3)
                lotto.tickets.add(Ticket(numbers))
            }
        }
    }

    private fun fillAutomaticTickets(lotto: Lotto) {
        val autoCount = lotto.numberOfAutomaticTickets
        for (i in 0 until autoCount) {
            lotto.tickets.add(lotto.createTicketForController())
        }
    }

    /*



    private fun handleLottoPurchase(
        inputView: InputView,
        outputView: OutputView,
    ): Lotto {
        val lotto: Lotto = getNumberOfAutomaticTickets(inputView)
        outputView.displayPurchaseAmount(lotto)
        saveManualTickets(lotto, inputView)
        outputView.displayNumberOfLottoTickets(lotto)
        lotto.generateAutomaticTickets()
        outputView.displayTickets(lotto)
        return lotto
    }

    private fun getNumberOfAutomaticTickets(
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

    private fun saveManualTickets(
        lotto: Lotto,
        inputView: InputView
    ) {
        lotto.numberOfManualTickets = inputView.getNumberOfManualTickets()
        fillManualTickets(lotto, inputView)
    }

    private fun fillManualTickets(
        lotto: Lotto,
        inputView: InputView
    ) {
        for (i in 0 until lotto.numberOfManualTickets) {
            val manualTicket = inputView.getManualTicket()
            lotto.tickets.add(i, manualTicket)
        }
    }
*/
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
