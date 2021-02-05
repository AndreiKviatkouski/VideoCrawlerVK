package by.AndreiKviatkouski.service;

import by.AndreiKviatkouski.entyties.Video;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

public class StatisticsService {

    private PrintWriter pw;

    public void writeToFile(Path file, List<Video> list) {
        try {
            FileWriter fw = new FileWriter(String.valueOf(file));
            pw = new PrintWriter(fw);
            printSearchList(list);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }

    public void printSearchList(List<Video> list) {
        StringBuilder builder = new StringBuilder();

        list.stream()
                .sorted((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()))
                .forEach(e -> builder
                        .append("\n")
                        .append(e.getName())
                        .append(":" + "\n")
                        .append("   URL:")
                        .append(e.getUrl())
                        .append("\n")
                        .append("   Download link: ")
                        .append(e.getDownloadLink())
                        .append("\n"));

        pw.write(builder.toString());
    }

}