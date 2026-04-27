package com.fileprocessor;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FilePathProcessorTest {
    private static final Logger log = LoggerFactory.getLogger(FilePathProcessorTest.class);
    private com.fileprocessor.FilePathProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new com.fileprocessor.FilePathProcessor();
    }

    @Test
    @DisplayName("TEST SẼ PASS TRÊN WINDOWS NHƯNG FAIL TRÊN LINUX/MAC")
    void testBuildWindowsPath() {
        // Test này chỉ PASS trên Windows
        String result = processor.buildWindowsPath("data", "test.txt");


        assertEquals("data\\test.txt", result);

        log.info("Windows path test: {}", result);
    }

    @Test
    @DisplayName("TEST SẼ PASS TRÊN LINUX/MAC NHƯNG FAIL TRÊN WINDOWS")
    void testBuildLinuxPath() {
        // Test này chỉ PASS trên Linux/Mac
        String result = processor.buildLinuxPath("data", "test.txt");


        assertEquals("data/test.txt", result);

        log.info("Linux path test: {}", result);
    }

    @Test
    @DisplayName("TEST XỬ LÝ TÊN FILE - PHỤ THUỘC OS")
    void testGetFileNameFromPath() {

        String windowsPath = "C:\\Users\\test\\data\\myfile.txt";
        String fileName = processor.getFileNameFromPathWindows(windowsPath);

        assertEquals("myfile.txt", fileName);
    }

    @Test
    @DisplayName("TEST TẠO THƯ MỤC - KIỂM TRA CROSS-PLATFORM")
    void testCreateDirectory() {
        String tempDir = System.getProperty("java.io.tmpdir");


        boolean result = processor.createDirectoryWindows(tempDir, "test_folder");

        // Verify
        assertTrue(result);
        log.info("Directory creation test on OS: {}", System.getProperty("os.name"));
    }

    @Test
    @DisplayName("IN THÔNG TIN HỆ ĐIỀU HÀNH")
    void printOsInfo() {
        String osName = System.getProperty("os.name");
        String fileSeparator = System.getProperty("file.separator");
        String pathSeparator = System.getProperty("path.separator");

        log.info("=== OS INFORMATION ===");
        log.info("OS Name: {}", osName);
        log.info("File Separator: '{}'", fileSeparator);
        log.info("Path Separator: '{}'", pathSeparator);
        log.info("User Dir: {}", System.getProperty("user.dir"));
        log.info("Temp Dir: {}", System.getProperty("java.io.tmpdir"));

        // Kiểm tra nếu đang chạy trên Windows
        if (osName.toLowerCase().contains("win")) {
            log.warn("Running on Windows - Backslash paths will work");
        } else {
            log.warn("Running on Unix/Linux/Mac - Forward slash paths will work");
        }
    }
}