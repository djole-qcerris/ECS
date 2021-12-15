package carProject.ECS.client;

import carProject.CCS.models.UserCredentials;
import com.example.Jobs.model.Job;
import com.firstexample.demo.model.Car;

import java.util.List;

public abstract class Client {
    public abstract  List<Car> get_Cars(Job job, UserCredentials uc);
}
