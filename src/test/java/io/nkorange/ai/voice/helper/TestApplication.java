package io.nkorange.ai.voice.helper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author wenchao.wu
 * @Date 2021/8/4 6:06 下午
 */
@SpringBootApplication(scanBasePackages = "io.nkorange.ai.voice.helper")
@ComponentScan("io.nkorange.ai.voice.helper.*")
@EnableScheduling
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
