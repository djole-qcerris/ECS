package carProject.ECS.filters;

import com.example.Jobs.model.Job;
import com.firstexample.demo.dto.ListOfCarsDTO;
import com.firstexample.demo.model.Car;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CarFilter {
    public List<Car> get_Valid_Cars(JSONObject query_params, List<Car> returned_cars){
        String year=null;
        String model=null;
        String brand=null;
        try {
            year=query_params.getString("year");
        }catch (Exception e){

        }
        try {
            model=query_params.getString("model");
        }catch (Exception e){

        }
        try {
            brand=query_params.getString("brand");
        }catch (Exception e){

        }
        Integer year_converted=null;
        ListOfCarsDTO loc=new ListOfCarsDTO();
        if(year==null)year_converted=null;
        else {
            year_converted=Integer.parseInt(year);
        }
        List<Car> filtered_car_list=new ArrayList<>();

        for(Car car:returned_cars){
            boolean is_good=true;
            if(model!=null){
                if(model.toLowerCase().compareTo(car.getModel().toLowerCase())!=0)is_good=false;
            }
            if(brand!=null){
                if(brand.toLowerCase().compareTo(car.getBrand().toLowerCase())!=0)is_good=false;
            }
            if(year_converted!=null){
                if(car.getProductionDate().getYear()!=year_converted)is_good=false;
            }

            if(is_good)filtered_car_list.add(car);
        }
        return filtered_car_list;
    }

}
