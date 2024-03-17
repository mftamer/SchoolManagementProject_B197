package school_management.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static String generateToken(String userName, String password){

        String url = "https://managementonschools.com/app/auth/login";

        String payload = "{\n" +
                "  \"password\": \""+password+"\",\n" +
                "  \"username\": \""+userName+"\"\n" +
                "}";

        Response response = given().body(payload).contentType(ContentType.JSON).when().post(url);

        return response.jsonPath().getString("token");

    }
}


/*
{
  "password": "Batch197+",
  "username": "AdminBatch197"
}
 */