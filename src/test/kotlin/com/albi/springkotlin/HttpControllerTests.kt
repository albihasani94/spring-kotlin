package com.albi.springkotlin

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class HttpControllerTests(@Autowired val mockMvc: MockMvc) {
    @MockkBean
    private lateinit var userRepository: UserRepository

    @MockkBean
    private lateinit var articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val albi = User("ahasani", "Albin", "Hasani")
        val article = Article(
                title = "Hi there folks",
                headline = "Test",
                content = "la la la",
                author = albi
        )
        val article1 = Article(
                title = "Hi there folks again",
                headline = "Test 2",
                content = "la la la la la",
                author = albi
        )

        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(article, article1)

        mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.[0].author.login").value(albi.login))
                .andExpect(jsonPath("\$.[0].slug").value(article.slug))
                .andExpect(jsonPath("\$.[1].author.login").value(albi.login))
                .andExpect(jsonPath("\$.[1].slug").value(article1.slug))
    }

    @Test
    fun `List users`() {
        val albi = User("ahasani", "Albin", "Hasani")
        val davidLynch = User("dlynch", "David", "Lynch")

        every { userRepository.findAll() } returns listOf(albi, davidLynch)
        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.[0].login").value(albi.login))
                .andExpect(jsonPath("\$.[1].login").value(davidLynch.login))
    }
}