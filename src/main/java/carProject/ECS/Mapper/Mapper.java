package carProject.ECS.Mapper;

import carProject.ECS.client.CarForYouClient;
import carProject.ECS.client.Client;

public class Mapper {

    public Client get_Client(String s){
        if(s.equals("car_for_you"))
            return new CarForYouClient();

        return null;
    }
}
