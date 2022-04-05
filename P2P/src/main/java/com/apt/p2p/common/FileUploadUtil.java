package com.apt.p2p.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUploadUtil {
    public static String saveFile(String uploadDir, String fileName,
                                  MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return null;
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            String extensionName = getExtensionName(multipartFile).orElse(null);
            fileName += '.' + extensionName;
            File folder = ResourceUtils.getFile("src/main/assets/" + uploadDir);
            Path uploadPath = Paths.get(folder.getPath());

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // delete if exist same name but difference extension
            List<Path> existFilePath = findFiles(fileName, uploadDir);

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Could not save image file: " + fileName);
            return null;
        }

        return fileName;
    }

    public static Optional<String> getExtensionName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1).toLowerCase());
    }

    public static void deleteFile(String uploadDir, String fileName) throws IOException {
        File folder = ResourceUtils.getFile("src/main/assets/" + uploadDir);
        Path folderPath = Paths.get(folder.getPath());
        Path deleteFilePath = folderPath.resolve(fileName);
        Files.delete(deleteFilePath);
    }

    public static List<Path> findFiles(String fileName, String searchDirectory) throws IOException {
        searchDirectory = "src/main/assets/" + searchDirectory;
        try (Stream<Path> files = Files.walk(Paths.get(searchDirectory))) {
            return files
                    .filter(f -> StringProcessForView.removeExtensionFilename(f.getFileName().toString()).equals(fileName))
                    .collect(Collectors.toList());
        }
    }
}
