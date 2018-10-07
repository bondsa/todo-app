package io.interglobe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.interglobe.dto.TodoAppDTO;
import io.interglobe.repository.TodoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {

	@Autowired
	TodoRepository todoRepository;

	// Get all the list of todos
	@GetMapping("/todos")
	public List<TodoAppDTO> getAllTodos() {
		List<TodoAppDTO> todo = new ArrayList<>();
		return todoRepository.findAllByOrderByIdDesc();
	}

	// save a new todo with default value false
	@PostMapping("/todos")
	public TodoAppDTO createTodo(@Valid @RequestBody TodoAppDTO todo) {
		todo.setCompleted(false);
		return todoRepository.save(todo);
	}

	// find a todo based on id
	@GetMapping(value = "/todos/{id}")
	public ResponseEntity<TodoAppDTO> getTodoById(@PathVariable("id") int id) {
		TodoAppDTO todo = todoRepository.findById(id);
		if (Objects.nonNull(todo)) {
			return new ResponseEntity<>(todo, HttpStatus.OK);
		}
		return new ResponseEntity<>(todo, HttpStatus.NOT_FOUND);
	}

	// update todo title or status
	@PutMapping(value = "/todos/{id}")
	public ResponseEntity<TodoAppDTO> updateTodo(@PathVariable("id") int id, @Valid @RequestBody TodoAppDTO todo) {
		TodoAppDTO todoData = todoRepository.findById(id);
		if (Objects.nonNull(todoData)) {
			todoData.setTitle(todo.getTitle());
			todoData.setCompleted(todo.getCompleted());
			TodoAppDTO updatedTodo = todoRepository.save(todoData);
			return ResponseEntity.ok().body(updatedTodo);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// delete a todo
	@DeleteMapping(value = "/todos/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable("id") int id) {
		TodoAppDTO todoData = todoRepository.findById(id);
		if (Objects.nonNull(todoData)) {
			todoRepository.delete(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}