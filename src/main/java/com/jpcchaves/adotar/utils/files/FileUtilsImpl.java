package com.jpcchaves.adotar.utils.files;

import com.jpcchaves.adotar.utils.files.contracts.FileUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtilsImpl implements FileUtils {
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("png", "jpeg", "jpg", "webp", "jfif");
    private static final long MAX_FILE_SIZE_BYTES = 2 * 1024 * 1024;
    private final Logger logger = Logger.getLogger(FileUtilsImpl.class.getName());

    @Override
    public String encodeMultipartFileWithPrefix(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            if (file.getSize() > MAX_FILE_SIZE_BYTES) {
                throw new RuntimeException("File size exceeds the allowed limit");
            }

            String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String fileExtension = getFileExtension(originalFilename);

            if (!ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase())) {
                throw new RuntimeException("Invalid file type. Allowed types are: " + ALLOWED_EXTENSIONS);
            }

            byte[] fileContent = file.getBytes();
            String base64Encoded = Base64.getEncoder().encodeToString(fileContent);
            return "data:" + file.getContentType() + ";base64," + base64Encoded;
        } catch (IOException | IllegalArgumentException e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
