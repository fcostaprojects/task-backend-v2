package com.empresa.TasksBackend.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

public record TaskRecordDto(
    @NotBlank(message = "O campo detail não foi preenchido")
    String detail,

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "O campo data não foi preenchido")
    @FutureOrPresent(message = "A data deve ser igual ou superior a atual")
    Date date

) {
}
