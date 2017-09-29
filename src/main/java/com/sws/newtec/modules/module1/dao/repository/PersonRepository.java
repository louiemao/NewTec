package com.sws.newtec.modules.module1.dao.repository;

import com.sws.newtec.modules.module1.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by george on 15-7-1.
 */
public interface PersonRepository extends PagingAndSortingRepository<Person,Long> {
}
