package com.java;

import java.util.ArrayList;
import java.util.Properties;
import java.io.*;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        String configFileInput = "resources/person.properties";
        String configFileOutput = "out/person.html";
        Properties prop = new Properties();
        try {
            prop.load(new BufferedReader(new InputStreamReader(new FileInputStream(configFileInput), "UTF-8")));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        StringBuilder str = new StringBuilder("        <h4><strong>");
        str.append("</strong></h4>\n");
        String target = prop.getProperty("target");
        String[] strMas = target.split("\",\"");

        ArrayList <String> list = new ArrayList<>();
        for (int i=0; i<strMas.length; i++)
        {
            //System.out.println("values["+i+"]"+values[i]);
            if (i == 0)
                list.add(strMas[i].substring(1));
            else if (i == strMas.length - 1)
                list.add(strMas[i].substring(0, strMas[i].length() - 1));
            else
                list.add(strMas[i]);
        }
        for (String x : list) {
            System.out.println("list[i] = " + x);
        }

        str.append("<ol>\n");
        for (String x : list) {
            str.append("<li>");
            str.append(x);
            str.append("</li>");
        }
        str.append("</ol>\n");

        String content = "<!DOCTYPE html>\n"
                + "<html lang=\"ru\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "    <title>Пример работы с ArrayList</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <h2 class=\"text-center\"><strong>Резюме</strong></h2>\n"
                + "        <h2 class=\"card-title\"><strong>Цель:</strong></h2>\n"
                + "        <p class=\"card-text\">" + str + "</p>\n"
                + "    </div>\n"
                + "</body>"
                + "</html>";

        try {
            File file = new File(configFileOutput);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter (configFileOutput, false);
            writer.write(content);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
