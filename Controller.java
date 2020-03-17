package com.algoritmos;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Controller {
    private TreeMap<String, String> dictionary = new TreeMap<>();
    private Scanner scanner = new Scanner(System.in);
    public void init(){
        fillDictionary();
        System.out.println("Ingrese el texto que desea traducir:");
        String textToTranslate = scanner.nextLine();
        System.out.println("Su resultado final es: \n");
        System.out.println(traduce(textToTranslate));
    }


    public String traduce(String textToTranslate){
        var list = textToTranslate.split(" ");
        StringBuilder finalResult =  new StringBuilder();
        for (String s : list) {
            if (hasKey(dictionary, s)) finalResult.append(dictionary.get(s.toLowerCase()));
            else finalResult.append("*").append(s).append("*");
            finalResult.append(" ");
        }
        return finalResult.toString();

    }
    private boolean hasKey(Map<String, String> map, String key){
        AtomicBoolean has = new AtomicBoolean(false);
        map.keySet().forEach(keyValue -> {
            if(key.equalsIgnoreCase(keyValue))
                has.set(true);
        });
        return has.getPlain();
    }
    private void fillDictionary(){
        boolean invalidName = true;
        String fileName;
        while (invalidName){
            try {
                System.out.println("Ingrese el nombre del archivo recuerde agregar la extension .txt:");
                fileName = scanner.nextLine();
                var mainPath = Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                if (getOsName().startsWith("Windows")){
                    if(String.valueOf(mainPath.charAt(0)).equals("/")) { mainPath = mainPath.substring(1, mainPath.length());}
                }
                List<String> strings = Files.readAllLines(Path.of(mainPath + fileName));
                for (String line:
                        strings) {
                    String firstLetter = String.valueOf(line.charAt(0));
                    line = firstLetter.equalsIgnoreCase("(") ? line.substring(1) : line;
                    String lastLetter = String.valueOf(line.charAt(line.length() - 1));
                    line = lastLetter.equalsIgnoreCase(")") ? line.substring(0, line.length() -1) : line;
                    String finalLine = line;
                    var holder = new ArrayList<String>(){{ addAll(List.of(finalLine.split(", ")));}};
                    var english = holder.get(0);
                    var spanish = holder.get(1);
                    dictionary.put(english.toLowerCase(), spanish);
                }
                invalidName = false;

            } catch(URISyntaxException | IOException e){
                System.out.println("Revise bien que su archivo txt exista");
            }
        }

    }
    public void fillDictionary(String fileName){
            try {
                var mainPath = Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                if (getOsName().startsWith("Windows")){
                    if(String.valueOf(mainPath.charAt(0)).equals("/")) { mainPath = mainPath.substring(1, mainPath.length());}
                }
                List<String> strings = Files.readAllLines(Path.of(mainPath + fileName));
                for (String line:
                        strings) {
                    String firstLetter = String.valueOf(line.charAt(0));
                    line = firstLetter.equalsIgnoreCase("(") ? line.substring(1) : line;
                    String lastLetter = String.valueOf(line.charAt(line.length() - 1));
                    line = lastLetter.equalsIgnoreCase(")") ? line.substring(0, line.length() -1) : line;
                    String finalLine = line;
                    var holder = new ArrayList<String>(){{ addAll(List.of(finalLine.split(", ")));}};
                    var english = holder.get(0);
                    var spanish = holder.get(1);
                    dictionary.put(english.toLowerCase(), spanish);
                }
            } catch(URISyntaxException | IOException e){
                System.out.println("Revise bien que su archivo txt exista");
            }
        }


    private  String getOsName()
    {
        return System.getProperty("os.name");
    }
}


