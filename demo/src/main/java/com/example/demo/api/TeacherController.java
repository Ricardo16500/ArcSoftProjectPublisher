package com.example.demo.api;

import com.example.demo.bl.SubjectBl;
import com.example.demo.bl.TeacherBl;
import com.example.demo.config.RabbitMqConfig;
import com.example.demo.entity.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jrojas
 */
@RestController
@RequestMapping("/v1/api/teacher")
public class TeacherController {

    private static Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);
    private TeacherBl teacherBl;

    @Autowired
    public TeacherController(TeacherBl teacherBl) {
        this.teacherBl = teacherBl;
    }

    @Autowired
    private RabbitTemplate template;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Teacher>> getSubjects() {
        LOGGER.info("Invocando al servicio REST para obtener el listado de profesores");
        List<Teacher> teacherList = teacherBl.getTeachers();
        LOGGER.info("Invocacion exitosa para obtener el listado de materias {}", teacherList);

        template.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.ROUTING_KEY_2,teacherList);
        template.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "",teacherList);
        template.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, RabbitMqConfig.T_ROUTING_KEY3,teacherList);


        return new ResponseEntity<>(teacherList, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Teacher> getSubjectsById(@PathVariable Integer id) throws Exception {
        LOGGER.info("Invocando al servicio REST para obtener en profesor con id: {}", id);
        Teacher teacher = teacherBl.getTeacherById(id);
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacher);
        LOGGER.info("Invocacion exitosa para obtener la materia: {}", teacher);

        template.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.ROUTING_KEY_2,teacherList);
        template.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "",teacherList);
        template.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, RabbitMqConfig.T_ROUTING_KEY3,teacherList);


        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Teacher> saveSubject(@RequestBody Teacher teacher) {
        LOGGER.info("Invocando al servicio REST para registrar un profesor con la siguiente información: {}", teacher);
        Teacher result = teacherBl.saveTeacher(teacher);
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(result);

        template.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.ROUTING_KEY_2,teacherList);
        template.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "",teacherList);
        template.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, RabbitMqConfig.T_ROUTING_KEY3,teacherList);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
