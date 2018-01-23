package ru.gva.timeAndTask;

import javafx.application.Application;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphicInterface extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        Button open = new Button();
        Button start = new Button();

        ArrayList<TextField> number = new ArrayList<>();

        Map<String, ArrayList<String>> arrTasks = new HashMap<>();

        open.setTranslateY(40);
        open.setTranslateX(75);

        open.setText("Open");
        open.setOnAction(event -> {
            int lenght=1;
//            add(number1);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("add file");
            File file = fileChooser.showOpenDialog(primaryStage);
//            System.out.println(file.getPath());
            try {
                ArrayList<String> value = new ArrayList<>();
                value.addAll(Files.readAllLines(Paths.get(file.getPath())));
                arrTasks.put(keyGen(file.getPath()), value);
                lenght = arrTasks.keySet().toArray(new String[0]).length;

            } catch (IOException e) {
                e.printStackTrace();

            }

//                root.getChildren().add(textField);
            number.add(new TextField());
            number.get(lenght-1).setTranslateX(0);
            number.get(lenght-1).setTranslateY(30+(25*lenght-1));
            root.getChildren().addAll(number.get(lenght-1));

        });

        start.setText("Start");
//        button1.setOnAction(event -> {
//            viewMap(arrTasks);

//        });
        start.setOnAction(event -> {
//            System.out.println(number.size());
            TextArea secondLabel = new TextArea();
            secondLabel.setText(viewMap(arrTasks));

            secondLabel.setText(viewMap(arrTasks));


//            System.out.println(number.get(0).getCharacters());
//            String n1 = String.valueOf(number.get(0).getCharacters());
//            int p = Integer.valueOf(n1);
//            System.out.println(p);

            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().addAll(secondLabel);

            Scene secondScene = new Scene(secondaryLayout, 500, 500);

            // New window (Stage)
            Stage newWindow = new Stage();

            newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);

            newWindow.show();
        });
//        addTextField(root,number);
        root.getChildren().addAll(open, start);
        primaryStage.setScene(new Scene(root, 200, 200));
        primaryStage.show();
    }

//    private void add(int number1) {
//        number1++;
//        System.out.println(number1);
//    }
//
//    private void addTextField(GridPane root, ArrayList<TextField> number) {
//        for (int i = 0; i < number.size(); i++) {
//            root.getChildren().addAll(number.get(i));
//            number.get(i).setTranslateX(0);
//            number.get(i).setTranslateY(90-(10*i));
//
//        }
//    }

//    private void scroll(ScrollBar scrollPane, TextArea secondLabel) {
//
//        scrollPane.valueProperty().addListener(event -> {
//           secondLabel.setTranslateY(0-scrollPane.getValue());
//
//        });
//    }

    private String viewMap(Map<String, ArrayList<String>> arrTasks) {
        StringBuilder result = new StringBuilder();
        String[] keyName = arrTasks.keySet().toArray(new String[0]);
        for (String aKeyName : keyName) {
            ArrayList<String> value = arrTasks.get(aKeyName);
            for (String aValue : value) {
                result.append(aValue).append("\n");
            }
        }
        return result.toString();
    }

    private String keyGen(String path) {
        Pattern nameFileAndExp = Pattern.compile("\\w+\\..+$");
        Matcher mNameFileAndExp = nameFileAndExp.matcher(path);

        if (mNameFileAndExp.find()) {
            return mNameFileAndExp.group();
        }
        return "key";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
