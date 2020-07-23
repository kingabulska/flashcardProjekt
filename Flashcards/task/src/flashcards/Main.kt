package flashcards

fun main(args: Array<String>) {
    val pack = Pack()

    if(args.isNotEmpty() && args[0] == "-import"){
        val fileName = args[1]
        pack.startImport(fileName)
    } else if (args.size > 2 && args[2] == "-import") {
        val fileName = args[3]
        pack.startImport(fileName)
    }

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
    if(args.isNotEmpty() && args[0] == "-export"){
        val fileName = args[1]
        pack.endExport(fileName)
    } else if(args.size > 2 && args[2] == "-export") {
        val fileName = args[3]
        pack.endExport(fileName)
    }
}

