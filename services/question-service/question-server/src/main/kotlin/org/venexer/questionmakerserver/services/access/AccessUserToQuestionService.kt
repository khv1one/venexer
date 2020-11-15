package org.venexer.questionmakerserver.services.access

import org.springframework.stereotype.Service
import org.venexer.questionmakerserver.models.access.AccessUserToQuestion
import org.venexer.questionmakerserver.repos.access.AccessUserToQuestionRepo

@Service
final class AccessUserToQuestionService(
        accessUserToQuestionRepo: AccessUserToQuestionRepo
) : AccessToQuestionService<AccessUserToQuestion, AccessUserToQuestionRepo>(
        accessUserToQuestionRepo
)