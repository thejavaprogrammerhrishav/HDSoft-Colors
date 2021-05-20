package com.hdsoft;

import com.hdsoft.config.Config;
import com.hdsoft.controller.Dashboard;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App extends Application {

    public static AnnotationConfigApplicationContext context;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);
        context.refresh();
        App.context = context;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(((Dashboard) context.getBean("dashboard"))));
        primaryStage.centerOnScreen();
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setTitle("HDSoft Color Codes");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
      context.close();
        Platform.exit();
    }
}
