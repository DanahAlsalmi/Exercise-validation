package com.example.exercisevalidation2.Model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {
    @NotNull(message = "ID cannot be null")
    @Min(2)
    private int id;

    @NotNull(message = "Description cannot be null")
    @Size(min = 15 ,message =" Description must be greater than or equal to 15" )
    private String description;

    @NotNull(message = "Capacity cannot be null")
    @Positive(message = "Capacity should be positive number")
    @Min(25)
    private int capacity;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime startDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime endDate;
}