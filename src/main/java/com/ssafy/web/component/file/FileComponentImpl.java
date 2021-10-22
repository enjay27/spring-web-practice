package com.ssafy.web.component.file;

import com.ssafy.web.dto.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class FileComponentImpl implements FileComponent {

    @Autowired
    FileRepository fileRepository;

    @Override
    public List<FileDto> findFiles(String isbn) throws Exception {
        return fileRepository.findFiles(isbn);
    }
}
