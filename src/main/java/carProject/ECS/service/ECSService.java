package carProject.ECS.service;

import carProject.CCS.models.UserCredentials;
import com.example.Jobs.model.Job;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ECSService {

    @Value("${ccs.server.address}")
    private String ccsAddress;

    @KafkaListener(topics = "kafka_job")
    public void getFromKafka(Job job){

        final String uri = ccsAddress + job.getName_of_site();
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        UserCredentials uc = restTemplate.getForObject(uri, UserCredentials.class);

    }

}
