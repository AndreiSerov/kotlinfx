package com.example.kotlinfx

import javafx.application.Application
import javafx.application.HostServices
import javafx.application.Platform
import javafx.stage.Stage
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ApplicationEvent
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.support.GenericApplicationContext
import java.util.function.*


/**
 * @author andreiserov
 */
class JavafxApplication : Application() {

    private var context: ConfigurableApplicationContext? = null

    @Throws(Exception::class)
    override fun init() {
        val initializer =
            ApplicationContextInitializer<GenericApplicationContext> { genericApplicationContext ->
                genericApplicationContext.registerBean(
                    Application::class.java,
                    Supplier { this@JavafxApplication })
                genericApplicationContext.registerBean(
                    Parameters::class.java,
                    Supplier { parameters })
                genericApplicationContext.registerBean(
                    HostServices::class.java,
                    Supplier { hostServices })
            }
        context = SpringApplicationBuilder().sources(KotlinFxApplication::class.java)
            .initializers(initializer)
            .build().run(*parameters.raw.toTypedArray())
    }

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        context?.publishEvent(StageReadyEvent(primaryStage))
    }

    @Throws(Exception::class)
    override fun stop() {
        context?.close()
        Platform.exit()
    }

    internal inner class StageReadyEvent(source: Any) : ApplicationEvent(source) {
        val stage: Stage
            get() = Stage::class.java.cast(getSource())
    }
}