package com.sws.newtec.modules.module1.dao.repository;

import com.sws.newtec.modules.module1.model.Server;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by george on 15-7-1.
 */
public interface ServerRepository extends PagingAndSortingRepository<Server,String> {
}
