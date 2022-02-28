package com.apt.p2p.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
//        Path uploadPath = Paths.get("/static/" + uploadDir);
//        ResourceUtils.getFile("classpath:static/img/auth");

//        File url = ResourceUtils.getFile("classpath:static/img/shops");
        File folder = ResourceUtils.getFile("/static/img/shops");
        Path uploadPath = Paths.get(folder.getPath());
        Path uploadPath2 = Paths.get("/static/" + uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
