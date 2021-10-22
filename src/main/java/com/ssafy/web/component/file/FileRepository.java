package com.ssafy.web.component.file;

import com.ssafy.web.dto.FileDto;

import java.util.List;

interface FileRepository {
    List<FileDto> findFiles(String isbn) throws Exception;
}
