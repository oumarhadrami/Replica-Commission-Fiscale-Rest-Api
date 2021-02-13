package com.mohamed.hadramy.springbootapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileInfo {
    private String name;
    private String url;

    public FileInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
