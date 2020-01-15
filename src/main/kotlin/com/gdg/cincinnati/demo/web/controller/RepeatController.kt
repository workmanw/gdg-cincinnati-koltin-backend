package com.gdg.cincinnati.demo.web.controller

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class RepeatController {
    @GetMapping("/repeat")
    fun repeat(@RequestParam message: String): String {
        return message
    }
}