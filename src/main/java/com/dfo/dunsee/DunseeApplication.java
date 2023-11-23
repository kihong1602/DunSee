package com.dfo.dunsee;

import com.dfo.dunsee.common.ServiceCode;
import java.util.concurrent.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@SpringBootApplication
public class DunseeApplication {

  public static void main(String[] args) {
    SpringApplication.run(DunseeApplication.class, args);
  }

  @Bean
  public Executor taskExecutor() {

    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(3);
    executor.setMaxPoolSize(10);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix(ServiceCode.setServiceMsg(ServiceCode.CHR201) + "ThreadPool Setting Process-");
    executor.initialize();
    return executor;
  }
}
