package edu.bsuir.model;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage primaryStage;
    private static BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initRootLayout("/authorization.fxml");

    }

    public static void initRootLayout(String pathTofxml) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(pathTofxml));
            rootLayout = (BorderPane)  loader.load();

            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[]args){
        launch(args);
    }


}

