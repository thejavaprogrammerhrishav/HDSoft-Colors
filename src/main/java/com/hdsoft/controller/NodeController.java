package com.hdsoft.controller;

import com.hdsoft.App;
import com.hdsoft.model.Color;
import com.hdsoft.model.Gradient;
import com.hdsoft.ui.UI;
import com.hdsoft.utils.Dialog;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class NodeController extends JFXButton {

    private Gradient gradient;
    private Color color;
    private GradientController gradientController;
    private ColorController colorController;
    private Clipboard board;

    public NodeController(Gradient gradient,GradientController gradientController) {
        this(gradient,null,gradientController,null);
    }

    public NodeController(Color color,ColorController colorController) {
        this(null,color,null,colorController);
    }

    public NodeController(Gradient gradient, Color color,GradientController gradientController,ColorController colorController) {
        this.gradient = gradient;
        this.color = color;
        this.gradientController = gradientController;
        this.colorController = colorController;

        FXMLLoader fxml= UI.getNode();
        fxml.setController(this);
        fxml.setRoot(this);

        try {
            fxml.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize(){
        if(gradient!=null){
            setStyle("-fx-background-color: "+gradient.getCode()+";");
        }
        if(color!=null){
            setStyle("-fx-background-color: "+color.getColor()+";");
        }

        setOnMouseClicked(this::mouseClicked);
    }

    private void mouseClicked(MouseEvent e) {
        board=Clipboard.getSystemClipboard();
        Map<DataFormat, Object> data=new HashMap<>();
        if(e.getClickCount()==1) {
            if (gradient != null) {
                data.put(DataFormat.PLAIN_TEXT,gradient.getCode());
            }
            if (color != null) {
                data.put(DataFormat.PLAIN_TEXT,color.getColor());
            }
            board.setContent(data);
        }else if(e.getClickCount()==2){
            if (gradient != null) {
                gradientController.updateOrDelete(gradient);
            }
            if (color != null) {
                colorController.updateOrDelete(color);
            }
        }else{
            Dialog.showError(getParent(),"Invalid Click Count","Click 1 time to copy code\nClick 2 times for update and delete");
        }

    }
}
