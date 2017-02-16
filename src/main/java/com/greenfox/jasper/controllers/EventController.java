package com.greenfox.jasper.controllers;

import com.greenfox.jasper.dto.PostDto;
import com.greenfox.jasper.services.TimedEventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/events")
public class EventController {
    @Autowired
    TimedEventServices timedEventServices;

    @RequestMapping(value = "/cancel")
    public ResponseEntity cancelEvent(@RequestBody PostDto eventPost){
        timedEventServices.cancelEvent(eventPost.getId());
        return ResponseEntity.status(HttpStatus.OK).body(timedEventServices.findOne(eventPost.getId()));
    }
}
