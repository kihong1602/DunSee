package com.dfo.dunsee.domain.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class FileUploadController {

  private final String uploadDir = Paths.get("C:", "tui-editor", "upload")
                                        .toString();

  @PostMapping("/image-upload")
  public String uploadEditorImage(@RequestParam("image") MultipartFile image) {
    if (image.isEmpty()) {
      return "";
    }

    String originFileName = image.getOriginalFilename();
    String uuid = UUID.randomUUID()
                      .toString()
                      .replace("-", "");
    String extension = originFileName.substring(originFileName.lastIndexOf(".") + 1);
    String saveFileName = uuid + "." + extension;
    String fileFullPath = Paths.get(uploadDir, saveFileName)
                               .toString();

    File dir = new File(uploadDir);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    try {
      File uploadFile = new File(fileFullPath);
      image.transferTo(uploadFile);

      return saveFileName;
    } catch (IOException e) {
      throw new RuntimeException("파일 저장 실패");
    }
  }


  @GetMapping(value = "/image-print", produces = {
      MediaType.IMAGE_GIF_VALUE,
      MediaType.IMAGE_JPEG_VALUE,
      MediaType.IMAGE_PNG_VALUE})
  public byte[] printEditorImage(@RequestParam("fileName") String fileName) {
    String fileFullPath = Paths.get(uploadDir, fileName)
                               .toString();

    File uploadedFile = new File(fileFullPath);
    if (!uploadedFile.exists()) {
      throw new RuntimeException("파일이 존재하지 않습니다.");
    }

    try {
      
      return Files.readAllBytes(uploadedFile.toPath());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
