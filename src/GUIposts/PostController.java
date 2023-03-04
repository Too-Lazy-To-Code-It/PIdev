package GUIposts;


import interfaces.CategoryInterface;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;


import Entity.Category;
import Entity.Post;

import Interfaces.PostInterface;

import Service.PostService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.ComboBox;

import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
//import javafx.stage.File

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import models.Categorie;
import service.CategoryService;
public class PostController implements Initializable {
    private PostService postService;
    CategoryInterface cat = new CategoryService();
    Post p = new Post();
    Categorie c = new Categorie();
    PostInterface st = new PostService();


    @FXML
    private TextField userIdField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField postTypeField;


    @FXML
    private TextField media;
    @FXML
    private ComboBox<?> categorie;
    ObservableList list1 = FXCollections.observableArrayList();
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        postService = new PostService();
        
        // Fetch the categories from the database
        List<Category> categories = postService.fetchCategories();
        
        if (categories != null) {
            // Create menu items and add them to the category menu button
//            for (Category category : categories) {
//                MenuItem menuItem = new CheckMenuItem(category.getName_category());
//                categoryMenuButton.getItems().add(menuItem);
//            }
            
         cat.fetchCategories().stream().forEach(e->list1.add(e.getNomCategorie()));
         
        categorie.getItems().addAll(list1);
        
    }
  }
    
    @FXML
    public void ADDPost(ActionEvent event) throws IOException{
        if (userIdField.getText().isEmpty() || titleField.getText().isEmpty() ||
        descriptionField.getText().isEmpty() || postTypeField.getText().isEmpty() ||
        media.getText().isEmpty() || categorie.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Missing fields");
        alert.setContentText("Please enter a comment and a user ID.");
        alert.showAndWait();
        return;
        }
        p.setId_user(Integer.parseInt(userIdField.getText()));
        p.setTitle(titleField.getText());
        p.setDescription_p(descriptionField.getText());
        p.setPost_typ(postTypeField.getText());
        p.setMedia(media.getText());
//        c.setId_Category(Integer.parseInt(IDCategory.getText()));
//        c.setName_category(NameCategory.getText());
        st.addPost(p);
      
        
     
       
//        c.setName_categorie(name_categorie.getText());
//System.out.println(p);
    }
    

        @FXML
         void setCat(ActionEvent event) {
          /*   p.setCategory_p(cat.fetchCategories((String) categorie.getValue()));*/
        }
    
         
//            @FXML
//   private void handleUploadPhoto(ActionEvent event) throws FileNotFoundException, IOException {
//       FileChooser fc = new FileChooser();
//       fc.getExtensionFilters().addAll(
//           new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
//       File selectedFile = fc.showOpenDialog(null);
//       if (selectedFile != null) {
//           String DBPath = "C:\\\\xampp\\\\htdocs\\\\img\\\\" + selectedFile.getName();
//           BufferedImage bufferedImage = ImageIO.read(selectedFile);
//           WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
//           ImageIO.write(bufferedImage, "jpg", new File(DBPath));
//           media.setText(selectedFile.getAbsolutePath());
//           FileInputStream fin = new FileInputStream(selectedFile);
//           ByteArrayOutputStream bos = new ByteArrayOutputStream();
//           byte[] buf = new byte[1024];
//           for (int readNum; (readNum = fin.read(buf)) != -1;) {
//               bos.write(buf, 0, readNum);
//           }
//           byte[] post_image = bos.toByteArray();
//       } else {
//           System.err.println("file is not valid");
//       }
//   }
         @FXML
private void handleUploadPhoto(ActionEvent event) throws FileNotFoundException, IOException {
    FileChooser fc = new FileChooser();
    fc.getExtensionFilters().addAll(
            new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
    File selectedFile = fc.showOpenDialog(null);
    if (selectedFile != null) {
        String DBPath = "C:\\\\xampp\\\\htdocs\\\\img\\\\" + selectedFile.getName();
        BufferedImage bufferedImage = ImageIO.read(selectedFile);
        WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
        ImageIO.write(bufferedImage, "jpg", new File(DBPath));
        media.setText(DBPath);
        FileInputStream fin = new FileInputStream(selectedFile);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int readNum; (readNum = fin.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }
        byte[] post_image = bos.toByteArray();
    } else {
        System.err.println("file is not valid");
    }
}
   
    @FXML
     public void handleReturn(ActionEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("/Gui/PostControlPanel.fxml"));
     Scene scene = new Scene(root);
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     stage.setScene(scene);
     stage.show();
 }
   
         
}
