package com.cg.myblog.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.UUID;

public class FileHandler {

    public static String saveFile(File file, String storageFolder) throws IOException {
        // 获取文件名
        String originalFileName = file.getName();

        // 确保存储文件夹存在
        File folder = new File(storageFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 获取文件的后缀名
        String fileExtension = "";
        int i = originalFileName.lastIndexOf('.');
        if (i > 0) {
            fileExtension = originalFileName.substring(i);
        }

        // 生成UUID文件名
        String fileName = UUID.randomUUID().toString() + fileExtension;

        // 保存文件
        try (InputStream fileStream = new FileInputStream(file);
             FileOutputStream outStream = new FileOutputStream(new File(storageFolder, fileName))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        }

        return fileName;
    }
}
