package com.example.exercisevalidation2.Controller;


import com.example.exercisevalidation2.Api.ApiResponse;
import com.example.exercisevalidation2.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList<Event> events=new ArrayList<>();

    //Read
    @GetMapping("/events")
    public ResponseEntity getEvents(){
        return ResponseEntity.status(200).body(events);
    }

    //Add
    @PostMapping("/add")
    public ResponseEntity createEvent(@Valid  @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(201).body(new ApiResponse("Event added successfully"));
    }

    //Update
    @PutMapping("/update/{id}")
    public ResponseEntity updateEvent(@PathVariable int id,@Valid @RequestBody Event event, Errors errors){
        if (errors.hasErrors()) {
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        for(int i=0; i<events.size(); i++){
            if(events.get(i).getId()==id){
                events.set(i,event);
                return ResponseEntity.status(201).body(new ApiResponse("Event updated successfully"));
            }
        }
        return ResponseEntity .status(201).body(new ApiResponse("Event not found"));
    }


    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable int id){
        for(int i=0; i<events.size(); i++){
            if(events.get(i).getId()==id){
                events.remove(i);
                return ResponseEntity.status(201).body(new ApiResponse("Event deleted successfully"));
            }
        }
        return ResponseEntity .status(201).body(new ApiResponse("Event not found"));
    }

    //Change Capacity
    @PutMapping("/change-capacity/{id}/{capacity}")
    public ResponseEntity changeEventCapacity(@PathVariable int id,@PathVariable int capacity){
//        for(int i=0; i<events.size(); i++){
//            if(events.get(i).getId()==id){
//                events.get(i).setCapacity(capacity);
//                return new ApiResponse("Event updated");
//            }
//        }
//        return new ApiResponse("Event not found");

        for (Event event : events) {
            if (event.getId() == id) {
                event.setCapacity(capacity);
                return ResponseEntity.ok(new ApiResponse("Event updated successfully"));
            }
        }
        return ResponseEntity.status(404).body(new ApiResponse("Event not found"));
    }


    //Search by given id
    @GetMapping("/search/{id}")
    public ResponseEntity searchEvent(@PathVariable int id){
//        for(int i=0; i<events.size(); i++){
//            if(events.get(i).getId()==id){
//                return new ApiResponse("Event found :"+events.get(i).getDescription());
//            }
//        }
//        return new ApiResponse("Event not found");
        for(int i=0; i<events.size(); i++){
            if(events.get(i).getId()==id){
                return ResponseEntity.status(201).body(new ApiResponse("Event found"));
            }
        }
        return ResponseEntity .status(201).body(new ApiResponse("Event not found"));

    }

}

