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


  @Override
  public void save(MultipartFile file, int folderType) {
    try {
      Path uploadsFolder = Paths.get("commissionFolders/" + getFolderName(folderType));
      Files.copy(file.getInputStream(), uploadsFolder.resolve(file.getOriginalFilename()));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public Resource load(String filename, int folderType) {
    try {
      Path uploadsFolder = Paths.get("commissionFolders/" + getFolderName(folderType));
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
  public Stream<Path> loadAllFromFolder(int folderType) {
    try {
      Path uploadsFolder = Paths.get("commissionFolders/" + getFolderName(folderType));
      return Files.walk(uploadsFolder, 1).filter(path -> !path.equals(uploadsFolder))
          .map(uploadsFolder::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }

  public String getFolderName(int folderType) {
    switch (folderType) {
      case 1:
        return "Réglementations Fiscales Fr";
      case 2:
        return "Rapports Fr";
      case 3:
        return "Autres Lois et Réglementations Fr";
      default:
        return "none";
    }
  }
}
