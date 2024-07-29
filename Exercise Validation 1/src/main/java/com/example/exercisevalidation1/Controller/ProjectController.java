package com.example.exercisevalidation1.Controller;
import com.example.exercisevalidation1.Api.ApiResponse;
import com.example.exercisevalidation1.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/project")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    //Read - display all project
    @GetMapping("/get")
    public ResponseEntity getProjects() {
        return ResponseEntity.status(200).body(projects);
    }

    //Add
    @PostMapping("/add")
    public ResponseEntity createProject(@Valid @RequestBody Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(201).body(new ApiResponse("Project created successfully :" + project.getTitle()));
    }

    //Update
    @PutMapping("/update/{id}")
    public ResponseEntity updateProject(@Valid @PathVariable int id, @Valid @RequestBody Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getId() == id) {
                projects.set(i, project);
                return ResponseEntity.status(201).body(new ApiResponse("Project updated successfully :" + project.getTitle()));
            }
        }
        return ResponseEntity.status(201).body(new ApiResponse("Project not found :" + project.getTitle()));
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProject(@PathVariable int id) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getId() == id) {
                projects.remove(i);
                return ResponseEntity.status(201).body(new ApiResponse("Project deleted successfully"));
            }
        }
        return ResponseEntity.status(201).body(new ApiResponse("Project not found"));
    }


    //Change status
    @PutMapping("/change-status/{id}")
    public ResponseEntity changeStatus(@Valid @PathVariable int id) {

        for (Project project : projects) {
            if (project.getId() == id) {
                switch (project.getStatus()) {
                    case "Not Started":
                        project.setStatus("In Progress");
                        break;
                    case "In Progress":
                        project.setStatus("Completed");
                        break;
                    case "Completed":
                        return ResponseEntity.status(400).body(new ApiResponse("Project is already completed"));
                    default:
                        return ResponseEntity.status(400).body(new ApiResponse("Invalid status"));
                }
                return ResponseEntity.status(200).body(new ApiResponse("Project status changed to " + project.getStatus()));
            }
        }
        return ResponseEntity.status(404).body(new ApiResponse("Project not found"));
    }

//Search
    @GetMapping("/search/{title}")
    public ResponseEntity searchProjectByTitle(@PathVariable String title) {
        for (Project project : projects) {
            if (project.getTitle().equalsIgnoreCase(title)) {
                return ResponseEntity.status(200).body(new ApiResponse("Project is found :" + project));
            }
        }
        return ResponseEntity.status(201).body(new ApiResponse("Project not found"));
    }

    //Search by company name
    @GetMapping("/company/{companyName}")
    public ResponseEntity getProjectsByCompanyName(@PathVariable String companyName) {
        List<Project> companyProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equalsIgnoreCase(companyName)) {
                companyProjects.add(project);
            }
        }
        return ResponseEntity.status(200).body(companyProjects);
    }

}


