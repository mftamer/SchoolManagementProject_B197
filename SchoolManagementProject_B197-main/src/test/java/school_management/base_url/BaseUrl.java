package school_management.base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static school_management.utilities.Authentication.generateToken;

public class BaseUrl {

    public static RequestSpecification spec;

    public static void setUp(String userName, String password){
        String baseUrl = "https://managementonschools.com/app";

        spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", generateToken(userName, password))
                .build();
    }

}
