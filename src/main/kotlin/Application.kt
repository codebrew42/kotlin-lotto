import controller.Controller
import view.InputView
import view.OutputView

fun main(){
    val controller = Controller()
    controller.run(inputView = InputView(), outputView = OutputView())
}