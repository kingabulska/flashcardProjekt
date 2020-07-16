package flashcards

fun main() {
    val pack = Pack()

    println("Input the action (add, remove, import, export, ask, exit):")
    var input = readLine()

    while (input != "exit") {

        when (input) {
            "add" -> pack.addCard()
            "remove" -> pack.remove()
            "import" -> pack.import()
            "export" -> pack.export()
            "ask" -> pack.ask()
            else -> println("Incorrect commend")
        }

        println("Input the action (add, remove, import, export, ask, exit):")
        input = readLine()
    }
    println("Bye bye!")
}

