package flashcards

class Flashcard (val term: String, val definition: String) {

    override fun toString(): String {
        return this.term + " " + this.definition
    }
}