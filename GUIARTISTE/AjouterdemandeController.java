/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIARTISTE;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import models.Categorie;
import models.offreTravail;
import service.CategoryService;
import service.offreTravailService;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import models.Logged;
import models.demandeTravail;
import service.demandeTravailService;


/**
 * FXML Controller class
 *
 * @author nour2
 */
public class AjouterdemandeController implements Initializable {
    demandeTravailService offs= new   demandeTravailService();
    demandeTravail of = new demandeTravail();
CategoryService c = new CategoryService();
    @FXML
    private ChoiceBox<Categorie> listeCategorie= new ChoiceBox<>();;
   
    @FXML
    private TextField titreOffre;
    @FXML
    private TextArea descriptionOffre;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loaddata();
    }    
     public void loaddata(){
       listeCategorie.getItems().addAll(c.fetchCategories());
      
    }

    @FXML
    private void ajouterOffre(ActionEvent event) {
     

if (titreOffre.getText().matches("\\d+")) {
    Alert alert = new Alert(Alert.AlertType.ERROR, "veuiller entre un titre valide");
        alert.showAndWait();}
        else if (descriptionOffre.getText().matches("\\d+"))
                { Alert alert = new Alert(Alert.AlertType.ERROR, "veuiller entrer une dscription valide");
        alert.showAndWait();}
else {
    // Le contenu du TextField n'est pas un entier

        of.setTitreDemande(titreOffre.getText());
       
        of.setDescriptionDemande(descriptionOffre.getText());
        of.setCategorieDemande(listeCategorie.getValue());
       
       /*getfrom token */ of.setIdArtiste(Logged.get_instance().getUser().getID_User());
      
           offs.addDemande(of);
            
   
    
String myVariable = "";
descriptionOffre.setText(myVariable);
	//titreOffre.setText(myVariable);
   listeCategorie.setValue(null);
  
           
    }
    }
}
