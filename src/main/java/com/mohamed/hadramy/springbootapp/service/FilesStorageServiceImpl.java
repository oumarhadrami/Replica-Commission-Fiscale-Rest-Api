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
  public void save(MultipartFile file, int folderType, String language) {
    try {
      Path uploadsFolder = Paths.get("commissionFolders/" + getFolderName(folderType, language));
      Files.copy(file.getInputStream(), uploadsFolder.resolve(file.getOriginalFilename()));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public Resource load(String filename, int folderType, String language) {
    try {
      Path uploadsFolder = Paths.get("commissionFolders/" + getFolderName(folderType, language));
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
  public Stream<Path> loadAllFromFolder(int folderType, String language) {
    try {
      Path uploadsFolder = Paths.get("commissionFolders/" + getFolderName(folderType, language));
      return Files.walk(uploadsFolder, 1).filter(path -> !path.equals(uploadsFolder)).map(uploadsFolder::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }

  public String getFolderName(int folderType, String language) {
    if (folderType == 1) {
      if (language.equals("ar")) {
        return "Réglementations Fiscales Ar";
      } else {
        return "Réglementations Fiscales Fr";
      }
    }
    else if (folderType == 2) {
      if (language.equals("ar")) {
        return "Rapports Ar";
      } else {
        return "Rapports Fr";
      }
    }
    else{
      if (language.equals("ar")) {
        return "Autres Lois et Réglementations Ar";
      } else {
        return "Autres Lois et Réglementations Fr";
      }
    }
  }
}
