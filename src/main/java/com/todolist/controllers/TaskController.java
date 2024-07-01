package com.todolist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.todolist.models.Task;
import com.todolist.services.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	// EndPoint to get all tasks
	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks() {
		// Retrieve all tasks from the service
		List<Task> tasks = taskService.getAllTasks();
		// Return tasks with HTTP status OK
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	// EndPoint to create a new task
	@PostMapping
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		// Create a new task with the provided description
		Task createdTask = taskService.createTask(task);
		// Return the created task with HTTP status CREATED
		return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
	}

	// EndPoint to update a task by id
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id,@RequestBody Task taskUpdate) {
		// Update the task with the provided id
		Task updatedTask = taskService.updateTask(id,taskUpdate);
		// Return the updated task with HTTP status OK
		return new ResponseEntity<>(updatedTask, HttpStatus.OK);
	}

	// EndPoint to delete a task by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		// Delete the task with the provided id
		taskService.deleteTask(id);
		// Return NO_CONTENT status when the task is successfully deleted
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/filter")
	public List<Task> filterTasks(
            @RequestParam(name = "priority", required = false) Long priority,
            @RequestParam(name = "isComplete", required = false) Boolean isComplete
    ) {
        return taskService.findByFilter(priority, isComplete);
    }
}
