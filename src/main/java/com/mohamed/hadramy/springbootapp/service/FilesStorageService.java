package com.mohamed.hadramy.springbootapp.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

  public void save(MultipartFile file, int folderType, String language);

  public Resource load(String filename, int folderType, String language);

  public Stream<Path> loadAllFromFolder(int folderType, String language);
}