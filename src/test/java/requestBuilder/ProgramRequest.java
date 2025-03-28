package requestBuilder;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.util.Map;
import common.ExcelReader;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import payload.Program_POJO;


import static io.restassured.RestAssured.given;

@Getter
@Setter
public class ProgramRequest {
	
	private Program_POJO program_POJO;  
    RequestSpecification requestspecification;
    Response response;
    
    
    public ProgramRequest() {
        this.program_POJO = new Program_POJO();
    }

    public void setuserRequestbody(String sheetName, String testCaseID) throws IOException {
        Map<String, String> testData = ExcelReader.getTestData(sheetName, testCaseID);
        
        	program_POJO.setProgramDescription(testData.get("ProgramDesc"));
            program_POJO.setProgramName(testData.get("ProgramName"));
            program_POJO.setProgramStatus(testData.get("ProgramStatus"));
    
    }

    public Program_POJO getuserRequestBody() {  
        return program_POJO;
    }

    // Set endpoint
    public void setEndpoint(String sheetName, String testCaseID) throws IOException {
        Map<String, String> testData = ExcelReader.getTestData(sheetName, testCaseID);
     

        if ("invalid Endpoint".equalsIgnoreCase(testData.get("usecase"))) {
        	program_POJO.setEndpoint(testData.get("Endpoint"));  // Set endpoint in user_POJO
        } else {
        	String ProgramPost = enumPackage.Endpoint.Program_POST.getPath();
        	program_POJO.setEndpoint(ProgramPost); 
        }
    }

    // Get the endpoint from login_POJO
    public String getEndpoint() {
        return program_POJO.getEndpoint();  // Return endpoint from login_POJO
    }
    
 public void programPost(String sheetName, String testCaseID, RequestSpecification requestSpecification) throws IOException {
        
	 setuserRequestbody(sheetName, testCaseID);
        setEndpoint(sheetName, testCaseID);
       

        // Making the POST request
        response = given()
                .spec(requestSpecification)
                .body(getuserRequestBody())  
                .when()
                .post(getEndpoint());
            
    
        System.out.println("****** Request Body: " + program_POJO);
        System.out.println("****** Response: " + response.prettyPrint());
        System.out.println("****** Status Code: " + response.getStatusCode());
    }
    public Response getResponse() {
        return response;
    }


}
