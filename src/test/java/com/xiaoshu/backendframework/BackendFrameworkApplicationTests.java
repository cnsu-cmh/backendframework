package com.xiaoshu.backendframework;

import com.xiaoshu.backendframework.constants.UserConstants;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendFrameworkApplicationTests {

    @Test
    public void contextLoads() {

    }
    @Test
    public void passwordEncoder() {
        String salt = DigestUtils.md5Hex(UUID.randomUUID().toString() + System.currentTimeMillis() + UUID.randomUUID().toString());
        Object object = new SimpleHash(UserConstants.HASH_ALGORITHM, "admin", salt, UserConstants.HASH_ITERATIONS);
        System.out.println(salt);
        System.out.println(object.toString());
    }

}
