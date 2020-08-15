package com.albi.springkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoryTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val articleRepository: ArticleRepository
) {
    @Test
    fun `When findByIdOrNull then return Article`() {
        val albi = User("ahasani", "Albin", "Hasani")
        entityManager.persist(albi)
        val article = Article("Testing Kotlin", "Hi fellow readers", "Test", author = albi)
        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`() {
        val albi = User("ahasani", "Albin", "Hasani")
        entityManager.persist(albi)
        entityManager.flush()
        val found = userRepository.findByLogin(albi.login)
        assertThat(found).isEqualTo(albi)
    }
}