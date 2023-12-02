package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Feedback extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Feedback Form");

        Button submitButton = new Button("Submit Feedback");
        submitButton.setOnAction(e -> openFeedbackDialog());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(submitButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openFeedbackDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("User Feedback");

        TextArea feedbackTextArea = new TextArea();
        feedbackTextArea.setPromptText("Provide your feedback here");
        feedbackTextArea.setPrefSize(250, 100);

        Button closeButton = new Button("Submit");
        closeButton.setOnAction(e -> dialog.close());

        VBox dialogVBox = new VBox(20);
        dialogVBox.getChildren().addAll(new Label("Feedback:"), feedbackTextArea, closeButton);
        dialogVBox.setAlignment(Pos.CENTER);

        Scene dialogScene = new Scene(dialogVBox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }
}
