package com.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.todolist.models.Task;
import com.todolist.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        try {
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating task", e);
        }
    }

    public Task updateTask(Long id, Task updatedTaskData) {
        try {
            Optional<Task> optionalTask = taskRepository.findById(id);

            if (optionalTask.isPresent()) {
                Task existingTask = optionalTask.get();
                existingTask.setTitle(updatedTaskData.getTitle());
                existingTask.setDescription(updatedTaskData.getDescription());
                existingTask.setIsComplete(updatedTaskData.isComplete());
                existingTask.setPriority(updatedTaskData.getPriority());

                return taskRepository.save(existingTask);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id: " + id);
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating task", e);
        }
    }

	public Task deleteTask(Long taskId) {
		Optional<Task> optionalTask = taskRepository.findById(taskId);
		if (optionalTask.isPresent()) {
			Task task = optionalTask.get();
			taskRepository.delete(task);
			return task;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La tarea " + taskId + " No fue encontrada");
		}
	}

	// Un solo metodo para el el filtrado de la informacion
	public List<Task> findByFilter(Long priority, Boolean isComplete) {
		if (priority == null && isComplete == null) {
			// Caso sin filtros
			return taskRepository.findAll();
		} else if (priority != null && isComplete != null) {
			// Caso con ambos filtros
			return taskRepository.findByPriorityAndIsComplete(priority, isComplete);
		} else if (priority != null) {
			// Caso solo por prioridad
			return taskRepository.findByPriority(priority);
		} else {
			// Caso solo por estado completado
			return taskRepository.findByIsComplete(isComplete);
		}
	}
}
