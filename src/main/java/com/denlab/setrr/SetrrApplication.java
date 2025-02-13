package com.denlab.setrr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SetrrApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SetrrApplication.class);
		app.addInitializers(new EarlyEnvPrinter());
		app.run(args);
	}

}
