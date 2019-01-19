package com.neobis.israil.infamily.model

class Question {

    var question: String? = null
    private val choice = arrayOfNulls<String>(2)
    var answer: String? = null

    constructor() {

    }

    constructor(question: String, choices: Array<String>, answer: String) {
        this.question = question
        this.choice[0] = choices[0]
        this.choice[1] = choices[1]
        this.answer = answer
    }

    fun getChoice(i: Int): String? {
        return choice[i]
    }

    fun setChoice(i: Int, choice: String) {
        this.choice[i] = choice
    }
}