package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() {
        assertNotNull(dataSource, "DataSource는 null이 아니어야 합니다");

        try {
            assertNotNull(dataSource.getConnection(), "데이터베이스 연결이 성립되어야 합니다");
            System.out.println("데이터베이스 연결 성공");
        } catch (SQLException e) {
            e.printStackTrace();
            assert false : "데이터베이스 연결 실패";
        }
    }

}
