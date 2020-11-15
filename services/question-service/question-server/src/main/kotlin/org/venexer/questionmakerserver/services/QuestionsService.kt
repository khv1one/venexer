package org.venexer.questionmakerserver.services

import org.springframework.stereotype.Service
import org.venexer.questionmakerserver.models.Question
import org.venexer.questionmakerserver.repos.QuestionsRepo
import org.venexer.utils.service.ServiceBase
import java.util.*

@Service
class QuestionsService(
        private val questionsRepo: QuestionsRepo
) : ServiceBase<Question, Long, QuestionsRepo>(questionsRepo) {

    fun loadQuestionsByCreatorId(userId: Long): Set<Question> {
        return questionsRepo.findByCreatorIdAndDeletedTime(userId)
    }

    fun loadQuestionByIdAndCreatorId(id: Long, creatorId: Long): Question? {
        return questionsRepo.findQuestionByIdAndCreatorIdAndDeletedTime(id, creatorId)
    }

    fun setHidden(question: Question, value: Boolean): Question {
        return questionsRepo.save(question.copy(isHidden = value, updatedTime = Date()))
    }

}