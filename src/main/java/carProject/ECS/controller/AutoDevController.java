package carProject.ECS.controller;


import carProject.ECS.service.CarService;
import com.firstexample.demo.dto.ListOfCarsDTO;
import com.firstexample.demo.model.Car;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.kafka.common.protocol.types.Field;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class AutoDevController {

    private final CarService carservice;


    public AutoDevController(){
        this.carservice=new CarService();
    }

    public ListOfCarsDTO search_cars(JSONObject jsonparams) {
        String year=null;
        String model=null;
        String brand=null;
        try {
            year=jsonparams.getString("year");
        }catch (Exception e){

        }
        try {
            model=jsonparams.getString("model");
        }catch (Exception e){

        }
        try {
            brand=jsonparams.getString("brand");
        }catch (Exception e){

        }



        Integer year_converted;
        ListOfCarsDTO loc=new ListOfCarsDTO();
        if(year==null)year_converted=null;
        else year_converted=Integer.parseInt(year);
        try {

            String q="make="+brand+"&model="+model+"&year_min="+year+"&apikey="+jsonparams.getString("apikey");
            String url="https://auto.dev/api/listings?"+ URLEncoder.encode(q, StandardCharsets.UTF_8);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);


            HttpResponse response = httpClient.execute(request);
            //   System.out.println(response.getStatusLine().getStatusCode());

            String json = EntityUtils.toString(response.getEntity());
            //       System.out.println(json);
            JSONObject temp1 = new JSONObject(json);
            JSONArray jarray=(JSONArray)temp1.get("records");
            List<Car> cars=new ArrayList<>();
            for(int i=0;i<jarray.length();i++){
                cars.add(this.carservice.create_Car_From_Json_Auto_Dev(jarray.getJSONObject(i)));
            }
            loc.setCars(cars);
        }
        catch (Exception e){}


        return loc;

    }

}
