package carProject.ECS.request.transformer;

import com.firstexample.demo.dto.ListOfCarsDTO;
import org.json.JSONArray;
import org.json.JSONObject;

public class CarForYouRequestTransformer {

    public JSONObject transform(JSONObject query_params){
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


        Integer year_converted;
        ListOfCarsDTO loc=new ListOfCarsDTO();
        if(year==null)year_converted=null;
        else year_converted=Integer.parseInt(year);
        try {
            JSONObject jsonobj = new JSONObject();
            JSONObject query = new JSONObject();
            if(year_converted!=null){
            query.put("firstRegistrationYearFrom", year_converted);
            query.put("firstRegistrationYearTo", year_converted);
            }
            JSONObject makeModel = new JSONObject();
            JSONArray listmmt = new JSONArray();
            listmmt.put(makeModel);
            makeModel.put("makeKey", brand);
            makeModel.put("modelKey", model);
            query.put("makeModelType", listmmt);
            jsonobj.put("query", query);

            return jsonobj;
        }catch (Exception e){}
        return null;
    }
}
