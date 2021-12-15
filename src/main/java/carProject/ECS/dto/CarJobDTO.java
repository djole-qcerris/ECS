package carProject.ECS.dto;

import com.example.Jobs.model.Job;
import com.firstexample.demo.model.Car;

public class CarJobDTO {
    private Job job;
    private Car car;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public CarJobDTO(Job job, Car car) {
        this.job = job;
        this.car = car;
    }
    public CarJobDTO(){}
}
