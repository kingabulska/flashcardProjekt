package flashcards

import java.io.File
import java.lang.instrument.ClassDefinition
import kotlin.random.Random

class Pack () {
    val flashcardPack: MutableList<Flashcard> = mutableListOf()

    fun addCard() {


        var term: String? = null
        var definition: String? = null

        println("The card:")
        var input = readLine()!!

        if (!this.checkIfTermExist(input)) {
            term = input

            println("The definition of the card:")
            input = readLine()!!
            if (!this.checkIfDefinitionExist(input)) {
                definition = input

                val flashcard = Flashcard(term, definition)
                flashcardPack.add(flashcard)

                println("The pair (\"$term\":\"$definition\") has been added.")

            } else {
                println("The definition \"$input\" already exists.")
            }

        } else {
            println("The card \"$input\" already exists.")
        }
    }

    fun remove() {
        println("The card:")
        var input = readLine()!!

        if (checkIfTermExist(input)) {
            val element = flashcardPack.first {it.term == input}
            flashcardPack.remove(element)
            println("The card has been removed.")
        } else {
            println("Can't remove \"$input\": there is no such card.")
        }
    }

    fun import() {
        println("File name:")
        val fileName = readLine()!!
        val file = File(fileName)

        if (file.isFile) {
            val lines = file.readLines()
            val n = lines.size

            for (line in lines) {
                val splitLine = line.split(" ")
                val term = splitLine[0]
                val definition = splitLine[1]

                if (!this.checkIfTermExist(term) ) {
                    flashcardPack.add(Flashcard(term, definition))
                } else if (checkIfTermExist(term)) {
                    val element = flashcardPack.first {it.term == term}
                    flashcardPack.remove(element)
                    flashcardPack.add(Flashcard(term, definition))
                }
            }

            println("$n cards have been loaded.")
        } else {
            println("File not found.")
        }

    }
    fun export() {
        println("File name:")
        val fileName = readLine()!!
        val file = File(fileName)
        val size = flashcardPack.size
        file.writeText(this.toString())
        println("$size cards have been saved.")
    }

    fun checkIfTermExist(inputTerm: String): Boolean {
        return flashcardPack.firstOrNull { it.term == inputTerm } != null
    }
    fun checkIfDefinitionExist(inputTerm: String): Boolean {
        return flashcardPack.firstOrNull { it.definition == inputTerm } != null
    }

    fun ask() {
        println("How many times to ask?")
        val n = readLine()!!.toInt()
        val size = flashcardPack.size


        for (i in 1..n) {
            val index = (0 until size).random()
            val flashcard = flashcardPack[index]
            val term = flashcard.term
            val definition = flashcard.definition
            println("Print the definition of \"$term\":")
            val userDef = readLine()!!

            if (userDef == definition) {
                println("Correct answer.")
            } else {
                if (checkIfDefinitionExist(userDef)) {
                    val rightTerm = flashcardPack.first{it.definition == userDef}.term

                    println("Wrong answer. " +
                            "The correct one is \"$definition\", you've just written the definition of " +
                            "\"$rightTerm\".")
                } else {
                    println("Wrong answer. The correct one is \"$definition\".")
                }
            }
        }
    }

    override fun toString(): String {
        return flashcardPack.joinToString(
                separator = "\n"
        )
    }
}
