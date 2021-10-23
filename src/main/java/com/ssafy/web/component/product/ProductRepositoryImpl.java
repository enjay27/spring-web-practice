package com.ssafy.web.component.product;

import com.ssafy.web.component.file.FileComponent;
import com.ssafy.web.dto.FileDto;
import com.ssafy.web.dto.ProductDto;
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
            if (files != null){
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
            }
            connection.commit();
        } catch(SQLException e) {
            connection.rollback();
            throw new SQLException(e);
        } finally {
            statement.close();
            connection.close();
        }
    }

    @Override
    public ProductDto findProduct(String isbn) throws Exception {
        Connection connection = dataSource.getConnection();
        String sql = "select * from product " +
                "where isbn = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, isbn);
        ResultSet resultSet = statement.executeQuery();
        ProductDto productDto = new ProductDto();
        while(resultSet.next()) {
            productDto.setIsbn(isbn);
            productDto.setMemberId(resultSet.getString("member_id"));
            productDto.setName(resultSet.getString("name"));
            productDto.setPrice(resultSet.getInt("price"));
            productDto.setExplanation(resultSet.getString("explanation"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return productDto;
    }

    @Override
    public void modifyProduct(ProductDto productDto) throws Exception {
        Connection connection = dataSource.getConnection();
        String sql = "update product " +
                "set name = ?, " +
                "price = ?, " +
                "explanation = ? " +
                "where isbn = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productDto.getName());
        statement.setInt(2, productDto.getPrice());
        statement.setString(3, productDto.getExplanation());
        statement.setString(4, productDto.getIsbn());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void deleteProduct(ProductDto productDto) throws Exception {
        Connection connection = dataSource.getConnection();
        String sql = "delete from product " +
                "where isbn = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productDto.getIsbn());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}
