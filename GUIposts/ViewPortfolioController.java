package GUIposts;

import Entity.Post;
import Entity.PostLike;
import Interfaces.PostInterface;
import Service.PostService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Logged;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class ViewPortfolioController implements Initializable {

     @FXML
    private VBox tfpostlist;
    private PostService postService;

    public ViewPortfolioController() {
        postService = new PostService();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
       //List<Post> posts = postService.fetchPortfolioPostDetails();
          List<Post> posts = postService.fetchPortfolioPostDetailsOfThePortfolioCreater();
        for (Post post : posts) {
            VBox postBox = new VBox();
            postBox.setStyle("-fx-border-color: black; -fx-padding: 5px;");
            Label titleLabel = new Label("Title: " + post.getTitle());
            Label descriptionLabel = new Label("Description: " + post.getDescription_p());
            // Load the image from the file system based on the file path stored in the database
            String mediaPath = post.getMedia();
            if (mediaPath != null && !mediaPath.isEmpty()) {
                File mediaFile = new File(mediaPath);
                if (mediaFile.exists() && mediaFile.isFile()) {
                    ImageView mediaView = new ImageView(new Image(mediaFile.toURI().toString()));
                    mediaView.setFitWidth(400); // set the width of the image view as needed
                    mediaView.setFitHeight(400); // set the height of the image view as needed
                    postBox.getChildren().add(mediaView);
                } else {
                    Label mediaLabel = new Label("Media: " + mediaPath);
                    postBox.getChildren().add(mediaLabel);
                }
            }

            HBox titleBox = new HBox();
            titleBox.getChildren().addAll(titleLabel);
            postBox.getChildren().add(titleBox);

            HBox descriptionBox = new HBox();
            descriptionBox.getChildren().addAll(descriptionLabel);
            postBox.getChildren().add(descriptionBox);

            // Get the number of likes for this post
            List<PostLike> likes = postService.Number_Of_Likes_For_A_Post_Post(post.getId_post());
            int numberOfLikes = likes.size();
            Label likesLabel = new Label("Likes: " + numberOfLikes); // create a Label object to display the number of likes
            postBox.getChildren().add(likesLabel);


            Button button1 = new Button("View Comments");
            
            button1.setLayoutX(1039.0);
            button1.setLayoutY(612.0);
            button1.setPrefHeight(46.0);
            button1.setPrefWidth(202.0);
            button1.setStyle("-fx-background-color: C10C99;");
            button1.setTextFill(Color.WHITE);
            
        button1.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUIposts/VueCommentPost.fxml"));
                Parent root = loader.load();
                VueCommentPostController controller = loader.getController();
                controller.setId_post(post.getId_post()); // set the postId in the VueCommentPostController instance

                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();         
            } catch (IOException e)  {
                e.printStackTrace();
            }
        });







