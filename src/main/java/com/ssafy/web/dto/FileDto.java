package com.ssafy.web.dto;

public class FileDto {
    private String path;
    private String fileName;
    private String saveName;

    public FileDto(String path, String fileName, String saveName) {
        this.path = path;
        this.fileName = fileName;
        this.saveName = saveName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getPath() {
        return path;
    }

    public String getFileName() {
        return fileName;
    }

    public String getSaveName() {
        return saveName;
    }
}
