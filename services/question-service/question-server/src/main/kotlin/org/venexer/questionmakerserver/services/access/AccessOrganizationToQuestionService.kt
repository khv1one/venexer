package org.venexer.questionmakerserver.services.access

import org.springframework.stereotype.Service
import org.venexer.questionmakerserver.models.access.AccessOrganizationToQuestion
import org.venexer.questionmakerserver.repos.access.AccessOrganizationToQuestionRepo

@Service
final class AccessOrganizationToQuestionService(
        accessOrganizationToQuestionRepo: AccessOrganizationToQuestionRepo
) : AccessToQuestionService<AccessOrganizationToQuestion, AccessOrganizationToQuestionRepo>(
        accessOrganizationToQuestionRepo
)