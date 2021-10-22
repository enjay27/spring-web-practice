package com.ssafy.web.component.file;

import com.ssafy.web.dto.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
class FileRepositoryImpl implements FileRepository {

    @Autowired
    DataSource dataSource;

    @Override
    public List<FileDto> findFiles(String isbn) throws Exception {
        Connection connection = dataSource.getConnection();

        String sql = "select * from files " +
                "where product_isbn = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, isbn);

        ResultSet resultSet = statement.executeQuery();
        List<FileDto> fileDtos = new ArrayList<>();
        while (resultSet.next()) {
            FileDto fileDto = new FileDto();
            fileDto.setPath(resultSet.getString("path"));
            fileDto.setFileName(resultSet.getString("file_name"));
            fileDto.setSaveName(resultSet.getString("save_name"));
            fileDtos.add(fileDto);
        }

        return fileDtos;
    }
}
