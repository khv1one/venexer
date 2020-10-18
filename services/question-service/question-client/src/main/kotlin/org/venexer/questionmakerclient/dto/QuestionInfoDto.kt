package org.venexer.questionmakerclient.dto

data class QuestionInfoDto(
    val id: Long,
    val text: String?,
    val creatorId: Long?,
    val organizationId: Long?,
    val isPrivate: Boolean = false,
    val isHidden: Boolean = false
)