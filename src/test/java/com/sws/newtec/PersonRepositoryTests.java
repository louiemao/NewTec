//package com.sws.newtec;
//
//import com.sws.newtec.modules.module1.dao.repository.PersonRepository;
//import com.sws.newtec.modules.module1.model.Person;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * Created by george on 15-7-1.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//public class PersonRepositoryTests {
//    @Autowired   PersonRepository personRepository;
//    @Test
//    public void readsFirstPageCorrectly(){
//        Page<Person> persons=personRepository.findAll(new PageRequest(0,10));
//       // assertThat(persons.isFirst(),is(true));
//    }
//
//}
