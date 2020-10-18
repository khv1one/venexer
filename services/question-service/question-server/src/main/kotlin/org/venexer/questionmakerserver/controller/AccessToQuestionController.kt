package org.venexer.questionmakerserver.controller

import org.springframework.stereotype.Controller
import org.venexer.questionmakerserver.models.access.AccessUserToQuestion
import org.venexer.questionmakerserver.services.access.AccessToQuestionService

@Controller
class AccessToQuestionController(
        //private val questionsService: QuestionsService,
        private val accessToQuestion: AccessUserToQuestion
) {
    //test
}