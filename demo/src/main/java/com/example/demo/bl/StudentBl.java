package com.example.demo.bl;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jrojas
 */
@Service
public class StudentBl {
    private static Logger LOGGER = LoggerFactory.getLogger(StudentBl.class);
    private StudentRepository studentRepository;

    @Autowired
    public StudentBl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public PageImpl<StudentDto> getStudentsPaginate(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("studentId").ascending());
        LOGGER.info("DATABASE: Iniciando consulta para obtener los estudiantes");
        Page<Student> pageStudent = studentRepository.findAll(pageable);
        List<StudentDto> studentList = pageStudent.stream()
                .map(StudentDto::new)
                .collect(Collectors.toList());
        LOGGER.info("DATABASE-SUCCESS: Consulta exitosa para obtener el listado de estudiantes {}", studentList);
        return new PageImpl<>(studentList, pageable, pageStudent.getTotalElements());
    }

    public List<Student> getStudents() {
        LOGGER.info("DATABASE: Iniciando consulta para obtener los estudiantes");
        List<Student> studentList = (List<Student>) studentRepository.findAll();
        LOGGER.info("DATABASE-SUCCESS: Consulta exitosa para obtener el listado de estudiantes {}", studentList);
        return studentList;
    }

    public Student getStudentById(Long id) {
        LOGGER.info("DATABASE: Iniciando consulta para obtener estudiante con id: {}", id);
        Student student = studentRepository.getStudentById(id);
        LOGGER.info("DATABASE-SUCCESS: Consulta exitosa para obtener estudiante con id:{}, retorno {}", id, student);
        return student;
    }

    public Student saveStudent(Student student) {
        LOGGER.info("DATABASE: Iniciando consulta para guardar estudiante con la siguiente información: {}", student);
        Student result = studentRepository.save(student);
        LOGGER.info("DATABASE-SUCCESS: Consulta exitosa para guardar estudiante retorno {}", result);
        return result;
    }
}
