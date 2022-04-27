package com.example.demo.repository;

import com.example.demo.entity.Student;
import com.example.demo.entity.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author jrojas
 */
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Integer> {
}
