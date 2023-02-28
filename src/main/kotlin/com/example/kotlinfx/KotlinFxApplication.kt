package com.example.kotlinfx

import com.example.kotlinfx.config.JavafxApplication
import javafx.application.Application
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlinFxApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(JavafxApplication::class.java, *args)
        }
    }
}
