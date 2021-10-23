package com.ssafy.web.component.member;

import com.ssafy.web.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
class MemberRepositoryImpl implements MemberRepository {

    @Autowired
    DataSource dataSource;

    @Override
    public MemberDto findMember(MemberDto memberDto) throws Exception {
        Connection connection = dataSource.getConnection();
        String sql = "select * from members " +
                "where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, memberDto.getId());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            MemberDto findMember = new MemberDto();
            findMember.setId(resultSet.getString("id"));
            findMember.setPw(resultSet.getString("pw"));
            findMember.setName(resultSet.getString("name"));
            findMember.setAddr(resultSet.getString("addr"));
            findMember.setAge(resultSet.getInt("age"));
            resultSet.close();
            statement.close();
            connection.close();
            return findMember;
        }
        resultSet.close();
        statement.close();
        connection.close();
        return null;
    }

    @Override
    public void insertMember(MemberDto memberDto) throws Exception {
        Connection connection = dataSource.getConnection();
        String sql = "insert into members (id, pw, name, addr, age) " +
                "values (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, memberDto.getId());
        statement.setString(2, memberDto.getPw());
        statement.setString(3, memberDto.getName());
        statement.setString(4, memberDto.getAddr());
        statement.setInt(5, memberDto.getAge());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
