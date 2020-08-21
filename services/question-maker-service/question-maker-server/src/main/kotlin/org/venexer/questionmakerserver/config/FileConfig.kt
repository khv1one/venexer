package org.venexer.questionmakerserver.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class FileConfig() : WebMvcConfigurer {

    @Value("\${upload.img.path}")
    private lateinit var uploadImgPath: String
    @Value("\${upload.sound.path}")
    private lateinit var uploadSoundPath: String

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        println("[TEST] init property $uploadImgPath")
        println("[TEST] init property $uploadSoundPath")

        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://$uploadImgPath/")
        registry.addResourceHandler("/sound/**")
                .addResourceLocations("file://$uploadSoundPath/")
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
    }
}