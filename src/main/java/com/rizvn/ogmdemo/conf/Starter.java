package com.rizvn.ogmdemo.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Riz
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={HibernateJpaAutoConfiguration.class})
@ComponentScan("com.rizvn")
public class Starter {
  public static void main(String [] args) throws Exception{
    SpringApplication.run(Starter.class, args);
  }
}
