package br.ufpb.dcx.aps.formbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class FormbuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormbuilderApplication.class, args);
	}


	public String index() {
		return "index.html";
	}

}
