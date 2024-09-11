package com.kagoji.atfarestfulapis.logger;

import org.springframework.stereotype.Component;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CustomLogger {

    private static final String LOG_DIR = "src/main/resources/logs";

    public boolean customLogWrite(String logDirName, String fileTypeName, String message) {

        Path logDirPath = Paths.get(LOG_DIR, logDirName);
        // Create directories if not exist
        if (Files.notExists(logDirPath)) {
            try {
                Files.createDirectories(logDirPath);
                System.out.println("LOG DIR Created: " + logDirPath);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        System.out.println("LOG DIR: " + logDirPath);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
        String logFileName = fileTypeName + "_" + dateFormat.format(new Date()) + ".txt";

        Path logFilePath = logDirPath.resolve(logFileName);

        try (FileWriter writer = new FileWriter(logFilePath.toFile(), true)) {
            SimpleDateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String timestamp = timeFormat.format(new Date());

            String logMessage = timestamp + " - " + fileTypeName + " --> " + message + System.lineSeparator();
            writer.write(logMessage);
            System.out.println("Log Message: " + logMessage);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

