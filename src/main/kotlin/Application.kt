import controller.Controller
import view.ErrorMessages
import view.InputView
import view.OutputView

fun main() {
    try {
        val controller = Controller()
        controller.run(inputView = InputView(), outputView = OutputView())
    } catch (_: IllegalArgumentException) {
        println(ErrorMessages.PROGRAM_RUNNING_ERROR.message)
    }
}
