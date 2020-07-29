package org.venexer.authservice.dto

import java.io.Serializable

data class UserDto (
    private val id: String,
    private val username: String
) : Serializable { //TODO: delete?

    fun id(): String = id
    fun username(): String = username

    companion object {
        private const val serialVersionUID: Long = 1L
    }
}