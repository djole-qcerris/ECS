package carProject.ECS.response.transformer;

import carProject.CCS.models.UserCredentials;
import com.firstexample.demo.model.Car;
import com.firstexample.demo.model.CarChassis;
import com.firstexample.demo.model.EngineType;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CarForYouResponseTranformer {

    public List<Car> get_Cars(HttpResponse response){
        try {


            String json = EntityUtils.toString(response.getEntity());
            //       System.out.println(json);
            JSONObject temp1 = new JSONObject(json);
            JSONArray jarray = (JSONArray) temp1.get("content");
            List<Car> cars = new ArrayList<>();
            for (int i = 0; i < jarray.length(); i++) {
                cars.add(this.create_Car_From_Json_Car_for_you(jarray.getJSONObject(i)));
            }

            return cars;
        }catch (Exception e){}

        return null;
    }

    public Car create_Car_From_Json_Car_for_you(JSONObject json)  {
        Car car=new Car();
        EngineType et=new EngineType();
        CarChassis cc=new CarChassis();
        car.setChassis(cc);
        car.setEngineType(et);

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
        try {
            et.setMotorPower(json.getInt("kiloWatts"));
        }catch (Exception e) {
            et.setMotorPower(-1);
        }
        try {
            et.setHPower(json.getInt(" horsePower"));
        }catch (Exception e){
            et.setHPower(-1);
        }
        try {
            car.setId(json.getLong(" id"));
        }catch (Exception e){
            car.setId(0L);
        }

        return car;
    }
}
