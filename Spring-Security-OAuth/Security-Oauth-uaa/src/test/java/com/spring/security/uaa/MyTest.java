package com.spring.security.uaa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {
    @Test
    public void TestBcryt(){
        String hashpw = BCrypt.hashpw("456", BCrypt.gensalt());
        System.out.println(hashpw); //校验原始密码和BCrypt密码是否一致
        boolean checkpw = BCrypt.checkpw("456", hashpw);
        System.out.println(checkpw);
    }
}
