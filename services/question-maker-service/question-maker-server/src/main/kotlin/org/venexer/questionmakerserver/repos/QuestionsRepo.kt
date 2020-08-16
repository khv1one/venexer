package org.venexer.questionmakerserver.repos

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.venexer.questionmakerserver.models.Question

@Repository
interface QuestionsRepo : CrudRepository<Question, Long> {

    fun findByGroupId(groupId: Long): HashSet<Question>

    fun findByCreatorId(creatorId: Long): HashSet<Question>
}