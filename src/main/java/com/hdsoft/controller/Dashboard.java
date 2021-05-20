package com.hdsoft.controller;

import com.hdsoft.ui.UI;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dashboard")
public class Dashboard extends AnchorPane {

    @FXML
    private JFXButton gradient;

    @FXML
    private JFXButton color;

    @FXML
    private ScrollPane pane;


    @Autowired
    private ColorController colorController;

    @Autowired
    private GradientController gradientController;

    public Dashboard() {
        FXMLLoader fxml = UI.getDashboard();
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
        CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(500);
                Platform.runLater(()->pane.setContent(colorController));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        gradient.setOnAction(this::gradient);
        color.setOnAction(this::color);
    }

    private void color(ActionEvent event) {
        pane.setContent(colorController);
    }

    private void gradient(ActionEvent event) {
        pane.setContent(gradientController);
    }

    public void setColorController(ColorController colorController) {
        this.colorController = colorController;
    }

    public void setGradientController(GradientController gradientController) {
        this.gradientController = gradientController;
    }
}
