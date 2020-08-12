package org.venexer.questionmakerservice.controller

import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation.*
import org.venexer.authclient.dto.AuthUserInfo

@RestController
class QuestionController() {

    @GetMapping("/all")
    fun createNewAccount(@AuthenticationPrincipal auth: AuthUserInfo): String {

        return auth.toString()
    }
}