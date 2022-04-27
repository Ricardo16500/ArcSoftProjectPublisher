package com.example.demo.dto;

import com.example.demo.entity.Student;

/**
 * @author jrojas
 */
public class StudentDto {
    private int studentId;
    private String name;
    private String lastName;
    private String email;

    public StudentDto() {
    }

    public StudentDto(Student student) {
        studentId = student.getStudentId();
        name = student.getName();
        lastName = student.getLastName();
        email = student.getEmail();
    }

    public StudentDto(int studentId, String name, String lastName, String email) {
        this.studentId = studentId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
