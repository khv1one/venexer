package org.venexer.authservice.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.venexer.authservice.services.AuthClientDetailsService
import org.venexer.authservice.services.CustomUserDetailsService
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
class OAuth2AuthorizationConfig(
    @Qualifier("authenticationManagerBean")
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val authClientDetailsService: AuthClientDetailsService,
    private val encoder: PasswordEncoder
) : AuthorizationServerConfigurerAdapter() {

    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        security
            ?.tokenKeyAccess("permitAll")
            ?.checkTokenAccess("isAuthenticated()")
            ?.passwordEncoder(encoder)
            ?.allowFormAuthenticationForClients()
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients?.withClientDetails(authClientDetailsService)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints
            ?.tokenStore(tokenStore())
            ?.authenticationManager(authenticationManager)
            ?.userDetailsService(userDetailsService)
    }

    @Bean
    fun tokenStore(): TokenStore {
        return JdbcTokenStore(oauthDataSource())
    }

    @ConfigurationProperties(prefix = "spring.datasource")
    fun oauthDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }
}