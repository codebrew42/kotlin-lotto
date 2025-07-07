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
        val lotto = setNumberOfEachTicket(inputView, outputView)
        //    lotto = generateManualTickets()
        //    lotto = generateAutomaticTickets()
        outputView.displayTicketsWithoutBrackets(lotto)
        // val winningCombination = handleWinningCombination(inputView, outputView)
        // handleResultDisplay(lotto, winningCombination, outputView)
    }

    private fun setNumberOfEachTicket(
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
                generateManualTickets(lotto, inputView)
                return lotto
            } catch (e: IllegalArgumentException) {
                println(e.message ?: "Unknown error")
                lastException = e
            }
        }
        throw lastException ?: IllegalArgumentException("Unknown error")
    }

    private fun generateManualTickets(
        lotto: Lotto,
        inputView: InputView,
    ) {
        if (lotto.numberOfManualTickets > 0) {
            println(view.PromptMessages.GET_MANUAL_TICKETS_NUMBERS.message)
            repeat(lotto.numberOfManualTickets) {
                val numbers = inputView.getUserInputAsListOfInt("", 3)
                lotto.tickets.add(model.Ticket(numbers))
            }
        }
    }

    /*

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
