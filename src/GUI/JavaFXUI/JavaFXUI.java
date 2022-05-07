package GUI.JavaFXUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXUI extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Teszt");
        Button teszt_bt = new Button();
        teszt_bt.setText("Click me");
        teszt_bt.setOnAction(event -> System.out.println("Hello World!"));

        StackPane root = new StackPane();
        root.getChildren().add(teszt_bt);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
