package org.venexer.questionmakerclient.dto

data class QuestionDto (
    val text: String?,
    val resourceLink: String?,
    val answers: HashSet<String> = HashSet(),
    val trueAnswer: String?,
    val groupId: Long?,
    val organizationId: Long?,
    val isPrivate: Boolean = false,
    val isHidden: Boolean = false
)