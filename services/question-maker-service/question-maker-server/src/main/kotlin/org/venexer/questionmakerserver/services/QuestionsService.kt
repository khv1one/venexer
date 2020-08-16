package org.venexer.questionmakerserver.services

import org.springframework.stereotype.Service
import org.venexer.questionmakerserver.models.Question
import org.venexer.questionmakerserver.repos.QuestionsRepo

@Service
class QuestionsService(val questionsRepo: QuestionsRepo) {

    fun create(question: Question): Question {
        return questionsRepo.save(question)
    }

    fun loadQuestionsByCreatorId(userId: Long): Set<Question> {
        return questionsRepo.findByCreatorId(userId)
    }
}