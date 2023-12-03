package ru.nelshin.learnenglishwordsapp

data class ScoreAnswer(
    var correctAnswerQuestions: Int = 0,
    var wrongAnswerQuestions: Int = 0,
    var skipAnswerQuestions: Int = 0
){
    fun getScore(): Int{
        return correctAnswerQuestions + wrongAnswerQuestions + skipAnswerQuestions
    }
}
