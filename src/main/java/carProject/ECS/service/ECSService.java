package carProject.ECS.service;

import com.example.Jobs.model.Job;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ECSService {

    @KafkaListener(topics = "kafka_job")
    public void getFromKafka(Job job){

        final String uri = "http://localhost:8080/api/" + job.getName_of_site();
        System.out.println(uri);
      //  RestTemplate restTemplate = new RestTemplate();
      //  ThirdParty tcp = restTemplate.getForObject(uri, ThirdParty.class);

       // System.out.println(tcp);
    }

}
