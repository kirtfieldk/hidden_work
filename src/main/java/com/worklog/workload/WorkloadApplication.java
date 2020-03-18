package com.worklog.workload;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class WorkloadApplication {

	public static void main(String[] args) {
//		File file = new File("/Users/keithkirtfield/Documents/java/repos/gather");
//		if(file.exists()){
//			file.delete();
//		}else {
//			try {
//				Git git = Git.cloneRepository()
//						.setURI("https://github.com/kirtfieldk/golang-Games.git")
//						.setDirectory(file)
//						.call();
//			} catch (GitAPIException e) {
//				System.out.println(e);
//
//			}
//		}
		SpringApplication.run(WorkloadApplication.class, args);
	}

}
