package org.venexer.questionmakerclient.dto.questions

import java.util.*
import kotlin.collections.HashSet

data class QuestionFullInfoDto (
        val id: Long,
        val text: String?,
        val creatorId: Long?,
        val resourceLink: String?,
        val answers: HashSet<String> = HashSet(),
        val isPrivate: Boolean = false,
        val isHidden: Boolean = false,
        val created: Date,
        val updated: Date
)