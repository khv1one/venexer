package org.venexer.authservice.models

import org.springframework.security.core.userdetails.UserDetails
import org.venexer.authservice.enums.Roles
import javax.persistence.*

@Entity
@Table(name = "users")
data class User  (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0,

    @Column(length = 200)
    private val username: String,

    @Column(length = 200)
    private val password: String,

    private val activated: Boolean = false,

    private val activationKey: String? = "",

    private val resetPasswordKey: String? = "",

    private val authorities: HashSet<Roles> = HashSet()

): UserDetails {
    fun id(): Long = id

    override fun getAuthorities(): HashSet<Roles> = authorities

    override fun isEnabled(): Boolean = activated

    override fun getUsername(): String = username

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String = password

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}