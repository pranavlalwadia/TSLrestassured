package day2;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector.Matcher;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;
public class Github {
	 @BeforeTest
	    public void beforeTest()
	    {
	        baseURI="https://api.github.com/user/repos";
	        authentication=RestAssured.oauth2("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");
	        }
	 @Test(enabled=true)
	  public void gettingAllRepositories() {
	      given()
	              .auth()
	              .oauth2("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")
	          .when()
	              .get()
	          .then()
	              .log()
	              .body()
	              .statusCode(200);
	  }
	      @Test(enabled=true)
	      public void createRepositories() {
	          JSONObject data = new JSONObject();

	            data.put("name", "RestAssuredCreations2");
	            data.put("description", "Created By RestAssured Tool");
	            data.put("homepage", "https://github.com/pranavlalwadia");

	          given()
	                  .auth()
	                  .oauth2("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")
	                  .header("Content-Type", "application/json")
	                  .body(data.toJSONString())
	              .when()
	                  .post()
	              .then()
	                  .log()
	                  .body()
	                  .statusCode(201);
	      }
}
