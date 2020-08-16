package com.albi.springkotlin

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {
    @Bean
    fun databaseInitializer(userRepository: UserRepository, articleRepository: ArticleRepository) =
            ApplicationRunner {
                val albi = userRepository.save(User("ahasani", "Albin", "Hasani"))
                articleRepository.save(Article(
                        title = "Hi there folks",
                        headline = "Test",
                        content = "la la la",
                        author = albi
                ))
                articleRepository.save(Article(
                        title = "Hi there folks again",
                        headline = "Test 2",
                        content = "la la la la la",
                        author = albi
                ))

            }
}