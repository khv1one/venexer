package org.venexer.authservice.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.venexer.authservice.enums.Roles
import javax.persistence.*

@Entity
@Table(name = "users")
data class User  (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long,

    @Column(length = 200)
    private val username: String,

    @Column(length = 200)
    private val password: String,

    private val activated: Boolean,

    private val activationKey: String,

    private val resetPasswordKey: String,

    private val authorities: MutableCollection<Roles>

): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun isEnabled(): Boolean {
        return activated
    }

    override fun getUsername(): String {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}