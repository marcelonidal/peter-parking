package com.grupo7.peter_parking.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EnvLoader {

    public static void loadEnv(String filePath) {
        try {
            Files.lines(Path.of(filePath))
                    .filter(line -> !line.trim().isEmpty() && !line.startsWith("#"))
                    .forEach(line -> {
                        String[] parts = line.split("=", 2);
                        if (parts.length == 2) {
                            System.setProperty(parts[0].trim(), parts[1].trim());
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo .env", e);
        }
    }

}
