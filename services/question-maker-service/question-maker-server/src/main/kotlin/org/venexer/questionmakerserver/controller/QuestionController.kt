package org.venexer.questionmakerserver.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.venexer.authclient.dto.AuthUserInfo
import org.venexer.questionmakerclient.dto.QuestionDto
import org.venexer.questionmakerclient.dto.QuestionInfoDto
import org.venexer.questionmakerserver.models.Question
import org.venexer.questionmakerserver.services.QuestionsService

@RestController
class QuestionController(val questionsService: QuestionsService) {

    @GetMapping("/my")
    fun getAllAuthUserTests(@AuthenticationPrincipal auth: AuthUserInfo): List<QuestionInfoDto> {
        return questionsService
            .loadQuestionsByCreatorId(auth.id)
            .map { it.toQuestionInfoDto() }
    }

    @PostMapping("/add")
    fun createQuestion(
        @AuthenticationPrincipal auth: AuthUserInfo,
        @RequestBody questionDto: QuestionDto
    ): QuestionInfoDto {
        return questionsService
            .create(questionDto.toQuestion(auth))
            .toQuestionInfoDto()
    }

    @RequestMapping("/user/{id}")
    fun getNotHiddenQuestionsOfUser(
        @PathVariable(value="id") userId: Long,
        @AuthenticationPrincipal auth: AuthUserInfo
    ): List<QuestionInfoDto> {
        return questionsService
            .loadQuestionsByCreatorId(userId)
            .map { it.toQuestionInfoDto() }
    }

    private fun QuestionDto.toQuestion(auth: AuthUserInfo) = Question(
        text = text,
        resourceLink = resourceLink,
        answers = answers,
        trueAnswer = trueAnswer,
        groupId = groupId,
        creatorId = auth.id,
        forOrganizationId = forOrganizationId,
        isPrivate = isPrivate,
        isHidden = isHidden,
        deletedTime = null
    )
}