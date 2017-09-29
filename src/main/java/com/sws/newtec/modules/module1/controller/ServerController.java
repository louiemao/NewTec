package com.sws.newtec.modules.module1.controller;

import com.sws.newtec.modules.module1.dao.repository.ServerRepository;
import com.sws.newtec.modules.module1.model.Server;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by george on 15-7-6.
 */
@Controller
public class ServerController {

    @Autowired
    ServerRepository serverRepository;

    @RequestMapping(value = "/mongo/servers", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Server> findAll() {
        return (List<Server>) serverRepository.findAll();
    }

    @RequestMapping(value = "/mongo/servers/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Server findServerById(@PathVariable String id) {
        return serverRepository.findOne(id);
    }

    public
    @ResponseBody
    @RequestMapping(value = "/mongo/servers", method = RequestMethod.POST)
    boolean createServer(@RequestBody Server server) {
        if (server.getId().equals("")) {
            server.setId(new ObjectId().toString());
        }
        try {
            server.setRgstDate(Date.class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        serverRepository.save(server);
        return true;
    }
}
