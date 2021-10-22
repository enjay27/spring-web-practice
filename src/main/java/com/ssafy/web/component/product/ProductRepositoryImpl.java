package com.ssafy.web.component.product;

import com.ssafy.web.component.file.FileComponent;
import com.ssafy.web.dto.FileDto;
import com.ssafy.web.dto.ProductFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    DataSource dataSource;

    @Autowired
    FileComponent fileComponent;

    @Override
    public List<ProductFileDto> searchProduct(String name) throws Exception {
        Connection connection = dataSource.getConnection();
        String sql = "select * from product ";

        if (name != null) sql += "where name like '%" + name + "%'";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<ProductFileDto> productFileDtos = new ArrayList<>();
        while (resultSet.next()) {
            ProductFileDto productFileDto = new ProductFileDto();
            productFileDto.setIsbn(resultSet.getString("isbn"));
            productFileDto.setMemberId(resultSet.getString("member_id"));
            productFileDto.setName(resultSet.getString("name"));
            productFileDto.setPrice(resultSet.getInt("price"));
            productFileDto.setExplanation(resultSet.getString("explanation"));
            productFileDtos.add(productFileDto);
        }

        for (ProductFileDto productFileDto : productFileDtos) {
            final String isbn = productFileDto.getIsbn();
            List<FileDto> fileDtos = fileComponent.findFiles(isbn);
            productFileDto.setFiles(fileDtos);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return productFileDtos;
    }

    @Override
    public void insertProductWithFiles(ProductFileDto productFileDto) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String productSql = "insert into product (isbn, member_id, name, price, explanation) " +
                    "values (?, ?, ?, ?, ?)";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(productSql);
            statement.setString(1, productFileDto.getIsbn());
            statement.setString(2, productFileDto.getMemberId());
            statement.setString(3, productFileDto.getName());
            statement.setInt(4, productFileDto.getPrice());
            statement.setString(5, productFileDto.getExplanation());
            statement.executeUpdate();
            StringBuilder fileSql = new StringBuilder("insert into files (product_isbn, path, file_name, save_name) values");
            List<FileDto> files = productFileDto.getFiles();
            for (int i = 0; i < files.size(); i++) {
                fileSql.append("(?, ?, ?, ?)");
                if (i != files.size() - 1)
                    fileSql.append(",");
            }
            statement = connection.prepareStatement(fileSql.toString());
            int index = 0;
            for (FileDto file : files) {
                statement.setString(++index, productFileDto.getIsbn());
                statement.setString(++index, file.getPath());
                statement.setString(++index, file.getFileName());
                statement.setString(++index, file.getSaveName());
            }
            statement.executeUpdate();
        } catch(SQLException e) {
            connection.rollback();
            throw new SQLException(e);
        } finally {
            statement.close();
            connection.close();
        }
    }
}
