package flashcards

class Flashcard (
        val term: String,
        val definition: String,
        var errors: Int = 0) {



    override fun toString(): String {
        return this.term + "  " + this.definition + "  " + this.errors
    }
}