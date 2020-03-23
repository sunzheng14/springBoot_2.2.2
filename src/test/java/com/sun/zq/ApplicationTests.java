package com.sun.zq;

import com.sun.zq.dao.UserMapper;
import com.sun.zq.model.User;
import com.sun.zq.model.UserExample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

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

	@Test
	void testQuery() {
		UserExample example = new UserExample();
		example.or().andNameLike("%san%");
		example.setOrderByClause("id desc");

		List<User> list = userMapper.selectByExample(example);
		list.forEach((User user) -> {
			System.out.println(user.getId());
		});

	}

	@Test
	void testSelect() {
		User user = userMapper.selectByPrimaryKey(2);
		if (user != null) {
			System.out.println(user.getId());
		}

	}

}
