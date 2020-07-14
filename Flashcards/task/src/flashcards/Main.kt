package flashcards

fun main() {
    val pack = Pack()

    println("Input the number of cards:")
    val n = readLine()!!.toInt()
    var term: String? = null
    var definition:String? =null

    for (i in 1..n) {
        println("The card #$i:")
        var input = readLine()!!
        while (term == null) {
            if (!pack.checkIfTermExist(input)) {
                term = input

            } else {
                println("The card \"$input\" already exists. Try again:")
                input = readLine()!!

            }
        }


        println("The definition of the card #$i:")
        input = readLine()!!
        while (definition == null) {
            if (!pack.checkIfDefinitionExist(input)) {
                definition = input
            } else {
                println("The definition \"$input\" already exists. Try again:")
                input = readLine()!!
            }
        }
        pack.addCard(term, definition)
        definition = null
        term = null
    }
    pack.startLearning(n)
}

