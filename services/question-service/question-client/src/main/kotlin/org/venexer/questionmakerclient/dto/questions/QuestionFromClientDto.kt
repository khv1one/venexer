package org.venexer.questionmakerclient.dto.questions

data class QuestionFromClientDto (
    val text: String?,
    val resourceLink: String?,
    val answers: HashSet<String> = HashSet(),
    val trueAnswer: String?,
    val isPrivate: Boolean = false,
    val isHidden: Boolean = false
)