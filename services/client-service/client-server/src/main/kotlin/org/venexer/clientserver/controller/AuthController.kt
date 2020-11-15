package org.venexer.clientserver.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.venexer.authclient.dto.AuthUserInfo

@RestController
class QuestionController() {

    @GetMapping("/login")
    fun loginPage(): String {
        return "loginPage"
    }
}