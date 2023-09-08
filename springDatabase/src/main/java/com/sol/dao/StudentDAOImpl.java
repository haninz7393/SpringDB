package com.sol.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.sol.models.Student;
import com.sol.rowmapper.StudentRowMapper;

public class StudentDAOImpl implements StudentDAO {

	private JdbcTemplate jdbcTemplate;

	public StudentDAOImpl(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@Override
	public List<Student> getAllStudents() {
		String sql = "SELECT * FROM `student`";
		List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());
		return students;
	}

	@Override
	public void saveStudent(Student student) {
		Object[] studentInfo = { student.getStudent_id(), student.getName(), student.getCity(), student.getAge() };

		String sql = "INSERT INTO `students`.`student` (`student_id`, `name`, `city`, `age`) " + "VALUES (?, ?, ?, ?);";

		jdbcTemplate.update(sql, studentInfo);
		System.out.println("Insered successfully!");

	}

	@Override
	public Student getStudentById(int id) {
		String sql = "SELECT `id`, `student_id`, `name`, `city`, `age` FROM `student` WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new StudentRowMapper());

	}

	@Override
	public int delete(int id) {
		String sql = "DELETE from `students`.`student` WHERE `id`=" + id;
		return jdbcTemplate.update(sql);
	}

	@Override
	public int update(Student student) {
		String sql = "UPDATE `students`.`student` SET `student_id` ='" + student.getStudent_id() + "', `name` = '"
				+ student.getName() + "', `city` = '" + student.getCity() + "', `age` =  " + student.getAge()
				+ " WHERE `id` = " + student.getId() + ";";

		return jdbcTemplate.update(sql);
	}

}
