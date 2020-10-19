package org.venexer.questionmakerserver.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.venexer.authclient.dto.AuthUserInfo
import org.venexer.questionmakerclient.dto.access.AccessToQuestionDto
import org.venexer.questionmakerclient.dto.access.AccessToQuestionFromClientDto
import org.venexer.questionmakerserver.models.access.AccessUserToQuestion
import org.venexer.questionmakerserver.services.access.AccessUserToQuestionService

@RestController
class AccessToQuestionController(
        private val accessUserToQuestionService: AccessUserToQuestionService
) {
    @GetMapping("/my")
    fun getMyAccess(@AuthenticationPrincipal auth: AuthUserInfo): List<AccessToQuestionDto> {
        return accessUserToQuestionService
                .findByCreatorId(auth.id)
                .map {it.toAccessToQuestionDto()}
    }

    @GetMapping("/to_me")
    fun getGaveMeAccess(@AuthenticationPrincipal auth: AuthUserInfo): List<AccessToQuestionDto> {
        return accessUserToQuestionService
                .findByTargetId(auth.id)
                .map {it.toAccessToQuestionDto()}
    }

    @PostMapping("/add/user")
    fun giveAccessUser(
            @AuthenticationPrincipal auth: AuthUserInfo,
            @RequestBody accessFromClientDto: AccessToQuestionFromClientDto
    ): AccessToQuestionDto {
        return accessUserToQuestionService
                .create(accessFromClientDto.toAccessUserToQuestion(auth))
                .toAccessToQuestionDto()
    }

    @PostMapping("/remove/user/{user_id}/{question_id}")
    fun removeAccessUser(
            @AuthenticationPrincipal auth: AuthUserInfo,
            @PathVariable(value="user_id") targetId: Long,
            @PathVariable(value="question_id") questionId: Long
    ): ResponseEntity<String> {
        val accessToDelete = accessUserToQuestionService
                .findByCreatorIdAndTargetIdAndQuestionId(auth.id, targetId, questionId)

        return if (accessToDelete != null) {
            accessUserToQuestionService.delete(accessToDelete)
            ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Access with target $targetId and questionId $questionId was deleted")
        } else {
            ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Access with target $targetId and questionId $questionId not found")
        }
    }

    private fun AccessToQuestionFromClientDto.toAccessUserToQuestion(auth: AuthUserInfo) = AccessUserToQuestion(
        targetId = targetId,
        creatorId = auth.id,
        questionId =  questionId
    )
}