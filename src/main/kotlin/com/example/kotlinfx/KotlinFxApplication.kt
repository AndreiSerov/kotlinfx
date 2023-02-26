package com.example.kotlinfx

import javafx.application.Application
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlinFxApplication

fun main(args: Array<String>) {
    Application.launch(JavafxApplication::class.java, *args)
}
