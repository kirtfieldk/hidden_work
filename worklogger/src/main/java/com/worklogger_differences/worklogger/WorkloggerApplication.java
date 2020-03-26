package com.worklogger_differences.worklogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
	This Spring Boot Server handles the logic of difference
	It holds three postgres tables

	Files, content, and differences
	The file contents must have the same file ID to be compared

	Through the client -> files and their contents will populate this server's db
	And this server will populate the difference between files
	between git commits

 */
@SpringBootApplication
public class WorkloggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkloggerApplication.class, args);
	}

}
