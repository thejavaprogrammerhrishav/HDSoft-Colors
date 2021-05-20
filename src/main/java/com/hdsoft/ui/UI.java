package com.hdsoft.ui;

import javafx.fxml.FXMLLoader;
import org.springframework.stereotype.Component;

public class UI {
    private static String path="/com/hdsoft/ui/resources/";

    public static FXMLLoader getDashboard(){
        return new FXMLLoader(UI.class.getResource(path+"dashboard.fxml"));
    }

    public static FXMLLoader getColors(){
        return new FXMLLoader(UI.class.getResource(path+"colors.fxml"));
    }

    public static FXMLLoader getGradients(){
        return new FXMLLoader(UI.class.getResource(path+"gradients.fxml"));
    }

    public static FXMLLoader getNode(){
        return new FXMLLoader(UI.class.getResource(path+"node.fxml"));
    }
}
