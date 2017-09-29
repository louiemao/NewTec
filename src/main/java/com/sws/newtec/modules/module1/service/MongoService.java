package com.sws.newtec.modules.module1.service;

import com.sws.newtec.modules.module1.dao.CustomerRepository;
import com.sws.newtec.modules.module1.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by george on 15-6-29.
 */
@Service
public class MongoService {
    private final static Logger logger= LoggerFactory.getLogger(MongoService.class);
    @Autowired
    CustomerRepository customerRepository;

    public void saveCustomer(Customer customer){
        customerRepository.Save(customer);
    }

    public List<Customer> findAllCustomer(){
        return customerRepository.findAll();
    }

    public void deleteCustomer(String id) {
        customerRepository.delete(id);
    }

    public Customer findCustomerById(String id) {

        return customerRepository.findOne(id);
    }
}
