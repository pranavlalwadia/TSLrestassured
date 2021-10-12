package day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherAPI {
  @Test(description="getting weather information of Specific City")
  public void getWeather() {
	  RestAssured.given()
	              .when()
	              		.get("https://api.openweathermap.org/data/2.5/weather?q=Pune&appid=a3af1477da5763e10389900c7276a8c8")
	              .then()
	              		.log()
	              		.body()
	              		.statusCode(200);
  }
  @Test(description="getting weather information of Specific City")
  public void getWeather2() {
	 Response res= RestAssured.given()
	              .when()
	              		.get("https://api.openweathermap.org/data/2.5/weather?q=Pune&appid=a3af1477da5763e10389900c7276a8c8");
	 System.out.println(res.prettyPrint());
	 System.out.println(res.getTime());
	 System.out.println(res.getStatusCode());
	 System.out.println(res.getContentType());
	 
	 Assert.assertEquals(res.getStatusCode(), 200);
	 
	 }
  @Test(enabled=false, description="Getting weather information of Specific City")
  public void getWeather3() {
                RestAssured.given()  //some Pre-Condition like authentication
                    .queryParam("q", "Pune")
                    .queryParam("appid", "a3af1477da5763e10389900c7276a8c8")
                    .when() //Performs some steps
                    .get("https://api.openweathermap.org/data/2.5/weather")
                    .then()
                    .log() //print data in console
                    .body()//printing body
                    .statusCode(200);
      }
  
  @Test(enabled=true, description="Getting weather information of Specific City")
  public void getWeather4() {
      Map<String, String> param = new HashMap<String, String >();
      param.put("q", "Pune");
      param.put("appid", "a3af1477da5763e10389900c7276a8c8");
                RestAssured.given()  //some Pre-Condition like authentication
                .queryParams(param)
              .when()                    // Performs some steps
              .get("https://api.openweathermap.org/data/2.5/weather")
              .then()            // Some Post_Condition like Verification
              .log()            //Print data in Console
              .body()            //Printing body
              .statusCode(200);
      }
}