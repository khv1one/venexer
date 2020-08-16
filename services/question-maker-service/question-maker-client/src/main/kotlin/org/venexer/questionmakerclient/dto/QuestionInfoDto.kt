package org.venexer.questionmakerclient.dto

data class QuestionInfoDto(
    val id: Long,
    val creatorId: Long?,
    val forOrganizationId: Long?,
    val isPrivate: Boolean = false,
    val isHidden: Boolean = false
)