package carProject.ECS.service;

import carProject.CCS.models.UserCredentials;
import com.example.Jobs.model.Job;
import com.firstexample.demo.dto.ListOfCarsDTO;
import com.firstexample.demo.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ECSService {

    @Autowired
    private KafkaTemplate<String, Car> kafkaTemplate;
    private static final String TOPIC = "car_consumer";

    @Value("${ccs.server.address}")
    private String ccsAddress;

    @KafkaListener(topics = "kafka_job")
    public void getFromKafka(Job job) throws UnsupportedEncodingException {
        UserCredentials uc = getUserCredentials(job);
        List<Car> cars = getCars(uc,job);
        sendCars(cars);
    }

    private UserCredentials getUserCredentials(Job job) {
        final String uri = ccsAddress + job.getName_of_site();
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, UserCredentials.class);
    }

    private String makeQueryFromJob(Job job) throws UnsupportedEncodingException {
        String result = "?";
        String[] criteria = job.getCriteria().split(";");
        String[] values = job.getCriteria_value().split(";");
        for(int i = 0;i < criteria.length;i++)
            result += URLEncoder.encode(criteria[i], StandardCharsets.UTF_8.toString()) + "=" + URLEncoder.encode(values[i], StandardCharsets.UTF_8.toString()) + "&";
        result = result.substring(0, result.length()-1);
        return result;
    }

    private List<Car> getCars(UserCredentials uc, Job job) throws UnsupportedEncodingException {
        final String uri = uc.getApi_url() + makeQueryFromJob(job);
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        return (List<Car>) restTemplate.getForObject(uri, ListOfCarsDTO.class).getCars();
    }

    private void sendCars(List<Car> cars){
        for(Car car:cars){
            kafkaTemplate.send(TOPIC,car);
        }
    }

}
