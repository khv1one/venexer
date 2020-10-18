package org.venexer.questionmakerserver.services.access

import org.springframework.stereotype.Service
import org.venexer.questionmakerserver.models.access.AccessToQuestion
import org.venexer.questionmakerserver.repos.access.AccessToQuestionRepo
import org.venexer.utils.service.ServiceBase

@Service
class AccessToQuestionService<T: AccessToQuestion, R : AccessToQuestionRepo<T>>(
        private val accessUserRepo: R
) : ServiceBase<T, Long, R>(accessUserRepo) {

    fun findById(questionId: Long): HashSet<T> {
        return accessUserRepo.findByQuestionId(questionId)
    }

    fun findByIds(questionIds: Set<Long>): HashSet<T> {
        return accessUserRepo.findInQuestionId(questionIds)
    }

}