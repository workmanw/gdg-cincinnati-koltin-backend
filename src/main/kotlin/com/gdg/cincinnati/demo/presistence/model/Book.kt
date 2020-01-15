package com.gdg.cincinnati.demo.presistence.model

import javax.persistence.*


@Entity
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    @Column(nullable = false, unique = true)
    var title: String? = null
    @Column(nullable = false)
    var author: String? = null
}