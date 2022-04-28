package com.example.demo.api;

import com.example.demo.bl.SubjectBl;
import com.example.demo.config.RabbitMqConfig;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
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
@RequestMapping("/v1/api/subject")
public class SubjectController {

    private static Logger LOGGER = LoggerFactory.getLogger(SubjectController.class);
    private SubjectBl subjectBl;

    @Autowired
    public SubjectController(SubjectBl subjectBl) {
        this.subjectBl = subjectBl;
    }

    @Autowired
    private RabbitTemplate template;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Subject>> getSubjects() {
        LOGGER.info("Invocando al servicio REST para obtener el listado de materias");
        List<Subject> subjectList = subjectBl.getSubjects();
        LOGGER.info("Invocacion exitosa para obtener el listado de materias {}", subjectList);

        template.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE,RabbitMqConfig.ROUTING_KEY_3, subjectList);
        template.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "", subjectList);
        template.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE,RabbitMqConfig.T_ROUTING_KEY2,subjectList);

        return new ResponseEntity<>(subjectList, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Subject> getSubjectsById(@PathVariable Integer id) throws Exception {
        LOGGER.info("Invocando al servicio REST para obtener la materia con id: {}", id);
        Subject subjectList = subjectBl.getSubjectById(id);

        template.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE,RabbitMqConfig.ROUTING_KEY_3, subjectList);
        template.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "", subjectList);
        template.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE,RabbitMqConfig.T_ROUTING_KEY2,subjectList);

        return new ResponseEntity<>(subjectList, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Subject> saveSubject(@RequestBody Subject subject) {
        LOGGER.info("Invocando al servicio REST para registrar una materia con la siguiente información: {}", subject);
        Subject result = subjectBl.saveSubject(subject);
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(result);

        template.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE,RabbitMqConfig.ROUTING_KEY_3, subjectList);
        template.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "", subjectList);
        template.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE,RabbitMqConfig.T_ROUTING_KEY2,subjectList);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
