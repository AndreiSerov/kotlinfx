package com.example.kotlinfx.view

import com.example.kotlinfx.config.ControllerManager
import com.example.kotlinfx.domain.Contact
import com.example.kotlinfx.persist.ContactDao
import javafx.application.HostServices
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TableRow
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


/**
 * @author andreiserov
 */
@Component
class TableViewController(
    val hostServices: HostServices,
    val stage: Stage
) {
    @Autowired
    lateinit var controllerManager: ControllerManager

    @FXML
    var tableView: TableView<Contact>? = null

    @FXML
    var name: TextField? = null

    @FXML
    var phone: TextField? = null

    @FXML
    var label: Label? = null

    fun initialize() {
        tableView!!.items.addAll(ContactDao.findAll())

        tableView!!.setRowFactory { tv ->
            // Define our new TableRow
            val row: TableRow<Contact?> = TableRow()
            row.setOnMouseClicked { stage.scene = controllerManager.contactViewScene }
            row
        }
    }

    @FXML
    protected fun addContact(event: ActionEvent?) {
        val contact = Contact(name!!.text, phone!!.text)
        ContactDao.createContact(contact)
        tableView!!.items.add(contact)
    }

}