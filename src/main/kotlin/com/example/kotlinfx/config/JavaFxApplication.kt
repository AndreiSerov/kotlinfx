package com.example.kotlinfx.config

import com.example.kotlinfx.KotlinFxApplication
import javafx.application.Application
import javafx.application.HostServices
import javafx.application.Platform
import javafx.stage.Stage
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ApplicationEvent
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.registerBean
import java.util.function.*
import kotlin.reflect.jvm.jvmName


/**
 * @author andreiserov
 */
class JavafxApplication : Application() {

    lateinit var context: ConfigurableApplicationContext
    private val stage: Stage = Stage()

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
                genericApplicationContext.registerBean(
                    HostServices::class.java,
                    Supplier { hostServices })
                genericApplicationContext.registerBean(
                    Stage::class.java,
                    Supplier { stage }
                )
            }

        context = SpringApplicationBuilder()
            .sources(KotlinFxApplication::class.java)
            .initializers(initializer)
            .build()
            .run(*parameters.raw.toTypedArray())
    }

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        context.publishEvent(StageReadyEvent(stage))
    }

    @Throws(Exception::class)
    override fun stop() {
        context.close()
        Platform.exit()
    }

    internal inner class StageReadyEvent(source: Any) : ApplicationEvent(source) {
        val stage: Stage get() = Stage::class.java.cast(getSource())
    }
}