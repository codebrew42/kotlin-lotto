package view

import model.Lotto

class OutputView {
    fun displayPurchaseAmount(lotto: Lotto) {
        println("${lotto.purchaseAmount}")
    }

    fun displayNumberOfLottoTickets(lotto: Lotto) {
        println("You have purchased ${lotto.numberOfTickets} tickets.")
    }

    fun displayTickets(lotto: Lotto) {
        for (i in 0 until lotto.numberOfTickets) {
            println(lotto.tickets[i])
        }
        println()
    }

    fun displayWinningNumbers(winningNumbers: List<Int>) {
        for (winningNumber in winningNumbers) {
            if (winningNumbers.last() != winningNumber)
                print("$winningNumber, ")
            else
                println("$winningNumber")
        }
    }

    fun displayBonusNumber(bonusNumber: Int) {
        println("$bonusNumber\n")
    }

    fun displayMatchResults(results: List<Int>) {
        println("Winning Statistics")
        println("------------------")
        println("3 Matches (5,000 KRW) - ${results[4]} tickets")
        println("4 Matches (50,000 KRW) - ${results[3]}  tickets")
        println("5 Matches (1,500,000 KRW) - ${results[2]}  tickets")
        println("5 Matches + Bonus Ball (30,000,000 KRW) - ${results[1]}  tickets")
        println("6 Matches (2,000,000,000 KRW) - ${results[0]}  tickets")
    }

    fun displayWinningStatistic(winningStatistic: Float) {
        println("Total return rate is $winningStatistic (A rate below 1 means a loss)\n")
    }
    companion object {
    }
}