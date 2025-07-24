package model

enum class Rank(val winningMoney: Int) {
    FIRST(2_000_000_000),
    SECOND(30_000_000),
    THIRD(1_500_000),
    FOURTH(50_000),
    FIFTH(5_000),
    MISS(0),
    ;

    companion object {
        fun valueOfEachTicket(
            ticket: Ticket,
            winningCombination: WinningCombination,
        ): Rank {
            val matchCount = ticket.numbers.count { it in winningCombination.winningNumbers.numbers }
            val bonusMatch = winningCombination.bonusNumber in ticket.getIntNumbers()
            return when {
                matchCount == 6 -> FIRST
                matchCount == 5 && bonusMatch -> SECOND
                matchCount == 5 -> THIRD
                matchCount == 4 -> FOURTH
                matchCount == 3 -> FIFTH
                else -> MISS
            }
        }
    }
}
