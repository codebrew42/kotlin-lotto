package view

import model.Lotto

class OutputView {
    fun displayPurchaseAmount(lotto: Lotto) {
        println("${lotto.purchaseAmount}")
    }

    fun displaySingleNumber(number: Int) {
        println(number)
    }

    fun displayLottoTicketsInfo(lotto: Lotto) {
        println(
            "Purchased ${lotto.numberOfManualTickets} manual and ${lotto.numberOfTotalTickets} automatic tickets.",
        )
        for (ticket in lotto.tickets) {
            println(ticket)
        }
        println()
    }

    fun displayTicketWithoutBrackets(ticket: model.Ticket) {
        println(ticket.numbers.sorted().joinToString(", "))
    }

    fun displayManualTickets(lotto: Lotto) {
        if (lotto.numberOfManualTickets > 0) {
            for (i in 0 until lotto.numberOfManualTickets) {
                displayTicketWithoutBrackets(lotto.tickets[i])
            }
            println()
        }
    }

    fun displayWinningNumbers(winningNumbers: List<Int>) {
        for (winningNumber in winningNumbers) {
            if (winningNumbers.last() != winningNumber) {
                print("$winningNumber, ")
            } else {
                println("$winningNumber")
            }
        }
    }

    fun displayBonusNumber(bonusNumber: Int) {
        println("$bonusNumber\n")
    }

    fun displayMatchResults(results: List<Int>) {
        println(
            """
            Winning Statistics
            ------------------
            3 Matches (5,000 KRW) - ${results[4]} tickets
            4 Matches (50,000 KRW) - ${results[3]}  tickets
            5 Matches (1,500,000 KRW) - ${results[2]}  tickets
            5 Matches + Bonus Ball (30,000,000 KRW) - ${results[1]}  tickets
            6 Matches (2,000,000,000 KRW) - ${results[0]}  tickets
            """.trimIndent(),
        )
    }

    fun displayWinningStatistic(winningStatistic: Float) {
        println("Total return rate is ${"%.2f".format(winningStatistic)} (A rate below 1 means a loss)\n")
    }

    fun displayError(message: String) {
        println(message)
    }

    fun displayManualTicketsPrompt() {
        println(PromptMessages.GET_MANUAL_TICKETS_NUMBERS.message)
    }
}
