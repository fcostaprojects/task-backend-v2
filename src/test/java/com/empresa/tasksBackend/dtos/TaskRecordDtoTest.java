package com.empresa.tasksBackend.dtos;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

public class TaskRecordDtoTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void deveRetonarDuasMensagensParaParametrosVazios() {
        TaskRecordDto TaskRecordDto = new TaskRecordDto("",null);
        Set<ConstraintViolation<TaskRecordDto>> violations = validator.validate(TaskRecordDto);
        Assertions.assertThat(violations).extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder("O campo detail não foi preenchido",
                        "O campo data não foi preenchido");
    }

    @Test
    public void deveAceitarDatasFuturas() {
        LocalDate localDate = LocalDate.of(2030,01,01);
        TaskRecordDto taskRecordDto = new TaskRecordDto("Tarefa",localDate);
        Set<ConstraintViolation<TaskRecordDto>> violations = validator.validate(taskRecordDto);
        Assert.assertEquals(violations.size() ,0);
    }

    @Test
    public void naoDeveAceitarDataPassada() {
        LocalDate localDate = LocalDate.of(2024,01,01);
        TaskRecordDto taskRecordDto = new TaskRecordDto("Tarefa",localDate);
        Set<ConstraintViolation<TaskRecordDto>> violations = validator.validate(taskRecordDto);
        Assertions.assertThat(violations).extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder("A data deve ser igual ou superior a atual");
    }

    @Test
    public void deveAceitarDataAtual() {
        LocalDate localDate = LocalDate.now();
        TaskRecordDto taskRecordDto = new TaskRecordDto("Tarefa",localDate);
        Set<ConstraintViolation<TaskRecordDto>> violations = validator.validate(taskRecordDto);
        Assert.assertEquals(violations.size(),0);
    }
}
