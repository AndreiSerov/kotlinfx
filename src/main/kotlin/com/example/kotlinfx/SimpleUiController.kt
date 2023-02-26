package com.example.kotlinfx

import javafx.application.HostServices
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import org.springframework.stereotype.Component

/**
 * @author andreiserov
 */
@Component
class SimpleUiController(val hostServices: HostServices) {

    @FXML
    var label: Label? = null

    @FXML
    var button: Button? = null

    @FXML
    fun initialize() {
        button!!.onAction = EventHandler { actionEvent: ActionEvent? ->
            label!!.text = hostServices.documentBase
        }
    }
}