package com.mohamed.hadramy.springbootapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.mohamed.hadramy.springbootapp.message.ResponseMessage;
import com.mohamed.hadramy.springbootapp.model.FileInfo;
import com.mohamed.hadramy.springbootapp.service.FilesStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


@Controller
public class FilesController {

  @Autowired
  FilesStorageService storageService;

  @PostMapping("/upload/{folderType}")
  public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files, @PathVariable("folderType") int folderType) {
    String message = "";
    try {
      List<String> fileNames = new ArrayList<>();

      Arrays.asList(files).stream().forEach(file -> {
        storageService.save(file, folderType);
        fileNames.add(file.getOriginalFilename());
      });

      message = "Uploaded the files successfully: " + fileNames;
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Failed to upload files!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/files/{folderType}")
  public ResponseEntity<List<FileInfo>> getListFiles(@PathVariable("folderType") int folderType) {
    List<FileInfo> fileInfos = storageService.loadAllFromFolder(folderType).map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString(), folderType).build().toString();
      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/files/{filename:.+}/{folderType}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename, @PathVariable("folderType") int folderType) {
    Resource file = storageService.load(filename, folderType);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}