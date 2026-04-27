package com.fileprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;

public class FilePathProcessor {
    private static final Logger log = LoggerFactory.getLogger(FilePathProcessor.class);

    /**
     * PHIÊN BẢN CÓ LỖI - Dùng đường dẫn cứng kiểu Windows
     * Chỉ chạy được trên Windows, sẽ FAIL trên Linux/Mac
     */
    public String buildWindowsPath(String folder, String filename) {
        // ⚠️ LỖI CỐ Ý: Dùng backslash cứng (chỉ hoạt động trên Windows)
        String path = folder + "\\" + filename;
        log.info("Built Windows path: {}", path);
        return path;
    }

    /**
     * PHIÊN BẢN CÓ LỖI - Dùng đường dẫn cứng kiểu Linux
     * Chỉ chạy được trên Linux/Mac, sẽ FAIL trên Windows
     */
    public String buildLinuxPath(String folder, String filename) {
        // ⚠️ LỖI CỐ Ý: Dùng forward slash cứng (Linux/Mac)
        String path = folder + "/" + filename;
        log.info("Built Linux path: {}", path);
        return path;
    }

    /**
     * Kiểm tra file có tồn tại không - dùng đường dẫn cứng Windows
     */
    public boolean checkFileExistsWindows(String folder, String filename) {
        String path = folder + "\\" + filename;
        File file = new File(path);
        boolean exists = file.exists();
        log.info("File exists (Windows check): {} -> {}", path, exists);
        return exists;
    }

    /**
     * Tạo thư mục với đường dẫn cứng
     */
    public boolean createDirectoryWindows(String basePath, String dirName) {
        String fullPath = basePath + "\\" + dirName;
        File dir = new File(fullPath);

        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                log.info("Directory created: {}", fullPath);
            } else {
                log.error("Failed to create directory: {}", fullPath);
            }
            return created;
        }

        log.info("Directory already exists: {}", fullPath);
        return true;
    }

    /**
     * Lấy tên file từ đường dẫn - dùng split cứng
     */
    public String getFileNameFromPathWindows(String fullPath) {
        // ⚠️ LỖI: Dùng backslash cứng để split
        String[] parts = fullPath.split("\\\\");
        String fileName = parts[parts.length - 1];
        log.debug("Extracted filename: {} from {}", fileName, fullPath);
        return fileName;
    }
}