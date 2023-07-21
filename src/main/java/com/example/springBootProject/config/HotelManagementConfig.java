package com.example.springBootProject.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class HotelManagementConfig {
     
	@Bean
	public OpenAPI swaggerDocOPenApi() {
		
		Server developmentServer =new Server();
		developmentServer.setUrl("http://localhost:8080");
		developmentServer.setDescription("this is for development");
		
		Server productionServer=new Server();
		productionServer.setUrl("http://localhost:8080");
		productionServer.setDescription("this is for production");
		
		io.swagger.v3.oas.models.info.Contact contact=new io.swagger.v3.oas.models.info.Contact();
		contact.setName("Hotel Management");
		contact.setEmail("help.myhotel.in");
		contact.setUrl("http://mvnrepository.com/");
		
		License licence=new License();
		licence.setName("2 year licence");
		licence.setUrl("write licence provider's url");
		
		Info info=new Info();
		info.title("My Hotel");
		info.version("1.0");
		info.contact(contact);
		info.description("designed for hotel management");
		info.termsOfService("url");
		info.license(licence);
		
		OpenAPI openApi=new OpenAPI();
		openApi.info(info);
		openApi.servers(Arrays.asList(developmentServer,productionServer));
		return openApi;
		
	}
}
