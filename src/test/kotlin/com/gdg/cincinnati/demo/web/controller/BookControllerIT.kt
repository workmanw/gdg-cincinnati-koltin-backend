package com.gdg.cincinnati.demo.web.controller

import com.gdg.cincinnati.demo.presistence.model.Book
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.ResponseEntity
import javax.persistence.EntityManagerFactory


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerIT {

    @Autowired
    lateinit var entityManagerFactory: EntityManagerFactory

    @LocalServerPort
    var port = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @BeforeEach
    fun setup() {
        val em = entityManagerFactory.createEntityManager();
        em.transaction.begin();
        em.createNativeQuery("truncate table book;").executeUpdate();
        em.transaction.commit();
    }

    @Test
    fun testHappyPath() {
        val b = Book(title = "Test Title", author = "Test Author")
        val createdBook = restTemplate.postForEntity(
            "http://localhost:${port}/api/books/", b, Book::class.java).body
        requireNotNull(createdBook)

        val bookById = restTemplate.getForEntity<Book>(
            "http://localhost:${port}/api/books/${createdBook.id}",
            Book::class.java).body
        requireNotNull(bookById)
        assertEquals("Test Title", bookById.title)

        val bookList = restTemplate.getForEntity<Array<Book>>(
            "http://localhost:${port}/api/books",
            Array<Book>::class.java).body
        requireNotNull(bookList)
        assertEquals(1, bookList.size)
        assertEquals(createdBook.id, bookList[0].id)
    }
}