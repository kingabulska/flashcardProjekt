package flashcards

import java.io.File
import java.lang.instrument.ClassDefinition
import kotlin.random.Random

class Pack () {
    val flashcardPack: MutableList<Flashcard> = mutableListOf()
    val log: MutableList<String> = mutableListOf()

    val theCard = "The card:"
    val fileNamePrint = "File name:"

    fun addCard() {


        var term: String? = null
        var definition: String? = null


        println(theCard)
        log.add(theCard)
        var input = readLine()!!
        log.add(input)

        if (!this.checkIfTermExist(input)) {
            term = input

            val theDef = "The definition of the card:"
            println(theDef)
            log.add(theDef)
            input = readLine()!!
            log.add(input)
            if (!this.checkIfDefinitionExist(input)) {
                definition = input

                val flashcard = Flashcard(term, definition)
                flashcardPack.add(flashcard)

                val added = "The pair (\"$term\":\"$definition\") has been added."
                println(added)
                log.add(added)

            } else {
                val defExist = "The definition \"$input\" already exists."
                println(defExist)
                log.add(defExist)
            }

        } else {
            val cardExist = "The card \"$input\" already exists."
            println(cardExist)
            log.add(cardExist)
        }
    }

    fun remove() {
        println(theCard)
        log.add(theCard)

        var input = readLine()!!
        log.add(input)

        if (checkIfTermExist(input)) {
            val element = flashcardPack.first {it.term == input}
            flashcardPack.remove(element)

            val removed = "The card has been removed."
            println(removed)
            log.add(removed)
        } else {

            val cantRemove = "Can't remove \"$input\": there is no such card."
            println(cantRemove)
            log.add(cantRemove)
        }
    }

    fun import() {

        println(fileNamePrint)
        val fileName = readLine()!!
        val file = File(fileName)
        log.add(fileName)

        if (file.isFile) {
            val lines = file.readLines()
            val n = lines.size

            for (line in lines) {
                val splitLine = line.split("  ")
                val term = splitLine[0]
                val definition = splitLine[1]
                val errors = splitLine[2].toInt()

                if (!this.checkIfTermExist(term) ) {
                    flashcardPack.add(Flashcard(term, definition, errors))
                } else if (checkIfTermExist(term)) {
                    val element = flashcardPack.first {it.term == term}
                    flashcardPack.remove(element)
                    flashcardPack.add(Flashcard(term, definition, errors))
                }
            }

            val loaded = "$n cards have been loaded."
            println(loaded)
            log.add(loaded)
        } else {
            val fileNotFound = "File not found."
            println(fileNotFound)
            log.add(fileNotFound)
        }

    }
    fun export() {
        println(fileNamePrint)
        log.add(fileNamePrint)

        val fileName = readLine()!!
        val file = File(fileName)
        val size = flashcardPack.size
        file.writeText(this.toString())
        log.add(fileName)

        val savaed = "$size cards have been saved."
        println(savaed)
        log.add(savaed)
    }

    fun checkIfTermExist(inputTerm: String): Boolean {
        return flashcardPack.firstOrNull { it.term == inputTerm } != null
    }
    fun checkIfDefinitionExist(inputTerm: String): Boolean {
        return flashcardPack.firstOrNull { it.definition == inputTerm } != null
    }

    fun ask() {
        val ask = "How many times to ask?"
        println(ask)
        log.add(ask)
        val n = readLine()!!.toInt()
        log.add(n.toString())
        val size = flashcardPack.size


        for (i in 1..n) {
            val index = (0 until size).random()
            val flashcard = flashcardPack[index]
            val term = flashcard.term
            val definition = flashcard.definition
            var errors = flashcard.errors

            val printDef = "Print the definition of \"$term\":"
            println(printDef)
            log.add(printDef)
            val userDef = readLine()!!
            log.add(userDef)

            if (userDef == definition) {
                val corectAnsw = "Correct answer."
                println(corectAnsw)
                log.add(corectAnsw)
            } else {
                if (checkIfDefinitionExist(userDef)) {
                    val rightTerm = flashcardPack.first{it.definition == userDef}.term
                    errors += 1
                    val printRight = "Wrong answer. " +
                            "(The correct one is \"$definition\", you've just written the definition of " +
                            "\"$rightTerm\".)"
                    println(printRight)
                    log.add(printRight)
                } else {
                    errors += 1
                    val correctOne = "Wrong answer. (The correct one is \"$definition\".)"
                    println(correctOne)
                    log.add(correctOne)
                }
                flashcard.errors = errors
            }
        }
    }

