package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	// Adding new data to database using PostMapping
	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
		System.out.print(student);

	}

	public void deleteStudent(Long studentid) {
		boolean exists = studentRepository.existsById(studentid);

		if (!exists) {
			throw new IllegalStateException("Student with id " + studentid + " doesn't exist");
		}
		studentRepository.deleteById(studentid);
	}

	// Update
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalStateException("student with id " + studentId + "doesn't exist"));
		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name); 

		}
		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if (studentOptional.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			student.setEmail(email);

		}
	}

}
/*
 * List.of(new Student(1L, 21, "Adma", "adma@gmail.com", LocalDate.of(2000, 1,
 * 1)), new Student(1L, 21, "Adma", "adma@gmail.com", LocalDate.of(2000, 1, 1))
 * );
 */