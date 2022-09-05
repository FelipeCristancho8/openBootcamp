package com.build;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class BuildApplicationTests {

	@Test
	void contextLoads() {
		System.getenv().forEach(
				(k, v) -> System.out.println(k + "=" + v)
		);
	}

}
