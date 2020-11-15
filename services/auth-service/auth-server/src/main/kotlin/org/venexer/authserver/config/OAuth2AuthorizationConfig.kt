package org.venexer.authserver.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.env.Environment
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.venexer.authserver.services.AuthClientDetailsService
import org.venexer.authserver.services.CustomUserDetailsService
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
class OAuth2AuthorizationConfig(
    @Qualifier("authenticationManagerBean")
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val authClientDetailsService: AuthClientDetailsService,
    private val encoder: PasswordEncoder,
    private val env: Environment
) : AuthorizationServerConfigurerAdapter() {

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients?.withClientDetails(authClientDetailsService)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints
            ?.tokenStore(tokenStore())
            ?.authenticationManager(authenticationManager)
            ?.userDetailsService(userDetailsService)
    }

    override fun configure(oauthServer: AuthorizationServerSecurityConfigurer?) {
        oauthServer
            ?.tokenKeyAccess("permitAll()")
            ?.checkTokenAccess("isAuthenticated()")
            ?.passwordEncoder(encoder)
            ?.allowFormAuthenticationForClients()
    }

    @Bean
    fun tokenStore(): TokenStore {
        return JdbcTokenStore(oauthDataSource())
    }

    @Primary
    fun oauthDataSource(): DataSource {
        val url = env.getProperty("spring.datasource.url").orEmpty()
        val username = env.getProperty("spring.datasource.username").orEmpty()
        val password = env.getProperty("spring.datasource.password").orEmpty()

        return DriverManagerDataSource(url, username, password)
    }
}