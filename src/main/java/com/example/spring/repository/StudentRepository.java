package com.example.spring.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.spring.model.Student;

@Repository
public class StudentRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Student findById(long id) {
		try {
			return jdbcTemplate.queryForObject("select * from STUDENT where ID=?", new Object[] { id },
					new BeanPropertyRowMapper<Student>(Student.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Student> find() {
		return jdbcTemplate.query("select * from student", new BeanPropertyRowMapper<Student>(Student.class));
	}

	public int create(Student student) {
		return jdbcTemplate.update("insert into STUDENT(ID, NAME, PASSPORT_NUMBER) values(" + student.getId() + ", '"
				+ student.getName() + "', '" + student.getPassportNumber() + "')");
	}

	public int updateById(Long id, Student student) {
		try {
			return jdbcTemplate.update("update STUDENT set NAME='" + student.getName() + "', PASSPORT_NUMBER='"
				+ student.getPassportNumber() + "' where ID=" + id);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	public int deleteById(Long id) {
		try {
			return jdbcTemplate.update("delete from STUDENT where ID=" + id);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
}