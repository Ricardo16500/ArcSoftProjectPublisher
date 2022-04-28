package com.example.demo.api;

import com.example.demo.bl.StudentBl;
import com.example.demo.config.RabbitMqConfig;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jrojas
 */
@RestController
@RequestMapping("/v1/api/students")
public class StudentController {

    private static Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private StudentBl studentBl;

    @Value("${key}")
    String key;

    @Autowired
    public StudentController(StudentBl studentBl) {
        this.studentBl = studentBl;
    }

    @Autowired
    private RabbitTemplate template;

    @RequestMapping(value = "/paginate", method = RequestMethod.GET)
    public ResponseEntity<PageImpl<StudentDto>> getStudentsPaginate(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        LOGGER.info("Invocando al servicio REST para obtener el listado de estudiantes con KEY: {}", key);
        PageImpl<StudentDto> studentList = studentBl.getStudentsPaginate(page, size);
        LOGGER.info("Invocacion exitosa para obtener el listado de estudiantes {}", studentList);

        template.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.ROUTING_KEY_1, studentList);
        template.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "",studentList);
        template.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE,RabbitMqConfig.T_ROUTING_KEY1,studentList);

        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getStudents() {
        LOGGER.info("Invocando al servicio REST para obtener el listado de estudiantes con KEY: {}", key);
        List<Student> studentList = studentBl.getStudents();
        LOGGER.info("Invocacion exitosa para obtener el listado de estudiantes {}", studentList);

        template.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.ROUTING_KEY_1, studentList);
        template.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "",studentList);
        template.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE,RabbitMqConfig.T_ROUTING_KEY1,studentList);


        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentsById(@PathVariable Long id) {
        LOGGER.info("Invocando al servicio REST para obtener el estudiante con id: {}", id);
        Student student = studentBl.getStudentById(id);
        LOGGER.info("Invocacion exitosa para obtener el estudiantes {}", student);

        template.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.ROUTING_KEY_1, student);
        template.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "",student);
        template.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE,RabbitMqConfig.T_ROUTING_KEY1,student);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        LOGGER.info("Invocando al servicio REST para registrar un estudiante con la siguiente información: {}", student);
        Student result = studentBl.saveStudent(student);
        List<Student> studentList = new ArrayList<>();
        studentList.add(result);
        template.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.ROUTING_KEY_1, studentList);
        template.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "",studentList);
        template.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE,RabbitMqConfig.T_ROUTING_KEY1,studentList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
