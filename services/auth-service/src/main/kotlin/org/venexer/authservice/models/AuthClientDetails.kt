package org.venexer.authservice.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.provider.ClientDetails
import javax.persistence.*

@Entity
@Table(name = "auth_client_details")
data class AuthClientDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long,

    private val clientId: String,

    private val clientSecret: String,

    private val grantTypes: String?,

    private val scopes: String?,

    private val resources: String?,

    private val redirectUris: String?,

    private val accessTokenValidity: Int,

    private val refreshTokenValidity: Int,

    private val additionalInformation: String

) : ClientDetails {

    override fun getClientId(): String = clientId

    override fun getClientSecret(): String  = clientSecret

    override fun isSecretRequired(): Boolean = true

    override fun isScoped(): Boolean = true

    override fun getScope(): Set<String>  = createSet(scopes)

    override fun getResourceIds(): Set<String>  = createSet(resources)

    override fun getRegisteredRedirectUri(): Set<String>  = createSet(redirectUris)

    override fun getAccessTokenValiditySeconds(): Int = accessTokenValidity

    override fun getRefreshTokenValiditySeconds(): Int = refreshTokenValidity

    override fun getAdditionalInformation(): Map<String, Any> = HashMap()

    override fun isAutoApprove(scope: String?): Boolean = true

    override fun getAuthorities(): Collection<GrantedAuthority> = ArrayList()

    override fun getAuthorizedGrantTypes(): Set<String> = createSet(grantTypes)

    companion object {
        private const val serialVersionUID: Long = 1L

        fun createSet(dat: String?): Set<String> {
            return dat?.let { HashSet(it.split(",")) } ?: run { emptySet<String>() }
        }
    }
}