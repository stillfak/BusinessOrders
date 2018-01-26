package ru.gva.timeAndTask;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodsForTask {


    public static Map<String,ArrayList<String>> sort(int[] countsOfTask, Map<String, ArrayList<String>> arrTasks) {
        Map<String,ArrayList<String>> extract = new HashMap<>();
        int[] arrTask;
        String[] key = arrTasks.keySet().toArray(new String[0]);
        for (int i = 0; i < key.length; i++) {
            arrTask = new int[arrTasks.get(key[i]).size()];
            for (int j = 0; j < arrTask.length; j++) {
//                System.out.println(arrTasks.get(i).get(j));
                arrTask[j] = Integer.valueOf(arrTasks.get(key[i]).get(j));
            }
            extract.put(key[i],sortAndExtract(arrTask,countsOfTask[i]));

        }
        return extract;
    }

    private static ArrayList<String> sortAndExtract(int[] arrTask, int count) {
        ArrayList<String> extract = new ArrayList<>();
        Arrays.sort(arrTask);
        for (int i = arrTask.length-1; arrTask.length - i <= count; i--) {
            extract.add(String.valueOf(arrTask[i]));
        }
        return extract;
    }
    static int[] returnTextField(ArrayList<TextField> number) {
        int [] countOfTasks = new int[number.size()];
        for (int i = 0; i < number.size(); i++) {
            countOfTasks[i] = Integer.valueOf(String.valueOf(number.get(i).getCharacters()));
        }
        return countOfTasks;

    }

    /**
     *
     *
     * @param arrTasks
     * @return
     */
    static String viewMap(Map<String, ArrayList<String>> arrTasks) {
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

    static String keyGen(String path, String[] key) {

        Pattern nameFileAndExp = Pattern.compile("\\w+\\..+$");
        Matcher mNameFileAndExp = nameFileAndExp.matcher(path);

        if (mNameFileAndExp.find()) {

            return mNameFileAndExp.group();
        }

        return "key";
    }
}
