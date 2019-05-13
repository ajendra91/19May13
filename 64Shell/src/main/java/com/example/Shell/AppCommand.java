package com.example.Shell;

import java.util.Random;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import org.springframework.web.client.RestTemplate;

@ShellComponent
public class AppCommand {
	
	@Autowired
	public RestTemplate rt;

	/*@ShellMethod(value="jodana do number ko")
	public int add(int a,int b) {
		return a+b;
	}*/
	
	@ShellMethod(value="adding to numbers",key="s")
	public int add(int a,int b) {
		return a+b;
	}
	

	@ShellMethod(value="defalut test",key="say")
	public String wish(@ShellOption(defaultValue="ajendra") String name) {
		return name;
	}
	

	@ShellMethod(value="validation test",key="change")
	public String changepassword(@Size(min=4,max=10) String name) {
		return name;
	}
	
	@ShellMethod(value="conn check",key="connect")
	@ShellMethodAvailability(value="server is running")
	public String checkConn() {
		return isAvailable()?Availability.available().getReason():
			Availability.unavailable("server not running").getReason();
	}
	@ShellMethod(value="rest check",key="rest")
	public String callrest(String url) {
		return rt.getForObject(url, String.class);
	}
	
	private boolean isAvailable() {
		return new Random().nextBoolean();
	}
	
	@Bean
	public RestTemplate fun() {
		return new RestTemplate();
	}
	
}
