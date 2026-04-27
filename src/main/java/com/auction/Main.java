package com.fileprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("=== File Processor App Started ===");

        FilePathProcessor processor = new FilePathProcessor();

        String osName = System.getProperty("os.name");
        log.info("Current OS: {}", osName);

        // Thử các phương thức
        if (osName.toLowerCase().contains("win")) {
            String winPath = processor.buildWindowsPath("data", "file.txt");
            System.out.println("Windows path: " + winPath);
        } else {
            String linuxPath = processor.buildLinuxPath("data", "file.txt");
            System.out.println("Linux/Mac path: " + linuxPath);
        }

        log.info("=== File Processor App Finished ===");
    }
}