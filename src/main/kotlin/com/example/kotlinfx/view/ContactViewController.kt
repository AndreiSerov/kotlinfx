package com.example.kotlinfx.view

import com.example.kotlinfx.config.ControllerManager
import com.example.kotlinfx.domain.Contact
import javafx.application.HostServices
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.TextField
import javafx.stage.Stage
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * @author andreiserov
 */
@Component
@Scope("")
class ContactViewController(
    val hostServices: HostServices,
    val stage: Stage,
) {

    @Autowired
    lateinit var controllerManager: ControllerManager

    @FXML
    var name: TextField? = null
    @FXML
    var phone: TextField? = null
    @FXML
    protected fun goToPhoneList(event: ActionEvent?) {
        stage.scene = controllerManager.tableViewScene
    }

    @FXML
    fun receiveData(event: ActionEvent) {
        logger.info { "receiveData is called" }
        val node: Node = event.source as Node
        val contact = node.scene.window.userData as Contact
        name!!.promptText = contact.name
        name!!.promptText = contact.phone
    }

    companion object : KLogging()

}