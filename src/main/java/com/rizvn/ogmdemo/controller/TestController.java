package com.rizvn.ogmdemo.controller;

import com.rizvn.ogmdemo.entity.Breed;
import com.rizvn.ogmdemo.entity.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Riz
 */
@Controller
@RequestMapping("/test")
public class TestController {

  @PersistenceContext
  EntityManager em;

  @Resource
  TransactionTemplate transactionTemplate;

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView get(){
    return new ModelAndView("test/test");
  }

  @RequestMapping(method = RequestMethod.GET, params ="req=create")
  public @ResponseBody String create() {
    return transactionTemplate.execute(status -> {
      Breed collie = new Breed();
      collie.setName("Collie");
      em.persist(collie);

      Dog dina = new Dog();
      dina.setName("Dina");
      dina.setBreed(collie);
      em.persist(dina);

      return "Created";
    });
  }

  @RequestMapping(method = RequestMethod.GET, params ="req=createRollback")
  public @ResponseBody String createRollback(){
    try
    {
      return transactionTemplate.execute(status -> {

        Breed collie = new Breed();
        collie.setName("Collie-ROLLBACK");
        em.persist(collie);

        Dog dina = new Dog();
        dina.setName("Dina-ROLLBACK");
        if(true) {
          throw new RuntimeException("Rolling back");
        }
        dina.setBreed(collie);
        em.persist(dina);
        Long dinaId = dina.getId();
        return "rolled back";
      });
    }
    catch (Exception ex)
    {
      return "Created and Rolled Back";
    }
  }

  @RequestMapping(method = RequestMethod.GET, params = "req=listDogs")
  public @ResponseBody String listDogs(){
    return transactionTemplate.execute(status -> {
      List<Dog> dogs = em.createQuery("FROM Dog").getResultList();
      return dogs.toString();
    });
  }
}
