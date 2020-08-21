package org.venexer.questionmakerserver.services

import org.springframework.stereotype.Service
import org.venexer.questionmakerserver.models.Question
import org.venexer.questionmakerserver.repos.QuestionsRepo
import java.util.*

@Service
class QuestionsService(private val questionsRepo: QuestionsRepo) {

    fun create(question: Question): Question {
        return questionsRepo.save(question)
    }

    fun loadQuestionsByCreatorId(userId: Long): Set<Question> {
        return questionsRepo.findByCreatorIdAndDeletedTime(userId)
    }

    fun loadQuestionByIdAndCreatorId(id: Long, creatorId: Long): Question? {
        return questionsRepo.findQuestionByIdAndCreatorIdAndDeletedTime(id, creatorId)
    }

    fun delete(question: Question): Question {
        return questionsRepo.save(question.copy(updatedTime = Date(), deletedTime = Date()))
    }

    fun setHidden(question: Question, value: Boolean): Question {
        return questionsRepo.save(question.copy(isHidden = value, updatedTime = Date()))
    }

    fun update(newQuestion: Question, oldQuestion: Question): Question {
        return questionsRepo.save(newQuestion.copy(
                id = oldQuestion.id,
                createdTime = oldQuestion.createdTime
        ))
    }
}