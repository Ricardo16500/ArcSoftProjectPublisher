package com.example.demo.bl;

import com.example.demo.entity.Teacher;
import com.example.demo.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jrojas
 */
@Service
public class TeacherBl {
    private static Logger LOGGER = LoggerFactory.getLogger(TeacherBl.class);
    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherBl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers() {
        LOGGER.info("DATABASE: Iniciando consulta para obtener los profesores");
        List<Teacher> teacherList = teacherRepository.findAll();
        LOGGER.info("DATABASE-SUCCESS: Consulta exitosa para obtener el listado de profesores {}", teacherList);
        return teacherList;
    }

    public Teacher getTeacherById(Integer id) throws Exception {
        LOGGER.info("DATABASE: Iniciando consulta para obtener el profesor con id: {}", id);
        Teacher teacher = teacherRepository.findById(id).orElseThrow( () -> new Exception("No se encontro el profesor"));
        LOGGER.info("DATABASE-SUCCESS: Consulta exitosa para obtener el profesor con id:{}, retorno {}", id, teacher);
        return teacher;
    }

    public Teacher saveTeacher(Teacher teacher) {
        LOGGER.info("DATABASE: Iniciando consulta para guardar un profesor con la siguiente información: {}", teacher);
        Teacher result = teacherRepository.save(teacher);
        LOGGER.info("DATABASE-SUCCESS: Consulta exitosa para guardar profesor retorno {}", result);
        return result;
    }
}
