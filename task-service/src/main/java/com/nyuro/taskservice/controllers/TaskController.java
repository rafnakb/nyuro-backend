package com.nyuro.taskservice.controllers;

import com.nyuro.taskservice.services.TaskService;
import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("/")
    public ResponseEntity<List<Task>> getAll() {
        List<Task> taskList = service.getAll();
        return ResponseEntity.ok().body(taskList);
    }

    public ResponseEntity<Task> save(@RequestBody Task obj) {
        Task newObj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
