package com.dhu.bbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dhu.bbs")
public class DhuBbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DhuBbsApplication.class, args);
	}

}
