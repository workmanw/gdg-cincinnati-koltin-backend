package com.gdg.cincinnati.demo.web.exception

class BookNotFoundException(
    val bookId: Long
): RuntimeException() {
}