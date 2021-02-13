package com.mohamed.hadramy.springbootapp.service;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path uploadsFolder = Paths.get("commissionFolders");

  @Override
  public void save(MultipartFile file) {
    try {
      Files.copy(file.getInputStream(), this.uploadsFolder.resolve(file.getOriginalFilename()));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = uploadsFolder.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.uploadsFolder, 1).filter(path -> !path.equals(this.uploadsFolder)).map(this.uploadsFolder::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }
}
