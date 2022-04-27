package com.example.demo.bl;

import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jrojas
 */
@Service
public class SubjectBl {
    private static Logger LOGGER = LoggerFactory.getLogger(SubjectBl.class);
    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectBl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjects() {
        LOGGER.info("DATABASE: Iniciando consulta para obtener las materias");
        List<Subject> subjectList = subjectRepository.findAll();
        LOGGER.info("DATABASE-SUCCESS: Consulta exitosa para obtener el listado de materias {}", subjectList);
        return subjectList;
    }

    public Subject getSubjectById(Integer id) throws Exception {
        LOGGER.info("DATABASE: Iniciando consulta para obtener estudiante con id: {}", id);
        Subject subject = subjectRepository.findById(id).orElseThrow( () -> new Exception("No se encontro la materia"));
        LOGGER.info("DATABASE-SUCCESS: Consulta exitosa para obtener estudiante con id:{}, retorno {}", id, subject);
        return subject;
    }

    public Subject saveSubject(Subject subject) {
        LOGGER.info("DATABASE: Iniciando consulta para guardar una materia con la siguiente información: {}", subject);
        Subject result = subjectRepository.save(subject);
        LOGGER.info("DATABASE-SUCCESS: Consulta exitosa para guardar estudiante retorno {}", result);
        return result;
    }
}
