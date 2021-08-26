package com.xunli.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author：XiaoMa @Date：Created in 2021/8/26 10:34 @Description： 启动类
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xunli")
public class ServiceHospApplication {
  public static void main(String[] args) {

    SpringApplication.run(ServiceHospApplication.class, args);
  }
}
