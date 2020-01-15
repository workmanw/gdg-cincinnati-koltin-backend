package com.gdg.cincinnati.demo.web.controller

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class IncrementController {
    @GetMapping("/increment")
    fun repeat(@RequestParam num: Long): Long {
        return num + 1
    }
}