package org.venexer.questionmakerserver.models

data class AccessToQuestion(

    val id: Long = 0,

    val creatorId: Long,

    val targetUserId: Long
)