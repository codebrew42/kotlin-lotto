import controller.Controller
import view.InputView
import view.OutputView

fun main() {
    try {
        val controller = Controller()
        controller.run(inputView = InputView(), outputView = OutputView())
    } catch (e: IllegalArgumentException) {
        println("An error occurred. The program will terminate.")
    }
}
