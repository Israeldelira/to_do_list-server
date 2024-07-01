package com.todolist.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	/**
     * Busca tareas por su estado de completitud.
     * 
     * @param isComplete Estado de completitud de la tarea (true/false)
     * @return Lista de tareas encontradas que coinciden con el estado de completitud especificado.
     */
    List<Task> findByIsComplete(Boolean isComplete);

    /**
     * Busca tareas por su prioridad.
     * 
     * @param priority Prioridad de la tarea
     * @return Lista de tareas encontradas que coinciden con la prioridad especificada.
     */
    List<Task> findByPriority(Long priority);

    /**
     * Busca tareas por su prioridad y estado de completitud.
     * 
     * @param priority Prioridad de la tarea
     * @param isComplete Estado de completitud de la tarea (true/false)
     * @return Lista de tareas encontradas que coinciden con la prioridad y el estado de completitud especificados.
     * Podemos aplicar una interfaz en dado caso que crezcan estos valores como una interfaz de filtros para mandar un solo parametro
     */
    List<Task> findByPriorityAndIsComplete(Long priority, Boolean isComplete);
}