package com.sun.zq;

import com.sun.zq.dao.UserMapper;
import com.sun.zq.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(classes = Application.class)
class ApplicationTests {
	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
		System.out.println("test started!");
	}

	@Test
	void testMybatis() {
		User user = new User();
		user.setName("lisi");
		user.setAge(20);
		user.setMobile("15000969862");
		user.setCrtTime(new Date());
		user.setUptTime(user.getCrtTime());

		userMapper.insert(user);
	}

}
