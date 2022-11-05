package edu.bsuir.controllers;


import edu.bsuir.model.App;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class AuthorizationController {

    private static String LOGINASADMIN = "LOGINASLOGISTICIAN";
    private static String LOGINASEMPLOYEE = "LOGINASSTOCKMAN";


    @FXML
    private Button login_as_stockman;

    @FXML
    private Button login_as_logistician;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Label error_label;


    public AuthorizationController() throws IOException {
    }

    public void initialize(){

        login_as_stockman.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            String login = login_field.getText();
            String password = password_field.getText();
            if (password.equals("stock")) {
                App.initRootLayout("/readtasks.fxml");
            } else {
                error_label.setText("Incorrect password.");
            }
        });

        login_as_logistician.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            String login = login_field.getText();
            String password = password_field.getText();
            if (password.equals("log")) {
                App.initRootLayout("/workwithstocks.fxml");
            } else {
                error_label.setText("Incorrect password.");
            }
        });


    }

}

