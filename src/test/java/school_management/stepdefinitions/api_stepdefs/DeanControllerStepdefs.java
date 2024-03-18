package school_management.stepdefinitions.api_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import school_management.pojos.DeanPojo;
import school_management.pojos.DeanPostPojo;
import school_management.pojos.ObjectPojo;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static school_management.base_url.BaseUrl.setUp;
import static school_management.base_url.BaseUrl.spec;


public class DeanControllerStepdefs {

    DeanPostPojo payload;

    Response response;

    public static int userId;

    public static DeanPojo expectedData;
    public static ObjectPojo objectPojo;

    @Given("User is authorized as {string}")
    public void userIsAuthorizedAs(String role) {
        role = role.trim().toLowerCase();

        switch (role){
            case "admin":
                setUp("AdminBatch197", "Batch197+");
                break;
            case "dean":
                setUp("","");
                break;
            case "teacher":
                setUp("","");
                break;
            default:
                System.out.println("Please provide valid credentials for your role");

        }
    }

    @And("User sets the Url for Dean Save")
    public void userSetsTheUrlForDeanSave() {
        spec.pathParams("first", "dean", "second", "save");
    }

    @And("sets the payload for Dean Save")
    public void setsThePayloadForDeanSave() {
        payload = new DeanPostPojo("1990-09-24", "UK", "FEMALE", "Kate", "Kate_456", "226-662-2261", "882-22-2881", "Middelton", "Kate");
    }

    @When("sends POST request and get response")
    public void sendsPostRequestAndGetResponse() {
        response = given(spec).body(payload).post("{first}/{second}");
        response.prettyPrint();
    }

    @Then("verifies status code is {int}")
    public void verifiesStatusCodeIs(int code) {
        assertEquals(code, response.statusCode());
    }

    @And("verifies response body")
    public void verifiesResponseBody() {
        DeanPojo actualData = response.as(DeanPojo.class);

        assertEquals(payload.getBirthDay(), actualData.getObject().getBirthDay());
        assertEquals(payload.getBirthPlace(), actualData.getObject().getBirthPlace());
        assertEquals(payload.getGender(), actualData.getObject().getGender());
        assertEquals(payload.getName(), actualData.getObject().getName());
        assertEquals(payload.getPhoneNumber(), actualData.getObject().getPhoneNumber());
        assertEquals(payload.getSsn(), actualData.getObject().getSsn());
        assertEquals(payload.getSurname(), actualData.getObject().getSurname());
        assertEquals(payload.getUsername(), actualData.getObject().getUsername());



    }

    @And("User gets id of the Dean with username {string}")
    public void userGetsIdOfTheDeanWithUsername(String username) {
        spec.pathParams("first", "dean", "second", "getAll");

        response = given(spec).get("{first}/{second}");

        List<Integer> idList =  response.jsonPath().getList("findAll{it.username == '"+username+"' }.userId");
        userId = idList.get(0);
        System.out.println("userId = " + userId);


    }

    @And("User sets the Url for Get Dean By id")
    public void userSetsTheUrlForGetDeanById() {
        spec.pathParams("first","dean", "second", "getManagerById", "third", userId);

    }

    @And("sets the expected data for Get Dean By id")
    public void setsTheExpectedDataForGetDeanById() {
        objectPojo = new ObjectPojo(userId, "Kate", "Kate", "Middelton", "1990-09-24", "882-22-2881", "UK", "226-662-2261", "FEMALE");
        expectedData = new DeanPojo(objectPojo, "Dean successfully found", "OK");
    }

    @When("sends GET request and get response")
    public void sendsGetRequestAndGetResponse() {
        response = given(spec).get("{first}/{second}/{third}");
        response.prettyPrint();
    }

    @And("verifies response body for Get Dean By id")
    public void verifiesResponseBodyForGetDeanById() {
        DeanPojo actualData = response.as(DeanPojo.class);

        assertEquals(expectedData.getObject().getBirthDay(), actualData.getObject().getBirthDay());
        assertEquals(expectedData.getObject().getUsername(), actualData.getObject().getUsername());
        assertEquals(expectedData.getObject().getName(), actualData.getObject().getName());
        assertEquals(expectedData.getObject().getSurname(), actualData.getObject().getSurname());
        assertEquals(expectedData.getObject().getBirthPlace(), actualData.getObject().getBirthPlace());
        assertEquals(expectedData.getObject().getGender(), actualData.getObject().getGender());
        assertEquals(expectedData.getObject().getPhoneNumber(), actualData.getObject().getPhoneNumber());
        assertEquals(expectedData.getObject().getSsn(), actualData.getObject().getSsn());
        assertEquals(expectedData.getObject().getUserId(), actualData.getObject().getUserId());

        assertEquals(expectedData.getHttpStatus(), actualData.getHttpStatus());
        assertEquals(expectedData.getMessage(), actualData.getMessage());


    }

    @And("User deletes the created dean")
    public void userDeletesTheCreatedDean() {

        spec.pathParams("first","dean", "second", "delete", "third", userId);
        response = given(spec).delete("{first}/{second}/{third}");

        response.then().statusCode(200);


    }
}

/*
{ "1990-09-24", "UK", "FEMALE", "Kate", "Kate_456", "226-662-2261", "882-22-2881", "Middelton", "Kate" }
 */