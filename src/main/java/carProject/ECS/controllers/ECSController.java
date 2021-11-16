package carProject.ECS.controllers;

import carProject.ECS.models.ThirdParty;
import com.example.Jobs.model.Job;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.RestTemplate;

public class ECSController {

    @KafkaListener(topics = "jobs")
    public void getFromKafka(Job job){

        final String uri = "http://localhost:8080/api/" + job.getName_of_site();

        RestTemplate restTemplate = new RestTemplate();
        ThirdParty tcp = restTemplate.getForObject(uri, ThirdParty.class);

        System.out.println(tcp);
    }

}
