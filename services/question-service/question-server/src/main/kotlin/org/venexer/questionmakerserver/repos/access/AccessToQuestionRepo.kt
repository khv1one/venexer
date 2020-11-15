package org.venexer.questionmakerserver.repos.access

import org.springframework.data.repository.CrudRepository
import org.venexer.questionmakerserver.models.access.AccessToQuestion
import java.util.*
import kotlin.collections.HashSet

interface AccessToQuestionRepo<T : AccessToQuestion> : CrudRepository<T, Long> {

    fun findByCreatorId(creatorId: Long, deletedTime: Date? = null): HashSet<T>

    fun findByQuestionId(questionId: Long, deletedTime: Date? = null): HashSet<T>

    fun findByTargetId(targetId: Long, deletedTime: Date? = null): HashSet<T>

    fun findInQuestionId(questionIds: Set<Long>, deletedTime: Date? = null): HashSet<T>

    fun findByCreatorIdAndTargetIdAndQuestionId(
            creatorId: Long,
            targetId: Long,
            questionId: Long,
            deletedTime: Date? = null
    ): T?
}