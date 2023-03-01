package sims.softwareii.softwareii.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sims.softwareii.softwareii.dao.DBUsers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LoginController implements Initializable {
    static ResourceBundle rb = ResourceBundle.getBundle("bundle/lang", Locale.getDefault());
    static TimeZone timeZone = TimeZone.getDefault();
    static String timeZoneName = TimeZone.getDefault().getID();

    @FXML
    Label title, prompt, errorMessageLabel, timezoneLabel, timezoneDisplay, usernameLabel, passwordLabel;
    @FXML
    Button submitButton, quitButton;
    @FXML
    TextField usernameTextField, passwordTextField;


    /**
     * A method that will determine if all fields are filled out, and if so it will query the database to see if a matching user can be found. If there is one, the user will be sent to the main menu, otherwise an error will display.
     *
     * @param e
     */
    @FXML
    public void onClickSubmit(ActionEvent e) {
        String userName = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (userName.isBlank() || password.isBlank()) {
            errorMessageLabel.setText(rb.getString("errorMissingFields"));
        } else {
            boolean userMatches = DBUsers.validateUser(userName, password);
            if (userMatches) {
                System.out.println("Access Granted");
            } else {
                errorMessageLabel.setText(rb.getString("errorBadData"));
            }
        }
    }

    /**
     * A method that clears any error messaging data upon changing text.
     */
    public void onEnteringDataClearWarnings() {
        errorMessageLabel.setText("");
    }

    /**
     * LAMBDA FUNCTION: the ifPresent() method on the Alert class takes a function as an argument, allowing for a lambda expression to be passed in. This can avoid an extra, unnecessary layer of abstraction by creating an anonymous function to be run on its value.
     * Allows a user to shut down the Scheduling application. The text's language is set by their location.
     */
    @FXML
    public void onClickQuitButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(rb.getString("quitTitle"));
        alert.setContentText(rb.getString("quitAlert"));
        alert.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) SceneController.exitAllScenes();
        });

    }


    /**
     * Sets the various labels, buttons, and other displays to either English or French depending on the user's location.
     */
    @FXML
    private void setLabels() {
        title.setText(rb.getString("title"));
        prompt.setText(rb.getString("prompt"));
        usernameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
        submitButton.setText(rb.getString("submit"));
        quitButton.setText(rb.getString("quit"));
        timezoneLabel.setText(rb.getString("timezoneLabel"));
        timezoneDisplay.setText(timeZoneName);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabels();
    }

    public static void main(String[] args) {
        System.out.println(timeZoneName);
        System.out.println(rb.getString("username"));
        System.out.println(rb.getString("error"));
    }
}
