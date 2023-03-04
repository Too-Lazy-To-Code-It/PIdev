/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.Tutoriel;
import Models.Utilisateur;
import interfaces.CategorieInterface;
import interfaces.TutorielInterface;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.CategorieService;
import services.TutorielService;

/**
 * FXML Controller class
 *
 * @author achref
 */
public class FXML_ADD_TutorielController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextArea descrption;
    @FXML
    private Label image_name;
    @FXML
    private TextField niveau;
    @FXML
    private ComboBox<String> categories;
    ObservableList list = FXCollections.observableArrayList();
    
    CategorieInterface ci = new CategorieService();
    TutorielInterface ti = new TutorielService();
    Tutoriel tutoriel = new Tutoriel();
    Utilisateur u = new Utilisateur();
    
    String src;
    String dest;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.removeAll(list);
        ci.fetchCategorie().stream().forEach(e->list.add(e.getNameCategorie()));
        categories.getItems().addAll(list);
    }    

    @FXML
    private void importImage(ActionEvent event) throws IOException {
           FileChooser fc = new FileChooser();
           FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG Files","*.png");
           FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG Files","*.jpg");

           fc.getExtensionFilters().addAll(extFilterPNG,extFilterJPG);
           
           File selectedFile = fc.showOpenDialog(null);
           
           if(selectedFile != null) {
               src = selectedFile.getPath();
               dest = "C:\\Users\\achref\\Documents\\NetBeansProjects\\JavaApplication1\\src\\img\\"+selectedFile.getName();
               image_name.setText(selectedFile.getName());
               tutoriel.setPathImg("/img/"+selectedFile.getName());
           } else {
               System.err.println("file is not valid");
           }
    }

    @FXML
    private void addChallenge(ActionEvent event) throws IOException {
                if(titre.getText().length()==0||descrption.getText().length()==0||niveau.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Veuillez  remplir tous les champs");
            alert.show();
            return;
        }
        else if(!niveau.getText().matches("[0-9]")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Veuillez  remplir tous les champs"+ "");
            alert.show();
            return;
        }
        else{
            Files.move(Paths.get(src), Paths.get(dest)); 
            tutoriel.setTitle(titre.getText());
            tutoriel.setDescription(descrption.getText());
            tutoriel.setCategorie(ci.fetchCategorieByName(categories.getValue()));
            tutoriel.setCreator(new Utilisateur());
            tutoriel.setNiveau(Integer.parseInt(niveau.getText()));
            u.setID_user(1);
            tutoriel.setCreator(u);
            ti.addTutoriel(tutoriel);
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./FXML_Fetch_Tutoriels.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
}
    
