package com.jpcchaves.adotar.utils.files.contracts;

import org.springframework.web.multipart.MultipartFile;

public interface FileUtils {
    String encodeMultipartFileWithPrefix(MultipartFile file);

    String getFileExtension(MultipartFile file);
}
