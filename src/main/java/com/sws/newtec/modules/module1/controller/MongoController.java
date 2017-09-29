package com.sws.newtec.modules.module1.controller;

import com.sws.newtec.modules.module1.model.Customer;
import com.sws.newtec.modules.module1.service.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by george on 15-6-29.
 */
@Controller
public class MongoController {

    private final static Logger logger = LoggerFactory.getLogger(MongoController.class);
    @Autowired
    MongoService mongoService;

    /**
     * get all Customer
     *
     * @return Customer List
     */
    @RequestMapping("/mongo/customers")
    public
    @ResponseBody
    List<Customer> getCustomers() {
        logger.debug("查找所有的客户信息");
        return mongoService.findAllCustomer();
    }

    /**
     * create new Customer
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "/mongo/customers", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean saveCustomer(@RequestBody Customer customer) {
        //String firstName=String.valueOf(modelMap.get("firstName"));
        //String lastName=String.valueOf(modelMap.get("lastName"));
        logger.debug("保存客户信息");
        logger.debug(String.valueOf(customer));
        mongoService.saveCustomer(customer);
        return true;
    }

    /**
     * delete customer by customer id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/mongo/customers/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    boolean deleteCustomer(@PathVariable String id) {
        logger.debug("删除客户");
        mongoService.deleteCustomer(id);
        return true;
    }

    /**
     * get customer by customer id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/mongo/customers/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Customer findCustomerById(@PathVariable String id) {
        logger.debug("获取客户明细");
        Customer customer = mongoService.findCustomerById(id);
        return customer;
    }
}
