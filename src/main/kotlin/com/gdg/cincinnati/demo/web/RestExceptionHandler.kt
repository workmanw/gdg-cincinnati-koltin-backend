package com.gdg.cincinnati.demo.web

import com.gdg.cincinnati.demo.web.exception.BookIdMismatchException
import com.gdg.cincinnati.demo.web.exception.BookNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.ConstraintViolationException


@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(BookNotFoundException::class)
    protected fun handleNotFound(ex: BookNotFoundException?, request: WebRequest?): ResponseEntity<Any> {
        return handleExceptionInternal(ex!!, "Book \"${ex.bookId}\" not found", HttpHeaders(), HttpStatus.NOT_FOUND, request!!)
    }

    @ExceptionHandler(
        BookIdMismatchException::class,
        ConstraintViolationException::class,
        DataIntegrityViolationException::class)
    fun handleBadRequest(ex: Exception, request: WebRequest?): ResponseEntity<Any> {
        return handleExceptionInternal(ex, ex
                .localizedMessage, HttpHeaders(), HttpStatus.BAD_REQUEST, request!!)
    }
}