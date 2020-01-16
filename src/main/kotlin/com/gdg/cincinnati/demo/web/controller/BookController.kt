package com.gdg.cincinnati.demo.web.controller

import com.gdg.cincinnati.demo.presistence.model.Book
import com.gdg.cincinnati.demo.presistence.repo.BookRepository
import com.gdg.cincinnati.demo.web.exception.BookIdMismatchException
import com.gdg.cincinnati.demo.web.exception.BookNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/books")
class BookController(
    val bookRepository: BookRepository
) {
    @GetMapping
    fun findAll(): MutableIterable<Book?> {
        return bookRepository.findAll();
    }

    @GetMapping("/title/{title}")
    fun findByTitle(@PathVariable title: String): List<Book?>?  {
        return bookRepository.findByTitle(title)
    }

    @GetMapping("/author/{author}")
    fun findByAuthor(@PathVariable author: String): List<Book?>?  {
        return bookRepository.findByAuthor(author)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Book? {
        return bookRepository.findById(id)
                .orElseThrow { BookNotFoundException(id) }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody book: Book): Book {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        bookRepository.findById(id)
                .orElseThrow { BookNotFoundException(id) }
        bookRepository.deleteById(id)
    }

    @PutMapping("/{id}")
    fun updateBook(@RequestBody book: Book, @PathVariable id: Long): Book? {
        if (book.id != id) {
            throw BookIdMismatchException()
        }
        bookRepository.findById(id)
                .orElseThrow { BookNotFoundException(id) }
        return bookRepository.save(book)
    }
}