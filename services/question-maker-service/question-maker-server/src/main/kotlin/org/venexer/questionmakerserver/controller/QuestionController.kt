package org.venexer.questionmakerserver.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.venexer.authclient.dto.AuthUserInfo
import org.venexer.questionmakerclient.dto.QuestionDto
import org.venexer.questionmakerclient.dto.QuestionInfoDto
import org.venexer.questionmakerserver.models.Question
import org.venexer.questionmakerserver.services.QuestionsService
import java.io.File
import java.util.*

@RestController
class QuestionController(private val questionsService: QuestionsService) {

    @Value("\${upload.img.path}")
    private lateinit var uploadImgPath: String
    @Value("\${upload.sound.path}")
    private lateinit var uploadSoundPath: String

    @GetMapping("/my")
    fun getAllAuthUserQuestions(@AuthenticationPrincipal auth: AuthUserInfo): List<QuestionInfoDto> {
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

    @RequestMapping("/edit/{id}", method = [RequestMethod.POST])
    fun editQuestion(
            @PathVariable(value="id") questionId: Long,
            @AuthenticationPrincipal auth: AuthUserInfo,
            @RequestBody questionDto: QuestionDto
    ): ResponseEntity<String> {
        val oldQuestion = questionsService.loadQuestionByIdAndCreatorId(questionId, auth.id)
        val newQuestion = questionDto.toQuestion(auth)

        return if (oldQuestion != null) {
            questionsService.update(newQuestion, oldQuestion)
            ResponseEntity.status(HttpStatus.OK).body("Question with id $questionId was updated")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question with id $questionId not found")
        }
    }

    @RequestMapping("/delete/{id}", method = [RequestMethod.POST])
    fun deleteQuestion(
            @PathVariable(value="id") questionId: Long,
            @AuthenticationPrincipal auth: AuthUserInfo
    ): ResponseEntity<String> {
        val questionToDelete = questionsService.loadQuestionByIdAndCreatorId(questionId, auth.id)

        return if (questionToDelete != null) {
            questionsService.delete(questionToDelete)
            ResponseEntity.status(HttpStatus.OK).body("Question with id $questionId was deleted")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question with id $questionId not found")
        }
    }

    @RequestMapping(
            value = ["/hide/{id}"],
            params = ["val"],
            method = [RequestMethod.PUT]
    )
    fun hideQuestion(
            @PathVariable(value="id") questionId: Long,
            @RequestParam(value="val") value: Boolean,
            @AuthenticationPrincipal auth: AuthUserInfo
    ): ResponseEntity<String>{
        val question = questionsService.loadQuestionByIdAndCreatorId(questionId, auth.id)

        return if (question != null) {
            questionsService.setHidden(question, value)
            ResponseEntity.status(HttpStatus.OK).body("Question with id $questionId was hided")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question with id $questionId not found")
        }
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

    private fun saveFile(fileToSave: MultipartFile?, path: String): String? {
        return if (
                fileToSave != null &&
                fileToSave.originalFilename != null &&
                fileToSave.originalFilename?.isEmpty() == false
        ) {
            val uploadDir = File(path)
            if (!uploadDir.exists()) uploadDir.mkdir()

            val filename = UUID.randomUUID().toString() + "." + fileToSave.originalFilename
            fileToSave.transferTo(File("$path/$filename"))

            filename
        } else {
            null
        }
    }
}