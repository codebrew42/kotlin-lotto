package model

object Statistics {
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

    fun calculateWinningAmount(matchResult: List<Int>): Int {
        return Rank.entries.withIndex().sumOf { (index, rank) ->
            matchResult.getOrElse(index) { 0 } * rank.winningMoney
        }
    }

    fun calculateWinningStatistic(purchaseAmount: Int, matchResult: List<Int>): Float {
        val winningAmount = calculateWinningAmount(matchResult)
        return (winningAmount.toFloat() / purchaseAmount.toFloat())
    }
}
