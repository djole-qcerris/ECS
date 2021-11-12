package carProject.ECS.controllers;

import carProject.ECS.models.Job;
import carProject.ECS.models.ThirdParty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.RestTemplate;

public class ECSController {

    @KafkaListener(topics = "jobs")
    public void getFromKafka(Job job){

        final String uri = "http://localhost:8080/api/" + job.getTarget();

        RestTemplate restTemplate = new RestTemplate();
        ThirdParty tcp = restTemplate.getForObject(uri, ThirdParty.class);

        System.out.println(tcp);
    }

}
