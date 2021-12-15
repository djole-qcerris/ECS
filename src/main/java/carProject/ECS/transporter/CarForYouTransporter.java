package carProject.ECS.transporter;

import carProject.CCS.models.UserCredentials;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class CarForYouTransporter {

    public HttpResponse transport(JSONObject json, UserCredentials uc){
        if(json==null)return null;
        try {


            StringEntity entity = new StringEntity(json.toString(),
                    ContentType.APPLICATION_JSON);

            HttpClient httpClient = HttpClientBuilder.create().build();
            String url=uc.getApi_url();
            HttpPost request = new HttpPost(url);
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            return response;
        }
        catch (Exception e){}
        return null;
    }

}
