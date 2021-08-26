package com.xunli.yygh.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description： 配置类
 * @ClassName: HospConfig.java
 * @Author： XiaoMa
 * @Date： 2021/8/26 15:15
 */
@Configuration
@MapperScan("com.xunli.yygh.hosp.mapper")
public class HospConfig {
}
