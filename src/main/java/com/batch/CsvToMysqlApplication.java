package com.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
public class CsvToMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvToMysqlApplication.class, args);
	}

}