    fun harderstCard() {
        val sortedTab =flashcardPack.sortByDescending { flashcard -> flashcard.errors  }
        val hardestTab: MutableList<Int> = mutableListOf()
        val size = flashcardPack.size
        hardestTab.add(0)
        var isEnd = false

        if(flashcardPack.size == 0) {
            val noCards = "There are no cards with errors."
            println(noCards)
            log.add(noCards)

        } else if(flashcardPack[0].errors == 0) {
            val noCards = "There are no cards with errors."
            println(noCards)
            log.add(noCards)
        } else {

            while(!isEnd) {
                if(flashcardPack.size > 1)  {
                    for (i in 0 until size - 1) {
                        if (flashcardPack[i + 1].errors == flashcardPack[i].errors) {
                            hardestTab.add(i + 1)
                        } else {
                            isEnd = true
                            break
                        }
                    }
                    isEnd = true
                } else {
                    isEnd = true
                }

            }

            if(hardestTab.size == 1) {
                val flashcard = flashcardPack[0]
                val term = flashcard.term
                val error = flashcard.errors
                val hardestPrint = "The hardest card is \"$term\". You have $error errors answering it."
                println(hardestPrint)
                log.add(hardestPrint)
            } else  {


                val size = hardestTab.size
                var terms = ""
                for (i in 0 until size - 1) {
                    val term = flashcardPack[i].term
                    terms += "\"$term\", "
                }
                val lastTerm = flashcardPack[size - 1]
                terms += "\"$lastTerm\"."
                val errors = flashcardPack[size - 1].errors

                val hardestPrint = "The hardest cards are $terms You have $errors errors answering them."
                println(hardestPrint)
                log.add(hardestPrint)
            }
        }



        /*for (i in 0 until size - 1) {
            val flashcard1 = flashcardPack[i]
            val flashcard2 = flashcardPack[i + 1]
            if (flashcard2.errors > flashcard1.errors) {
                if (flashcard2.errors > hardest) {
                    hardest = i + 1
                    hardestTab[0] = hardest
                } else if (flashcard2 )


            }*/


    }

    fun resetStats() {
        val reset = "Card statistics has been reset."
        val size = flashcardPack.size

        for (i in 0 until size) {
            val flashcard = flashcardPack[i]
            flashcard.errors = 0
        }

        println(reset)
        log.add(reset)
    }

    fun log() {
        println(fileNamePrint)
        val fileName = readLine()!!
        val file = File(fileName)
        val size = log.size

        for (i in 0 until size) {
            file.appendText(log[i] + "\n")
        }

        println("The Log has been saved.")
    }
    fun startImport(fileName: String) {

        val file = File(fileName)


            val lines = file.readLines()
            val n = lines.size

            for (line in lines) {
                val splitLine = line.split("  ")
                val term = splitLine[0]
                val definition = splitLine[1]
                val errors = splitLine[2].toInt()

                if (!this.checkIfTermExist(term) ) {
                    flashcardPack.add(Flashcard(term, definition, errors))
                } else if (checkIfTermExist(term)) {
                    val element = flashcardPack.first {it.term == term}
                    flashcardPack.remove(element)
                    flashcardPack.add(Flashcard(term, definition, errors))
                }
            }

            val loaded = "$n cards have been loaded."
            println(loaded)
            log.add(loaded)

            /*val fileNotFound = "File not found."
            println(fileNotFound)
            log.add(fileNotFound)*/


    }

    fun endExport(fileName: String) {

        val file = File(fileName)
        val size = flashcardPack.size
        file.writeText(this.toString())

        val savaed = "$size cards have been saved."
        println(savaed)
        log.add(savaed)
    }

    override fun toString(): String {
        return flashcardPack.joinToString(
                separator = "\n"
        )
    }
}
