package org.venexer.questionmakerserver.services.access

import org.springframework.stereotype.Service
import org.venexer.questionmakerserver.models.access.AccessGroupToQuestion
import org.venexer.questionmakerserver.repos.access.AccessGroupToQuestionRepo

@Service
final class AccessGroupToQuestionService(
        accessGroupToQuestionRepo: AccessGroupToQuestionRepo
) : AccessToQuestionService<AccessGroupToQuestion, AccessGroupToQuestionRepo>(
        accessGroupToQuestionRepo
)