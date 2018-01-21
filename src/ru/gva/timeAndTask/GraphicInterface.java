package ru.gva.timeAndTask;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphicInterface extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        Button button = new Button();
        Button button1 = new Button();

        Map<String, Boolean> arrTasks = new HashMap();

        button.setTranslateY(40);
        button.setTranslateX(75);

        button.setText("Open");
        button.setOnAction(event -> {
            FileChooser fileChooser  = new FileChooser();
            fileChooser.setTitle("Open file");
            File file = fileChooser.showOpenDialog(primaryStage);
//            System.out.println(file.getPath());
            try {
                arrTasks.put(keyGen(file.getPath()),new ArrayList<>().addAll(Files.readAllLines(Paths.get(file.getPath()))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        button1.setText("Start");
        button1.setOnAction(event -> {
            String[] keyName = arrTasks.keySet().toArray(new String[0]);
        });

        root.getChildren().add(button);
        primaryStage.setScene(new Scene(root,200,200));
        primaryStage.show();
    }

    private String keyGen(String path) {
        Pattern nameFileAndExp = Pattern.compile("\\w+\\..+$");
        Matcher mNameFileAndExp = nameFileAndExp.matcher(path);

        if (mNameFileAndExp.find()){
            return mNameFileAndExp.group();
        }
        return "key";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
