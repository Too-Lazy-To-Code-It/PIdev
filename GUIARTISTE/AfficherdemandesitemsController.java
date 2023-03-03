/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIARTISTE;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import models.AllUsers;
import models.demandeTravail;

import models.offreTravail;
import service.AllUsersService;


/**
 * FXML Controller class
 *
 * @author nour2
 */
public class AfficherdemandesitemsController implements Initializable {

    @FXML
    private Label titreOffre;
    @FXML
    private Label nomStudio;
   
    @FXML
    private Label dateAjout;
    @FXML
    private Label description;
    @FXML
    private Label categorie;
    @FXML
    private Circle photo1;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void loaddata(demandeTravail f) throws SQLException{
       titreOffre.setText(f.getTitreDemande());
       nomStudio.setText(f.getNomArtiste());
       
       dateAjout.setText(f.getDateAjoutDemande().toString());
       description.setText(f.getDescriptionDemande());
          categorie.setText(f.getCategorieDemande().getNomCategorie());
             
          AllUsersService u = new  AllUsersService();
           AllUsers user = u.fetchAUbyNickname(f.getNomArtiste());
            String imagePath = "C:/xampp2/htdocs/uploads/"+user.getAvatar();
            try (InputStream avatarStream = new FileInputStream(imagePath)) {
                Image avatarImage = new Image(avatarStream);
                photo1.setFill(new ImagePattern(avatarImage));
               

            } catch (IOException e) {
                System.err.println("Error loading avatar image: " + e.getMessage());
            }
   }

   
}
