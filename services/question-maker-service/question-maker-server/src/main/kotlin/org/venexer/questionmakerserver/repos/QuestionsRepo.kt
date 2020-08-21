package org.venexer.questionmakerserver.repos

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.venexer.questionmakerserver.models.Question
import java.util.*
import kotlin.collections.HashSet

@Repository
interface QuestionsRepo : CrudRepository<Question, Long> {

    fun findByGroupIdAndDeletedTime(groupId: Long, deletedTime: Date? = null): HashSet<Question>

    fun findByCreatorIdAndDeletedTime(creatorId: Long, deletedTime: Date? = null): HashSet<Question>

    fun findQuestionByIdAndCreatorIdAndDeletedTime(id: Long, creatorId: Long, deletedTime: Date? = null): Question?
}