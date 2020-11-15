package org.venexer.questionmakerclient.dto.access

data class AccessToQuestionDto(
        val id: Long,
        val targetId: Long,
        val questionId: Long,
        val creatorId: Long? = null
)