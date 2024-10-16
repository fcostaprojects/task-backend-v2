package com.empresa.tasksBackend.controllers;

import com.empresa.tasksBackend.dtos.TaskRecordDto;
import com.empresa.tasksBackend.models.Task;
import com.empresa.tasksBackend.repositories.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

//    @GetMapping("/")
//    public String hello() {
//        return "Hello World!";
//    }

    @PostMapping("/tasks")
    public ResponseEntity<Object> saveTask(@RequestBody @Valid TaskRecordDto taskRecordDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Coleta mensagens de erro
            var listMessagens = new ArrayList<String>();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                listMessagens.add(objectError.getDefaultMessage());
            }
//            List<String> listaErros = bindingResult.getAllErrors().stream()
//                    .map(ObjectError::getDefaultMessage)
//                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listMessagens);
        }

        var task = new Task();
        BeanUtils.copyProperties(taskRecordDto, task);
        var saida = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(saida);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        if(!taskList.isEmpty()) {
            for(Task product: taskList) {
                UUID id = product.getId();
                Link link = linkTo(methodOn(TaskController.class).getOneTask(id)).withSelfRel();
                product.add(link);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskList);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> getOneTask(@PathVariable(value = "id")UUID id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
        }
        Link link = linkTo(methodOn(TaskController.class).getAllTasks()).withSelfRel();
        task.get().add(link);
        return ResponseEntity.status(HttpStatus.OK).body(task.get());
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "id")UUID id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
        }
        taskRepository.delete(task.get());
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

}
