package com.hdsoft.utils;

import java.util.Optional;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import static javafx.stage.Modality.WINDOW_MODAL;

public class Dialog {
    public static void showInformation(Parent parent, String header, String content){
        alert(parent, header, content, INFORMATION);
    }

    public static void showError(Parent parent, String header, String content){
        alert(parent, header, content, ERROR);
    }

    public static String showConfirmation(Parent parent, String header, String content){
        return alert(parent,header,content,CONFIRMATION).map(ButtonType::getText).orElse("Other");
    }

    private static Optional<ButtonType> alert(Parent parent, String header, String content, AlertType type) {
        Alert alert=new Alert(type);
        alert.initModality(WINDOW_MODAL);
        alert.initOwner(parent.getScene().getWindow());
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setTitle("HDSoft Color Code");
        alert.setResizable(false);
        if(type.equals(CONFIRMATION)){
            alert.getButtonTypes().setAll(new ButtonType("Update"),new ButtonType("Delete"),ButtonType.CANCEL);
        }
        return alert.showAndWait();
    }

    public static String colorInput(Parent parent,String content){
        TextInputDialog alert=new TextInputDialog();
        alert.initModality(WINDOW_MODAL);
        alert.initOwner(parent.getScene().getWindow());
        alert.setTitle("Add New Color Code");
        alert.setHeaderText("Enter Color Code");
        alert.setContentText(content);
        return alert.showAndWait().orElse("");
    }

    public static String gradientInput(Parent parent,String content){
        TextInputDialog alert=new TextInputDialog();
        alert.initModality(WINDOW_MODAL);
        alert.initOwner(parent.getScene().getWindow());
        alert.setTitle("Add New Gradient Code");
        alert.setHeaderText("Enter Gradient Code");
        alert.setContentText(content);
        return alert.showAndWait().orElse("");
    }
}
