import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class APITest1 {
    @Test
    void checkResponse() {
        Response response = RestAssured.get("https://amock.io/api/enugalak/v1.1/gtfs/alerts/sydneytrains");
        System.out.println(response.getStatusCode());
        System.out.println(response.asPrettyString());
    }

    @Test
    void parseJSON(){
        Response response = RestAssured.get("https://amock.io/api/enugalak/v1.1/gtfs/alerts/sydneytrains");
        JsonPath j = new JsonPath(response.asString());
        System.out.println(j.getList("entity.id"));
        //System.out.println(j.getList("entity.alert.informed_entity.trip.trip_id"));
        System.out.println(j.getString("entity[1].alert.informed_entity.trip.trip_id"));
        //System.out.println(j.getList("entity.alert.informed_entity"));
        Object dataObject = com.jayway.jsonpath.JsonPath.parse(response.asString()).read("$[?(@.id == '1')]");
        String dataString = dataObject.toString();
        //System.out.println(dataString);
        System.out.println(j.getList("entity[?(@.id == '1')]"));

    }

}
