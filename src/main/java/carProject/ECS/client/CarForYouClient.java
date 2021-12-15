package carProject.ECS.client;

import carProject.CCS.models.UserCredentials;
import carProject.ECS.filters.CarFilter;
import carProject.ECS.request.transformer.CarForYouRequestTransformer;
import carProject.ECS.response.transformer.CarForYouResponseTranformer;
import carProject.ECS.transporter.CarForYouTransporter;
import com.example.Jobs.model.Job;
import com.firstexample.demo.model.Car;
import org.json.JSONObject;

import java.util.List;

public class CarForYouClient extends Client {

    public List<Car> get_Cars(Job job, UserCredentials uc){
        JSONObject params=new JSONObject();
        String[] criteria = job.getCriteria().split(";");
        String[] values = job.getCriteria_value().split(";");
        for(int i=0;i<criteria.length;i++)
            params.put(criteria[i],values[i]);
        CarForYouRequestTransformer cfyrqt=new CarForYouRequestTransformer();
        CarForYouResponseTranformer cfyrst=new CarForYouResponseTranformer();
        CarForYouTransporter cfyt=new CarForYouTransporter();
        CarFilter cf=new CarFilter();

        return cf.get_Valid_Cars(params,cfyrst.get_Cars(cfyt.transport(cfyrqt.transform(params),uc)));
    }

}
