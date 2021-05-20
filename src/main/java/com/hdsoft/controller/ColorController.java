package com.hdsoft.controller;

import com.hdsoft.config.Config;
import com.hdsoft.model.Color;
import com.hdsoft.service.Service;
import com.hdsoft.ui.UI;
import com.hdsoft.utils.Dialog;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("colorController")
public class ColorController extends VBox {

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton refresh;

    @FXML
    private FlowPane pane;

    private Service service;

    public ColorController(Service service) {
        this.service=service;

        FXMLLoader fxml = UI.getColors();
        fxml.setController(this);
        fxml.setRoot(this);

        try {
            fxml.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        setStyle("-fx-background-color: #000;");
        service.setParent(this);
        refresh(null);
        add.setOnAction(this::add);
        refresh.setOnAction(this::refresh);
    }

    private void refresh(ActionEvent event) {
        List<Color> colors = service.selectColor();
        List<NodeController> nodes = colors.stream().map(m -> new NodeController(m, this)).collect(Collectors.toList());
        pane.getChildren().setAll(nodes);
    }

    private void add(ActionEvent event) {
        String s = Dialog.colorInput(this, "");
        if (!s.isEmpty()) {
            Color c = new Color(s);
            boolean b = service.saveColor(c);
            if (b) {
                Dialog.showInformation(this, "Add New Color", "New Color Code Added Successfully");
            } else {
                Dialog.showError(this, "Add New Color", "New Color Code Adding Failed");
            }
        }
    }

    public void updateOrDelete(Color color) {
        String type = Dialog.showConfirmation(this, "Update Or Delete Color Code", "Press Update to update color code\nOr\nPress Delete to delete color code");
        if (type.equals("Update")) {
            String s = Dialog.colorInput(this, color.getColor());
            if (!s.isEmpty()) {
               color.setColor(s);
                boolean b = service.updateColor(color);
                if (b) {
                    Dialog.showInformation(this, "Update Color Code", "Color Code Updated Successfully");
                } else {
                    Dialog.showError(this, "Update Color Code", "Color Code Updated Failed");
                }
            }
        }
        if (type.equals("Delete")) {
            boolean b = service.deleteColor(color);
            if (b) {
                Dialog.showInformation(this, "Delete Color Code", "Color Code Deleted Successfully");
            } else {
                Dialog.showError(this, "Delete Color Code", "Color Code Deleted Failed");
            }
        }
    }
}
