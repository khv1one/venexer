package org.venexer.questionmakerclient.dto.access

data class AccessToQuestionFromClientDto(
    val targetId: Long,
    val questionId: Long
)