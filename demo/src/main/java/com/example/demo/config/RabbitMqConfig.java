package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.BindingBuilder.bind;

/**
 * @author jrojas
 */
@Configuration
public class RabbitMqConfig {
    /*
    * Queue test
    *
     */
    public static final String QUEUE = "test_queue";
    public static final String EXCHANGE = "test_exchange";
    public static final String ROUTING_KEY = "test_routing_key";

    /*
    * Direct exchange values
     */
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String D_QUEUE1 = "student_queue";
    public static final String ROUTING_KEY_1 = "student";

    public static final String D_QUEUE2 = "teacher_queue";
    public static final String ROUTING_KEY_2 = "teacher";

    public static final String D_QUEUE3 = "subject_queue";
    public static final String ROUTING_KEY_3 = "subject";

    /*
    * Fanout exchange values
     */
    public static final String F_QUEUE_1 = "fanout_queue_1";
    public static final String F_QUEUE_2 = "fanout_queue_2";
    public static final String F_QUEUE_3 = "fanout_queue_3";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";

    /*
    * Topic exchange values
     */
    public static final String T_QUEUE_1 = "t_student_queue";
    public static final String T_QUEUE_2 = "t_subject_queue";
    public static final String T_QUEUE_3 = "t_teacher_queue";
    public static final String T_QUEUE_4 = "t_total_queue";
    public static final String T_QUEUE_5 = "t_student_teacher_queue";
    public static final String TOPIC_EXCHANGE = "topic_exchange";

    public static final String T_ROUTING_KEY1= "topic.student";
    public static final String T_ROUTING_KEY2= "topic.subject";
    public static final String T_ROUTING_KEY3= "topic.teacher";
    public static final String T_ROUTING_KEY4= "topic.*";
    public static final String T_ROUTING_KEY5= "topic.#";

    /*
    *
    * DirectExchage
     */
    @Bean
    public Queue queue() {

        return new Queue(QUEUE);
    }
    @Bean
    public Queue deQueue() {

        return new Queue(D_QUEUE1);
    }
    @Bean
    public Queue deQueue2() {

        return new Queue(D_QUEUE2);
    }
    @Bean
    public Queue deQueue3() {

        return new Queue(D_QUEUE3);
    }

    @Bean
    public DirectExchange exchange() {

        return new DirectExchange(EXCHANGE);
    }
    @Bean
    public DirectExchange direct_exchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }
    @Bean
    public Binding binding1(Queue deQueue, DirectExchange direct_exchange) {
        return bind(deQueue)
                .to(direct_exchange)
                .with(ROUTING_KEY_1);
    }
    @Bean
    public Binding binding2(Queue deQueue2, DirectExchange direct_exchange) {
        return bind(deQueue2)
                .to(direct_exchange)
                .with(ROUTING_KEY_2);
    }
    @Bean
    public Binding binding3(Queue deQueue3, DirectExchange direct_exchange) {
        return bind(deQueue3)
                .to(direct_exchange)
                .with(ROUTING_KEY_3);
    }



    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
    /*
    * FanoutExchange
     */
    @Bean
    public Queue fQueue1(){
        return  new Queue(F_QUEUE_1);
    }

    @Bean
    public Queue fQueue2(){
        return new Queue(F_QUEUE_2);
    }

    @Bean
    public Queue fQueue3(){
        return new Queue(F_QUEUE_3);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    @Bean
    public Binding fanoutQ1 (Queue fQueue1, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fQueue1).to(fanoutExchange);
    }
    @Bean
    public Binding fanoutQ2 (Queue fQueue2, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fQueue2).to(fanoutExchange);
    }
    @Bean
    public Binding fanoutQ3 (Queue fQueue3, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fQueue3).to(fanoutExchange);
    }


    /*
    * Topic exchange
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Queue teQueue(){
        return new Queue(T_QUEUE_1);
    }
    @Bean
    public Queue teQueue2(){
        return new Queue(T_QUEUE_2);
    }
    @Bean
    public Queue teQueue3(){
        return new Queue(T_QUEUE_3);
    }
    @Bean
    public Queue teQueue4(){
        return new Queue(T_QUEUE_4);
    }
    @Bean
    public Queue teQueue5(){
        return new Queue(T_QUEUE_5);
    }

    @Bean
    public Binding tBinding (Queue teQueue, TopicExchange topicExchange){
        return BindingBuilder
                .bind(teQueue)
                .to(topicExchange)
                .with(T_ROUTING_KEY1);
    }
    @Bean
    public Binding tBinding2 (Queue teQueue2, TopicExchange topicExchange){
        return BindingBuilder
                .bind(teQueue2)
                .to(topicExchange)
                .with(T_ROUTING_KEY2);
    }
    @Bean
    public Binding tBinding3 (Queue teQueue3, TopicExchange topicExchange){
        return BindingBuilder
                .bind(teQueue3)
                .to(topicExchange)
                .with(T_ROUTING_KEY3);
    }
    @Bean
    public Binding tBinding4 (Queue teQueue4, TopicExchange topicExchange){
        return BindingBuilder
                .bind(teQueue4)
                .to(topicExchange)
                .with(T_ROUTING_KEY4);
    }
    @Bean
    public Binding tBinding5 (Queue teQueue5, TopicExchange topicExchange){
        return BindingBuilder
                .bind(teQueue5)
                .to(topicExchange)
                .with(T_ROUTING_KEY5);
    }
}
