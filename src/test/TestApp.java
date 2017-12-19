package test;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


public class TestApp {
	
	public static void main(String[] args) {
		
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor(); 
		threadPoolTaskExecutor.setMaxPoolSize(50);
	}

}
