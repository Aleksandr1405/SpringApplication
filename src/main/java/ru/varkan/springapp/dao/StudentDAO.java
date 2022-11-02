package ru.varkan.springapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.varkan.springapp.models.Student;

import java.util.List;


@Component
public class StudentDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> index() {
        return jdbcTemplate.query("SELECT * FROM Student", new BeanPropertyRowMapper<>(Student.class));
    }

    public Student show(int id) {
        return jdbcTemplate.query("SELECT * FROM Student WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Student.class))
                .stream().findAny().orElse(null);
    }

    public void save(Student student) {
        jdbcTemplate.update("INSERT INTO Person(name, age, email) VALUES(?, ?, ?)", student.getName(), student.getAge());
    }

    public void update(int id, Student updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Student WHERE id=?", id);
    }
}
