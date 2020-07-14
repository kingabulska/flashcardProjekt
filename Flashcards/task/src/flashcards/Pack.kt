package flashcards

import java.lang.instrument.ClassDefinition

class Pack () {
    private val flashcardPack: MutableList<Flashcard> = mutableListOf()

    fun addCard(term: String, definition: String) {
        val flashcard = Flashcard(term, definition)
        flashcardPack.add(flashcard)
    }
    fun checkIfTermExist(inputTerm: String): Boolean {
        return flashcardPack.firstOrNull { it.term == inputTerm } != null
    }
    fun checkIfDefinitionExist(inputTerm: String): Boolean {
        return flashcardPack.firstOrNull { it.definition == inputTerm } != null
    }

    fun startLearning (n: Int) {
        for (i in 0 until n) {
            val flashcard = flashcardPack[i]

            println("Print the definition of \"${flashcard.term}\":")
            val inputDef = readLine()!!
            val correctDef = flashcard.definition

            if (correctDef == inputDef) {
                println("Correct answer")
            } else {
                if (checkIfDefinitionExist(inputDef)) {
                    val rightTerm = flashcardPack.first{it.definition == inputDef}.term
                    println("Wrong answer. The correct one is \"$correctDef\", you've just written " +
                            "the definition of \"$rightTerm\".")
                } else {
                    println("Wrong answer. The correct one is \"$correctDef\".")
                }
            }

        }
    }
}