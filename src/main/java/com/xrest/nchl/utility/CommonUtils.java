package com.xrest.nchl.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class CommonUtils {
    public static String getPath(MultipartFile file, String paths, String fileName) throws IOException {

        String os = System.getProperty("os.name").toLowerCase();
        String root = "";
        if (os.contains("windows")) {
            root = "C:/";
        }
        byte[] bytes = file.getBytes();
        String dir = (("images/" + paths + "/"));
        Path path = Paths.get(root + dir);
        if (!Files.exists(path)) {
            new File(dir).mkdirs();
        }
        dir = dir.concat("/").concat(fileName).concat(file.getOriginalFilename());
        path = Paths.get(root + dir);
        Files.write(path, bytes);
        return dir;
    }
}
