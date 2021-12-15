package carProject.ECS.service;

import com.firstexample.demo.model.Car;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


public class CarService {

    public Car create_Car_From_Json_Car_for_you(JSONObject json)  {
        Car car=new Car();



        try {
            car.setBrand(json.getString("make"));
        }catch (Exception e){
            car.setBrand(null);
        }
        try {
            car.setModel(json.getString("model"));
        }catch (Exception e){
            car.setModel(null);
        }
        try {
            car.setMileage((double)(json.getInt("mileage")));
        }catch (Exception e){
            car.setMileage((double)(-1));
        }


        return car;
    }
    public Car create_Car_From_Json_Auto_Dev(JSONObject json)  {
        Car car=new Car();



        try {
            car.setBrand(json.getString("make"));
        }catch (Exception e){
            car.setBrand(null);
        }
        try {
            car.setModel(json.getString("model"));
        }catch (Exception e){
            car.setModel(null);
        }
        try {
            car.setMileage((double)(json.getInt("mileage")));
        }catch (Exception e){
            car.setMileage((double)(-1));
        }


        return car;
    }
}
