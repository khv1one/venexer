package org.venexer.questionmakerclient.dto.questions

data class QuestionShortInfoDto(
    val id: Long,
    val text: String?,
    val creatorId: Long?,
    val isPrivate: Boolean = false,
    val isHidden: Boolean = false
)