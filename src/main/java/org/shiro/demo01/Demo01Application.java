package org.shiro.demo01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class Demo01Application {
/**
 * https://blog.csdn.net/qq_45173404/article/details/109492486?biz_id=102&utm_term=shiro%E5%AE%8C%E6%95%B4%E6%90%AD%E5%BB%BA&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-1-109492486&spm=1018.2118.3001.4449
 * */
    public static void main(String[] args) {
        SpringApplication.run(Demo01Application.class, args);
    }

}
