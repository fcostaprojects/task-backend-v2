package com.empresa.tasksBackend.controllers;

import com.empresa.tasksBackend.dtos.TaskRecordDto;
import com.empresa.tasksBackend.repositories.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void naoDeveSalvarTaskSemDetail() throws Exception {
        LocalDate localDate = LocalDate.now();
        String detail = "";
        TaskRecordDto taskRecordDto = new TaskRecordDto(detail, localDate);

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        ResponseEntity<Object> response = taskController.saveTask(taskRecordDto, bindingResult);
        List erros = new ArrayList();
        erros.add("O campo detail não foi preenchido");
        List<ObjectError> validacoes = bindingResult.getAllErrors();
        Assertions.assertEquals(ResponseEntity.badRequest().body(validacoes), response);

    }

    @Test
    public void naoDeveSalvarTaskSemData() throws Exception {
        LocalDate localDate = null;
        String detail = "Tarefa";
        TaskRecordDto taskRecordDto = new TaskRecordDto(detail, localDate);

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        ResponseEntity<Object> response = taskController.saveTask(taskRecordDto, bindingResult);
        List erros = new ArrayList();
        erros.add("O campo data não foi preenchido");
        List<ObjectError> validacoes = bindingResult.getAllErrors();
        Assertions.assertEquals(ResponseEntity.badRequest().body(validacoes), response);

    }

    @Test
    public void naoDeveSalvarTaskComDataPassada() throws Exception {
        LocalDate localDate = LocalDate.of(2024,01,01);
        String detail = "Tarefa";
        TaskRecordDto taskRecordDto = new TaskRecordDto(detail, localDate);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        ResponseEntity<Object> response = taskController.saveTask(taskRecordDto, bindingResult);
        List erros = new ArrayList();
        erros.add("O campo data não foi preenchido");
        List<ObjectError> validacoes = bindingResult.getAllErrors();
        Assertions.assertEquals(ResponseEntity.badRequest().body(validacoes), response);
    }

    @Test
    public void deveSalvarComSucesso() throws Exception {
        LocalDate localDate = LocalDate.now();
        String detail = "Tarefa";
        TaskRecordDto taskRecordDto = new TaskRecordDto(detail, localDate);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        ResponseEntity<Object> response = taskController.saveTask(taskRecordDto, bindingResult);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

//
//    @Test
//    public void deveSavarTarefaComSucesso() {
//
//    }
}
