package ru.gva.timeAndTask;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphicInterface extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();

        Button open = new Button();
        Button start = new Button();

        ArrayList<TextField> number = new ArrayList<>();
        ArrayList<Label> nameTextField = new ArrayList<>();

        Map<String, ArrayList<String>> arrTasks = new HashMap<>();

        open.setTranslateX(150);
        open.setTranslateY(40);

        start.setTranslateX(100);
        start.setTranslateY(40);

        open.setText("Open");
        open.setOnAction((ActionEvent event) -> {
            int lenght = 1;
            String name = "";

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("add file");

            File file = fileChooser.showOpenDialog(primaryStage);

            try {
                ArrayList<String> value = new ArrayList<>();

                value.addAll(Files.readAllLines(Paths.get(file.getPath())));
                name = MethodsForTask.keyGen(file.getPath(),arrTasks.keySet().toArray(new String[0]));
                arrTasks.put(name, value);

                lenght = arrTasks.keySet().toArray(new String[0]).length;

            } catch (IOException e) {
                e.printStackTrace();

            }

            number.add(new TextField());
            nameTextField.add(new Label(name));

            number.get(lenght-1).setText("1");

            number.get(lenght - 1).setTranslateX(60);
            number.get(lenght - 1).setTranslateY(number.get(lenght - 1).getTranslateY() + (25 * lenght - 1));

            nameTextField.get(lenght-1).setTranslateX(10);
            nameTextField.get(lenght-1).setTranslateY(number.get(lenght-1).getTranslateY());

            root.getChildren().addAll(number.get(lenght - 1),nameTextField.get(lenght-1));

            start.setTranslateY(start.getTranslateY() + 30);

            open.setTranslateY(open.getTranslateY() + 30);

        });

        start.setText("Start");

        start.setOnAction((ActionEvent event) -> {
            int[] countsOfTask = MethodsForTask.returnTextField(number);
            Map<String,ArrayList<String>> arrTasksSort = MethodsForTask.sort(countsOfTask,arrTasks);

            TextArea secondLabel = new TextArea();
            secondLabel.setText(MethodsForTask.viewMap(arrTasksSort));


            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().addAll(secondLabel);

            Scene secondScene = new Scene(secondaryLayout, 500, 500);

            // New window (Stage)
            Stage newWindow = new Stage();

            newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);

            newWindow.show();

        });
        root.getChildren().addAll(open, start);
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }

}
