package com.springbootAnmte.animte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.springbootAnmte.animte.service.AnmteService;

@SpringBootApplication
@ComponentScan(basePackages = "com.springbootAnmte.animte")
public class AnimteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AnimteApplication.class, args);
	}
	@Autowired
	AnmteService as;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
