package com.hdsoft.controller;

import com.hdsoft.config.Config;
import com.hdsoft.model.Gradient;
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

@Component("gradientController")
public class GradientController extends VBox {

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton refresh;

    @FXML
    private FlowPane pane;


    private Service service;

    public GradientController(Service service) {
        this.service=service;

        FXMLLoader fxml = UI.getGradients();
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
        refresh(null);
        add.setOnAction(this::add);
        refresh.setOnAction(this::refresh);
    }

    private void refresh(ActionEvent event) {
        List<Gradient> colors = service.selectGradient();
        List<NodeController> nodes = colors.stream().map(m -> new NodeController(m, this)).collect(Collectors.toList());
        pane.getChildren().setAll(nodes);
    }

    private void add(ActionEvent event) {
        String s = Dialog.gradientInput(this, "");
        if (!s.isEmpty()) {
            Gradient c = new Gradient(s);
            boolean b = service.saveGradient(c);
            if (b) {
                Dialog.showInformation(this, "Add New Gradient", "New Gradient Code Added Successfully");
            } else {
                Dialog.showError(this, "Add New Gradient", "New Gradient Code Adding Failed");
            }
        }
    }

    public void updateOrDelete(Gradient gradient) {
        String type = Dialog.showConfirmation(this, "Update Or Delete Gradient Code", "Press Update to update gradient code\nOr\nPress Delete to delete gradient code");
        if (type.equals("Update")) {
            String s = Dialog.gradientInput(this, gradient.getCode());
            if (!s.isEmpty()) {
                gradient.setCode(s);
                boolean b = service.updateGradient(gradient);
                if (b) {
                    Dialog.showInformation(this, "Update Gradient Code", "Gradient Code Updated Successfully");
                } else {
                    Dialog.showError(this, "Update Gradient Code", "Gradient Code Updated Failed");
                }
            }
        }
        if (type.equals("Delete")) {
            boolean b = service.deleteGradient(gradient);
            if (b) {
                Dialog.showInformation(this, "Delete Gradient Code", "Gradient Code Deleted Successfully");
            } else {
                Dialog.showError(this, "Delete Gradient Code", "Gradient Code Deleted Failed");
            }
        }
    }
}
