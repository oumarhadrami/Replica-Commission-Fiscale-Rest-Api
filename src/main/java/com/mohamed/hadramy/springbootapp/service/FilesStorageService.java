package com.mohamed.hadramy.springbootapp.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

  public void save(MultipartFile file);

  public Resource load(String filename);

  public Stream<Path> loadAll();
}