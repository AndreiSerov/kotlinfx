package com.example.kotlinfx.config

import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

/**
 * @author andreiserov
 */
@Component
class ControllerManager(
    @Value("classpath:/table_view.fxml")
    private var table: Resource,
    @Value("classpath:/contact_view.fxml")
    private var contact: Resource,
    private val applicationContext: ApplicationContext
) {

    val contactViewScene: Scene by lazy { buildScene(contact) }
    val tableViewScene: Scene by lazy { buildScene(table) }

    private fun buildScene(res: Resource) = Scene(
        FXMLLoader(res.url).apply {
            setControllerFactory {
                applicationContext.getBean(it)
            }
        }.load(),
        600.0,
        600.0
    )
}