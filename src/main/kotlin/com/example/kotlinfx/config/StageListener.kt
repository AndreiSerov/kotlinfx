package com.example.kotlinfx.config

import com.example.kotlinfx.config.JavafxApplication.StageReadyEvent
import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.io.IOException


/**
 * @author andreiserov
 */
@Component
internal class StageListener(
    @Value("\${spring.application.ui.title}")
    var applicationTitle: String,
) : ApplicationListener<StageReadyEvent> {

    @Autowired
    lateinit var controllerManager: ControllerManager

    override fun onApplicationEvent(stageReadyEvent: StageReadyEvent) {
        try {
            val stage: Stage = stageReadyEvent.stage

            stage.scene = controllerManager.tableViewScene
            stage.title = applicationTitle
            stage.show()
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}