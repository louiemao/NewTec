package com.sws.newtec.modules.module1.dao;

import com.mongodb.DBObject;
import com.sws.newtec.modules.module1.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by george on 15-6-30.
 */

@Service
public class CustomerRepository {

    @Autowired
    public MongoOperations template;

    /**
     * 插入一个文档
     * @param customer
     */
    public void Save(Customer customer){
        template.save(customer);
    }

    /**
     * 查询所有的Customer文档，作为List返回
     * @return Customer列表
     */
    public List<Customer> findAll(){
        return template.findAll(Customer.class);
    }


    public void delete(String id) {
        Criteria criteria=Criteria.where("id").is(id);
        template.remove(new Query(criteria),Customer.class);
    }

    public Customer findOne(String id) {
        return template.findOne(new Query(new Criteria().where("id").is(id)),Customer.class);
    }
}
