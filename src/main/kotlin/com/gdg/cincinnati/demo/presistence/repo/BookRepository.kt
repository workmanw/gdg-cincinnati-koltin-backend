package com.gdg.cincinnati.demo.presistence.repo

import com.gdg.cincinnati.demo.presistence.model.Book
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book?, Long?> {
    fun findByTitle(title: String?): List<Book?>?
    fun findByAuthor(author: String?): List<Book?>?
}
