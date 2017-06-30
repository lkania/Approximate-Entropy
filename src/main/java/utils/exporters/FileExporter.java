package utils.exporters;

import java.io.FileWriter;
import java.io.IOException;

public class FileExporter {

    public static void export(String content, String path) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.append(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("File doesn't exists");
        }

    }
}
