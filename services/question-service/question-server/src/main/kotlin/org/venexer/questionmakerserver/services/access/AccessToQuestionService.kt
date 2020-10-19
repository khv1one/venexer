package org.venexer.questionmakerserver.services.access

import org.springframework.stereotype.Service
import org.venexer.questionmakerserver.models.access.AccessToQuestion
import org.venexer.questionmakerserver.repos.access.AccessToQuestionRepo
import org.venexer.utils.service.ServiceBase

@Service
class AccessToQuestionService<T: AccessToQuestion, R : AccessToQuestionRepo<T>>(
        private val accessRepo: R
) : ServiceBase<T, Long, R>(accessRepo) {

    fun findByQuestionId(questionId: Long): HashSet<T> {
        return accessRepo.findByQuestionId(questionId)
    }

    fun findByQuestionIds(questionIds: Set<Long>): HashSet<T> {
        return accessRepo.findInQuestionId(questionIds)
    }

    fun findByCreatorId(creatorId: Long): HashSet<T> {
        return accessRepo.findByCreatorId(creatorId)
    }

    fun findByTargetId(targetId: Long): HashSet<T> {
        return accessRepo.findByTargetId(targetId)
    }

    fun findByCreatorIdAndTargetIdAndQuestionId(
            creatorId: Long,
            targetId: Long,
            questionId: Long
    ): T? {
        return accessRepo.findByCreatorIdAndTargetIdAndQuestionId(creatorId, targetId, questionId)
    }
}
