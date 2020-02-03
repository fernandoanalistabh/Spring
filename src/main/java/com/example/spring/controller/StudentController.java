package com.example.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.Student;
import com.example.spring.repository.StudentRepository;
import com.example.spring.service.StudentService;
import com.example.spring.utils.Constants;

@RestController
public class StudentController {

	@Autowired
	StudentService service;

	@GetMapping("/student")
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> students = service.findStudents();
		if (students != null)
			return new ResponseEntity<>(students, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/student/{id}")
	public ResponseEntity<?> getStudent(@PathVariable("id") String id) {
		if (id != null) {
			Student student = service.findStudentById(Long.parseLong(id));
			if (student != null)
				return new ResponseEntity<>(student, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("É necessário enviar um id do estudante.", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/student")
	public ResponseEntity<String> postStudent(@RequestBody Student student) {
		if (student != null) {
			int result = service.createStudent(student);
			if (result == 1)
				return new ResponseEntity<>(Constants.ESTUDANTE_CRIADO, HttpStatus.OK);
			return new ResponseEntity<>(Constants.ESTUDANTE_NAO_CRIADO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(Constants.ERRO_ENVIO, HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/student")
	public ResponseEntity<String> putStudent(@RequestBody Student student) {
		if (student != null) {
			int result = service.updateStudent(student);
			if (result == 1)
				return new ResponseEntity<>(Constants.ESTUDANTE_ATUALIZADO, HttpStatus.OK);
			return new ResponseEntity<>(Constants.ESTUDANTE_NAO_EXISTE, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(Constants.ERRO_ENVIO, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("student/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) {
		if (id != null) {
			int result = service.deleteStudent(id);
			if (result == 1)
				return new ResponseEntity<>("Estudante apagado.", HttpStatus.OK);
			return new ResponseEntity<>(Constants.ESTUDANTE_NAO_EXISTE, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(Constants.ERRO_ENVIO, HttpStatus.BAD_REQUEST);
	}
}