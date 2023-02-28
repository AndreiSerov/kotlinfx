package com.example.kotlinfx.view

import com.example.kotlinfx.config.ControllerManager
import javafx.application.HostServices
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author andreiserov
 */
@Component
class ContactViewController(
    val hostServices: HostServices,
    val stage: Stage,
) {

    @Autowired
    lateinit var controllerManager: ControllerManager

    @FXML
    protected fun goToPhoneList(event: ActionEvent?) {
        stage.scene = controllerManager.tableViewScene
    }
}