//            Button button2 = new Button("like");
//            button2.setOnAction((ActionEvent e) -> {
//                Post p = new Post();
//                p.setId_post(post.getId_post());
//                p.setId_user(1); // set the user ID of the user who liked the post  
//                postService.addLike(p); // call the addLike method to add the like
//                // update the label displaying the number of likes
//                //List<PostLike> likes = postService.Number_Of_Likes_For_A_Post_Post(post.getId_post());
//               // int numberOfLikes = likes.size();
//                likesLabel.setText("Likes: " + numberOfLikes);
//            });
//            Button button3 = new Button("Unlike");
//            button3.setOnAction((ActionEvent e) -> {
//                Post p = new Post();
//                p.setId_post(post.getId_post());
//                p.setId_user(1); // set the user ID of the user who unliked the post
//                postService.deleteLike(p.getId_post(), p.getId_user()); // call the deleteLike method to remove the like
//                // update the label displaying the number of likes
//                //List<PostLike> likes = postService.Number_Of_Likes_For_A_Post_Post(post.getId_post());
//                //int numberOfLikes = likes.size();
//                likesLabel.setText("Likes: " + numberOfLikes);
//            });
                
             Button deletePostButton = new Button("Delete Post");
             
             deletePostButton.setLayoutX(1039.0);
            deletePostButton.setLayoutY(612.0);
            deletePostButton.setPrefHeight(46.0);
            deletePostButton.setPrefWidth(202.0);
            deletePostButton.setStyle("-fx-background-color: C10C99;");
            deletePostButton.setTextFill(Color.WHITE);
             
             deletePostButton.setOnAction(event -> {        
                Post p = new Post();
                p.setTitle(post.getTitle());
                String title = post.getTitle();
                int CreaterUserId = postService.getIdUserByTitle(title);
                if (CreaterUserId == Logged.get_instance().getUser().getID_User()) {
                    postService.deletePost(post.getId_post()); // call the deletePost method to delete the post
                    tfpostlist.getChildren().remove(postBox); // remove the VBox representing the deleted post from the main VBox
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setHeaderText("Delete Post");
                    alert.setContentText("You can't delete the post, you're not the creator!");
                    alert.showAndWait();
                }
            });

     
            
            
            
                postBox.getChildren().add(deletePostButton);
                

            Button ModifyPostButton = new Button("Modify Post");
            ModifyPostButton.setLayoutX(1039.0);
            ModifyPostButton.setLayoutY(612.0);
            ModifyPostButton.setPrefHeight(46.0);
            ModifyPostButton.setPrefWidth(202.0);
            ModifyPostButton.setStyle("-fx-background-color: C10C99;");
            ModifyPostButton.setTextFill(Color.WHITE);
             
            
            ModifyPostButton.setOnAction(event -> {
                Post p = new Post();
                // p.setId_post(post.getId_post()
              // p.setId_user(currentUserId);
              //p.setId_post(post.getId_post())
              p.setTitle(post.getTitle());
              p.setId_user( post.getId_user());
              
             String title = post.getTitle();
               
                System.out.println("hey" + post.getId_user());
                System.out.println("id " + postService.getIdUserByTitle(title));
                int CreaterUserId =postService.getIdUserByTitle(title);
                if ((CreaterUserId == Logged.get_instance().getUser().getID_User())){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUIposts/ModifyPost.fxml"));
                    Parent modifyPostView = loader.load();
                    Scene scene = new Scene(modifyPostView);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    ModifyPostController modifyPostController = loader.getController();

                    // Pass the selected post object to the ModifyPostController
                    modifyPostController.initData(post);
                    
                    
                } 
                
                catch (IOException e) {
                    e.printStackTrace();
                }
                 //ModifyPostButton.setVisible(true);
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Modify Post");
                    alert.setContentText("You can't Modify the post, you're not the creator!");
                    alert.showAndWait();
                }
            });
            // Add the ModifyPostButton button to the postBox
            
            
            
            
            
            
            
            
            
            
            
            postBox.getChildren().add(ModifyPostButton);
           
//            if (currentUserId == post.getId_user()){
//                ModifyPostButton.setVisible(true);
//            }else{
//                ModifyPostButton.setVisible(false);
//            }






            HBox buttonBox = new HBox();
            buttonBox.getChildren().addAll( button1,ModifyPostButton,deletePostButton);

            buttonBox.setSpacing(10); // Set the spacing between the buttons
            

            VBox likesBox = new VBox();
            likesBox.getChildren().addAll(likesLabel, buttonBox);
            postBox.getChildren().add(likesBox);

            tfpostlist.getChildren().add(postBox);
        }
    }
    
    
       public void handleAddComment(ActionEvent event) {
    try {
        // Load the AddAComment.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/AddAComment.fxml"));
        Parent root = loader.load();

        // Create a new scene and display it in a new window
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        // Handle the exception here
        e.printStackTrace();
    }
}
       public void viewAddPostPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Post.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 public void viewModifyPostPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ModifyPost.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
               public void viewDeletePostPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("DeletePost.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        public void viewBlogPostPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ViewBlog.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
            public void viewPostControllePanel(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("PostControlPanel.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
              public void viewExplore(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Explore.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}