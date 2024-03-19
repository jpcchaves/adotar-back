package com.jpcchaves.adotar.application.utils.files.contracts;

import org.springframework.web.multipart.MultipartFile;

public interface FileUtils {
  String encodeMultipartFileWithPrefix(MultipartFile file);

  String getFileExtension(MultipartFile file);
}
