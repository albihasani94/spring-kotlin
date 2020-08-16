package com.albi.springkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class SpringKotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringKotlinApplication>(*args)
}
