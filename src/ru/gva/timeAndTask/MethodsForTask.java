package ru.gva.timeAndTask;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodsForTask {


    public static Map<String, ArrayList<String>> sort(int[] countsOfTask, Map<String, ArrayList<String>> arrTasks) {
        Map<String, ArrayList<String>> extract = new HashMap<>();
        int[] arrTask;
        String[] key = arrTasks.keySet().toArray(new String[0]);
        for (int i = 0; i < key.length; i++) {
            arrTask = new int[arrTasks.get(key[i]).size()];
            for (int j = 0; j < arrTask.length; j++) {
//                System.out.println(arrTasks.get(i).get(j));
                arrTask[j] = Integer.valueOf(arrTasks.get(key[i]).get(j));
            }
            extract.put(key[i], sortAndExtract(arrTask, countsOfTask[i]));

        }
        return extract;
    }

    private static ArrayList<String> sortAndExtract(int[] arrTask, int count) {
        ArrayList<String> extract = new ArrayList<>();
        Arrays.sort(arrTask);
        for (int i = arrTask.length - 1; arrTask.length - i <= count; i--) {
            extract.add(String.valueOf(arrTask[i]));
        }
        return extract;
    }

    static int[] returnTextField(ArrayList<TextField> number) {
        int[] countOfTasks = new int[number.size()];
        for (int i = 0; i < number.size(); i++) {
            countOfTasks[i] = Integer.valueOf(String.valueOf(number.get(i).getCharacters()));
        }
        return countOfTasks;

    }

    /**
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
        String result = "";

        Pattern nameFileAndExp = Pattern.compile("\\w+\\..+$");
        Matcher mNameFileAndExp = nameFileAndExp.matcher(path);

        if (mNameFileAndExp.find()) {
            result = mNameFileAndExp.group();
            if (result.length() > 7) {
                result = littleKey(result);
            }
            for (int i = 0; check(result, key); i++) {
                result += String.valueOf(i);
            }
        }

        return result;
    }

    private static String littleKey(String result) {
        String lKey = "";
        for (int i = 0; i < 4; i++) {
            lKey += result.charAt(i);

        }
        return lKey;
    }

    private static boolean check(String result, String[] key) {
        Pattern repeat = Pattern.compile(result);
        Matcher mRepeat;
        for (int i = 0; i < key.length; i++) {
            mRepeat = repeat.matcher(key[i]);
            if (mRepeat.find()) {
                return true;
            }
        }
        return false;
    }
}
