package com.example.testjavafx;

import com.example.testjavafx.src.Controller.Controller;
import com.example.testjavafx.src.Exception.MyException;
import com.example.testjavafx.src.Model.Expression.ValuExp;
import com.example.testjavafx.src.Model.Expression.VarExp;
import com.example.testjavafx.src.Model.MyAdts.*;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Statement.*;
import com.example.testjavafx.src.Model.Type.IntType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.IntValue;
import com.example.testjavafx.src.Model.Value.StringValue;
import com.example.testjavafx.src.Model.Value.Value;
import com.example.testjavafx.src.Repository.Repo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Stage anotherStage = new Stage();

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 1000, 900);

        MainController mainctrl=fxmlLoader2.getController();

        anotherStage.setTitle("Main");
        anotherStage.setScene(scene2);
        anotherStage.show();


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);

        HelloController selectWindowController = fxmlLoader.getController();
        selectWindowController.setMainWindowController(mainctrl);

        stage.setTitle("Select");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
