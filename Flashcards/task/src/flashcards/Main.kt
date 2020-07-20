package flashcards

fun main() {
    val pack = Pack()

    val printAction = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):"
    println(printAction)
    pack.log.add(printAction)
    var input = readLine()
    pack.log.add(input!!)

    while (input != "exit") {

        when (input) {
            "add" -> pack.addCard()
            "remove" -> pack.remove()
            "import" -> pack.import()
            "export" -> pack.export()
            "ask" -> pack.ask()
            "log" -> pack.log()
            "hardest card" -> pack.harderstCard()
            "reset stats" -> pack.resetStats()
            else -> println("Incorrect commend")
        }

        println(printAction)
        pack.log.add(printAction)
        input = readLine()
        pack.log.add(input!!)
    }
    println("Bye bye!")
}

