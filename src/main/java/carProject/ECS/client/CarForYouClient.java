package carProject.ECS.client;

import carProject.CCS.models.UserCredentials;
import carProject.ECS.filters.CarFilter;
import carProject.ECS.request.transformer.CarForYouRequestTransformer;
import carProject.ECS.response.transformer.CarForYouResponseTranformer;
import carProject.ECS.transporter.CarForYouTransporter;
import com.example.Jobs.model.Job;
import com.firstexample.demo.model.Car;
import org.apache.http.HttpResponse;
import org.json.JSONObject;


import java.util.List;

public class CarForYouClient extends Client {

    public List<Car> get_Cars(Job job, UserCredentials uc){
        JSONObject params=new JSONObject();
        String[] criteria = job.getCriteria().split(";");
        String[] values = job.getCriteria_value().split(";");
        for(int i=0;i<criteria.length;i++)
            params.put(criteria[i],values[i]);
        CarForYouRequestTransformer cfyrequesttransformer=new CarForYouRequestTransformer();
        CarForYouResponseTranformer cfyresponsetransformer=new CarForYouResponseTranformer();
        CarForYouTransporter cfytransporter=new CarForYouTransporter();
        CarFilter cf=new CarFilter();
        JSONObject httpjson=cfyrequesttransformer.transform(params);
        HttpResponse httpresponse=cfytransporter.transport(httpjson,uc);
        List<Car> cars=cfyresponsetransformer.get_Cars(httpresponse);
        return cf.get_Valid_Cars(params,cars);
    }

}
