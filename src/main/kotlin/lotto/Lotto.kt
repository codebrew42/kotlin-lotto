package lotto

class Lotto(val purchaseAmount: Int) {
    val numberOfTickets: Int
    val tickets = mutableListOf<MutableList<Int>>()

    init {
        require(purchaseAmount % PURCHASE_AMOUNT_UNIT == 0) { ERROR_INVALID_UNIT }
        numberOfTickets = purchaseAmount / PURCHASE_AMOUNT_UNIT
    }

    fun generateTickets() {
        while(tickets.size < numberOfTickets) {
            tickets.add(fillTicket())
        }
    }

    fun fillTicket(): MutableList<Int> {
        val ticket = mutableListOf<Int>()
        while (ticket.size < 6) {
            val number = (1..45).random()
            if (number !in ticket) {
                ticket.add(number)
            }
        }
        ticket.sort()
        return ticket
    }

    companion object {
        const val PURCHASE_AMOUNT_UNIT = 1000
        const val ERROR_INVALID_UNIT = "[ERROR] purchase amount must be divisible by 1000"
    }
}