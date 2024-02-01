package com.jpcchaves.adotar.application.utils.files;

import com.jpcchaves.adotar.application.utils.files.contracts.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class FileUtilsImpl implements FileUtils {
    private final Logger logger = Logger.getLogger(FileUtilsImpl.class.getName());

    @Override
    public String encodeMultipartFileWithPrefix(MultipartFile file) {
        try {
            byte[] fileContent = file.getBytes();
            String base64Encoded = Base64.getEncoder().encodeToString(fileContent);
            return "data:" + file.getContentType() + ";base64," + base64Encoded;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file content: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error encoding file content to base64: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String getFileExtension(MultipartFile file) {
        String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
        String cleanPath = StringUtils.cleanPath(originalFilename);

        int lastDotIndex = cleanPath.lastIndexOf(".");

        if (lastDotIndex == -1) {
            return "";
        }

        return cleanPath.substring(lastDotIndex + 1);
    }
}
