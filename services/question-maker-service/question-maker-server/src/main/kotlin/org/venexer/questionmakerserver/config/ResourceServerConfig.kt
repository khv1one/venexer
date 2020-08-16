package org.venexer.questionmakerserver.config

import com.google.gson.Gson
import feign.RequestInterceptor
import org.codehaus.jettison.json.JSONException
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestOperations
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices
import org.venexer.authclient.constants.Constants
import org.venexer.authclient.dto.AuthUserInfo


@Configuration
@EnableResourceServer
class ResourceServerConfig(
    val sso: ResourceServerProperties,
    val oAuth2ClientContext: OAuth2ClientContext
) : ResourceServerConfigurerAdapter() {

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    fun clientCredentialsResourceDetails(): ClientCredentialsResourceDetails {
        return ClientCredentialsResourceDetails()
    }

    @Bean
    fun oauth2FeignRequestInterceptor(): RequestInterceptor {
        return OAuth2FeignRequestInterceptor(oAuth2ClientContext, clientCredentialsResourceDetails())
    }

    @Bean
    fun restTemplate(oauth2ClientContext: OAuth2ClientContext): OAuth2RestOperations {
        return OAuth2RestTemplate(clientCredentialsResourceDetails(), oauth2ClientContext)
    }

    @Bean
    @Primary
    fun resourceServerTokenServices(): ResourceServerTokenServices {
        val userInfoTokenServices = UserInfoTokenServices(sso.userInfoUri, sso.clientId)
        userInfoTokenServices.setPrincipalExtractor { map: Map<String, *>? ->
            val jsonUser = map?.get(Constants.AUTH_USER_DTO) as String?
                ?: throw JSONException("Can't read AuthUserInfo from '${Constants.AUTH_USER_DTO}' field")

            return@setPrincipalExtractor Gson().fromJson(jsonUser, AuthUserInfo::class.java)
        }
        return userInfoTokenServices
    }

    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()
            ?.anyRequest()?.authenticated();
    }
}

