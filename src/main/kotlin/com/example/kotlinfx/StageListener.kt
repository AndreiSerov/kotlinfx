package com.example.kotlinfx

import com.example.kotlinfx.JavafxApplication.StageReadyEvent
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationListener
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.URL
import javafx.fxml.FXMLLoader


/**
 * @author andreiserov
 */
@Component
class StageListener(
    @Value("\${spring.application.ui.title}") applicationTitle: String,
    @Value("classpath:/ui.fxml") fxml: Resource, applicationContext: ApplicationContext
) : ApplicationListener<StageReadyEvent?> {
    private val applicationTitle: String
    private val fxml: Resource
    private val applicationContext: ApplicationContext

    init {
        this.applicationTitle = applicationTitle
        this.fxml = fxml
        this.applicationContext = applicationContext
    }

    override fun onApplicationEvent(stageReadyEvent: StageReadyEvent) {
        try {
            val stage: Stage = stageReadyEvent.stage
            val url: URL = fxml.getURL()
            val fxmlLoader = FXMLLoader(url)
            fxmlLoader.setControllerFactory(applicationContext::getBean)
            val root: Parent = fxmlLoader.load()
            val scene = Scene(root, 600.0, 600.0)
            stage.setScene(scene)
            stage.setTitle(applicationTitle)
            stage.show()
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}