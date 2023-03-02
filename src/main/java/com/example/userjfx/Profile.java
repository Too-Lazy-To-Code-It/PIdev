package com.example.userjfx;

import Models.AllUsers;
import Models.Logged;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Profile {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView Avatar;

    @FXML
    private ImageView Background;



    @FXML
    void initialize() {
        assert Avatar != null : "fx:id=\"Avatar\" was not injected: check your FXML file 'Profile.fxml'.";
        assert Background != null : "fx:id=\"Background\" was not injected: check your FXML file 'Profile.fxml'.";

        AllUsers user = Logged.get_instance().getUser();
        if (user != null) {
            System.out.println(user.getNickname());
            System.out.println(user);
            System.out.println(user.getAvatar());
            System.out.println(user.getBackground());

            String imagePath = "C:/xampp/htdocs/uploads/"+user.getAvatar();
            try (InputStream avatarStream = new FileInputStream(imagePath)) {
                Image avatarImage = new Image(avatarStream);
                Avatar.setImage(avatarImage);
                Avatar.setPreserveRatio(false);
                Avatar.setFitWidth(100);
                Avatar.setFitHeight(100);

            } catch (IOException e) {
                System.err.println("Error loading avatar image: " + e.getMessage());
            }

            String imagePath1 = "C:/xampp/htdocs/uploads/"+user.getBackground();
            try (InputStream backgroundStream = new FileInputStream(imagePath1)) {
                Image backgroundImage = new Image(backgroundStream);
                Background.setImage(backgroundImage);
                Background.setPreserveRatio(false);
                Background.setFitWidth(1386.0);
                Background.setFitHeight(320.0);

            } catch (IOException e) {
                System.err.println("Error loading background image: " + e.getMessage());
            }
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

}

