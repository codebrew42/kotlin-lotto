package model

class Tickets {
    private val tickets: MutableList<Ticket> = mutableListOf()

    fun add(ticket: Ticket) {
        tickets.add(ticket)
    }

    fun size(): Int = tickets.size

    fun getAll(): List<Ticket> = tickets.toList()

    fun getManualTickets(count: Int): List<Ticket> {
        require(count <= tickets.size) { "Not enough tickets for manual count" }
        return tickets.take(count)
    }

    fun getAutomaticTickets(manualCount: Int): List<Ticket> {
        require(manualCount <= tickets.size) { "Invalid manual count" }
        return tickets.drop(manualCount)
    }

    operator fun get(index: Int): Ticket = tickets[index]

    operator fun iterator(): Iterator<Ticket> = tickets.iterator()

    override fun toString(): String {
        return tickets.joinToString(separator = "\n")
    }
}
