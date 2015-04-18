package com.rizvn.ogmdemo.conf;

import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionManagerImple;
import org.hibernate.ogm.jpa.HibernateOgmPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Riz
 */
 @Configuration
public class DbConf {
  @Bean
  LocalContainerEntityManagerFactoryBean entityManager() {
    LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
    bean.setPersistenceUnitName("ogmdemo");
    return bean;
  }

  @Bean
  TransactionTemplate transactionTemplate(){
    TransactionTemplate transactionTemplate = new TransactionTemplate();
    transactionTemplate.setTransactionManager(platformTransactionManager());
    return transactionTemplate;
  }

  @Bean
  PlatformTransactionManager platformTransactionManager(){
    JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
    TransactionManagerImple transactionManagerImple  = new TransactionManagerImple();
    jtaTransactionManager.setTransactionManager(transactionManagerImple);
    return jtaTransactionManager;
  }
}
