package com.ssafy.web.component.file;

import com.ssafy.web.dto.FileDto;

import java.util.List;

public interface FileComponent {
    List<FileDto> findFiles(String isbn) throws Exception;
}
