package com.example.exercisevalidation1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class Project {

    @NotNull(message = "ID cannot be null")
    @Min(2)
    private int id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 8 ,message =" Title must be greater than or equal to 8" )
    private String title;

    @NotNull(message = "Description cannot be null")
    @Size(min = 15 ,message =" Description must be greater than or equal to 15" )
    private String description;

    @NotNull(message = "Status cannot be null")
    @Pattern(regexp ="Not Started|In Progress|Completed", message = "Status must be either 'Not Started', 'In Progress', or 'Completed'" )
    private String status;

    @NotNull(message = "Company Name cannot be null")
    @Size(min = 6 ,message =" Company name must be greater than or equal to 6" )
    private String companyName;
}
