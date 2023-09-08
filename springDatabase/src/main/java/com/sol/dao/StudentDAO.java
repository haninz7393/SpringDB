package com.sol.dao;

import java.util.List;
import com.sol.models.Student;

public interface StudentDAO {
	
	List<Student> getAllStudents();
	void saveStudent(Student student);
	Student getStudentById(int id);
	int delete(int id);
	int update(Student student);

}
