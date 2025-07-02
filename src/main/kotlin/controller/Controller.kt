package controller

import model.Lotto
import model.Rank
import view.InputView
import view.OutputView

class Controller {
    fun run(inputView: InputView, outputView: OutputView) {
        val purchaseAmount = inputView.getPurchaseAmount()
        val lotto = Lotto(purchaseAmount)
        outputView.displayPurchaseAmount(lotto)
        outputView.displayNumberOfLottoTickets(lotto)
        lotto.generateTickets()
        outputView.displayTickets(lotto)
        val winningNumbers = inputView.getWinningNumbers()
        outputView.displayWinningNumbers(winningNumbers)
        val bonusNumber = inputView.getBonusNumber(winningNumbers)
        outputView.displayBonusNumber(bonusNumber)
        val matchResult = calculateMatchResults(lotto, winningNumbers, bonusNumber)
        outputView.displayMatchResults(matchResult)
        val winningStatistic = calculateWinningStatistic(purchaseAmount, matchResult)
        outputView.displayWinningStatistic(winningStatistic)
    }

    fun calculateMatchResults(lotto: Lotto, winningNumbers: List<Int>, bonusNumber: Int): List<Int> {
        val matches = MutableList(6) { 0 }
        for (ticket in lotto.tickets) {
            when (Rank.valueOfEachTicket(ticket, winningNumbers, bonusNumber)) {
                Rank.FIRST -> matches[0] += 1
                Rank.SECOND -> matches[1] += 1
                Rank.THIRD -> matches[2] += 1
                Rank.FOURTH -> matches[3] += 1
                Rank.FIFTH -> matches[4] += 1
                Rank.MISS -> matches[5] += 1
            }
        }
        return matches
    }

    fun getWinningAmount(matchResult: List<Int>): Int {
        var totalAmount = 0

        for ((index, rank) in Rank.entries.withIndex()) {
            val count = matchResult.getOrElse(index) { 0 }
            totalAmount += count * rank.winningMoney
        }
        return totalAmount
    }


    fun calculateWinningStatistic(purchaseAmount: Int, matchResult: List<Int>): Float {
        val winningAmount = getWinningAmount(matchResult)
        return (winningAmount/purchaseAmount).toFloat()
    }

}