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
}