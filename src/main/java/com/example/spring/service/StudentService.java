package com.example.spring.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.model.Student;
import com.example.spring.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository repository;
	
	public List<Student> findStudents() {
		return repository.find();
	}
	
	public Student findStudentById(Long id) {
		return repository.findById(id);
	}
	
	public int createStudent(Student newStudent) {
		return repository.create(newStudent);
	}
	
	public int updateStudent(Student student) {
		Student storagedStudent = null;
		if (student.getId()!=null) {
			storagedStudent = findStudentById(student.getId());
			if (storagedStudent!=null) {
				BeanUtils.copyProperties(student, storagedStudent, "id");
				return repository.updateById(storagedStudent.getId(), storagedStudent);
			}
		}
		return 0;
	}
	
	public int deleteStudent(Long id) {
		return repository.deleteById(id);
	}
}	