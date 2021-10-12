package day2;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PositiveContactListTest {
	String id ;
	@Test(enabled=false,description="Getting all contact List")
	public void getAllContactListInfo() {
		given()
		 .when()
		 	   .get("http://3.13.86.142:3000/contacts\r\n")
		 .then()
		       .log()
		       .body()
		       .statusCode(200);
	}
	@Test(enabled=false,description="Getting specific Contact")
	public void getSpecificContact() {
		given()
		 .when()
		       .get("http://3.13.86.142:3000/contacts/60eba23e170734047659ad54")
		  .then()
		        .log()
		        .body()
		        .statusCode(200);
	}
	@Test(enabled=false,description="Getting specific Contact")
	public void getSpecificationContact1() {
		given()
		 .when()
		       .get("http://3.13.86.142:3000/contacts/60ebb0ab170734047659ad5b")
		 .then()
		       .log()
		       .body()
		       .statusCode(200);
	}
	@Test(enabled=true,description="Getting specific Contact")
	public void addingContact() {
		  JSONObject details = new JSONObject();
	        JSONObject loc = new JSONObject();
	        JSONObject emp = new JSONObject();

	        loc.put("city", "Mumbai");
	        loc.put("country", "India");
	        emp.put("jobTitle", "QA");
	        emp.put("company", "LTI");
	        details.put("firstName", "pranav");
	        details.put("lastName", "lalwadia");
	        details.put("email", "paranav@lti.com");
	        details.put("location", loc);
	        details.put("employer", emp);

	        ExtractableResponse<Response> ex= given()
	        										.header("Content-Type", "application/json")
	        										.body(details.toJSONString())
	        							        .when()
	                                                 .post("http://3.13.86.142:3000/contacts")
	                                             .then()
	                                             .log()
	                                             .body().
	                                             statusCode(200)
	                                             .extract();
	        id = ex.path("_id");
	        System.out.println(ex.path("id"));
            System.out.println(ex.path("firstName"));
            System.out.println(ex.path("lastName"));
	}
	
    @Test(enabled = true, dependsOnMethods="addingContact" ,description = "Getting specific Contact")
    public void postUpdateContact() {

        JSONObject details = new JSONObject();
        JSONObject loc = new JSONObject();
        JSONObject emp = new JSONObject();

        loc.put("city", "Mumbai");
        loc.put("country", "India");
        emp.put("jobTitle", "QA");
        emp.put("company", "LTI");
        details.put("firstName", "Dev");
        details.put("lastName", "kondalkar");
        details.put("email", "dev@lti.com");
        details.put("location", loc);
        details.put("employer", emp);

        given()
             .header("Content-Type","application/json")
             .body(details.toJSONString())
         .when()
             .put("http://3.13.86.142:3000/contacts/"+id)
         .then()
             .log()
             .body()
             .statusCode(204);
    }
    @Test(enabled = true, dependsOnMethods="postUpdateContact" ,description = "Getting specific Contact")
    public void getSpecficContact3() {
         given()
          .when()
          .get("http://3.13.86.142:3000/contacts/"+id)
          .then()
          .log()
          .body()
          .statusCode(200);
         
    }
    @Test(enabled = true, dependsOnMethods="getSpecficContact3" ,description = "Getting specific Contact")
    public void deletingSpecficContact  () {
         given()
          .when()
          .delete("http://3.13.86.142:3000/contacts/"+id)
          .then()
          .log()
          .body()
          .statusCode(204);
    }
    @Test(enabled = true, dependsOnMethods="deletingSpecficContact" ,description = "Getting specific Contact")
    public void getSpecficContact4() {
         given()
          .when()
          .get("http://3.13.86.142:3000/contacts/"+id)
          .then()
               .log()
               .body()
               .statusCode(404);
    }
}

