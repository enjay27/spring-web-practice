package com.ssafy.web.component.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class FileComponentImpl implements FileComponent {

    @Autowired
    FileRepository fileRepository;
}
