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
import static school_management.base_url.BaseUrl.setUp;
import static school_management.base_url.BaseUrl.spec;


public class DeanControllerStepdefs {

    DeanPostPojo payload;

    Response response;

    int userId;

    DeanPojo expectedData;

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

    @When("sends post request and get response")
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
        ObjectPojo innerJson = new ObjectPojo(userId, "Kate", "Kate", "Middelton", "1990-09-24", "882-22-2881", "UK", "226-662-2261", "FEMALE");
        expectedData = new DeanPojo(innerJson, "Dean successfully found", "OK");
    }

    @When("sends get request and get response")
    public void sendsGetRequestAndGetResponse() {
        response = given(spec).get("{first}/{second}/{third}");
        response.prettyPrint();
    }

    @And("verifies response body for Get Dean By id")
    public void verifiesResponseBodyForGetDeanById() {
        DeanPojo actualData = response.as(DeanPojo.class);

        assertEquals(expectedData.getObject().getBirthDay(), actualData.getObject().getBirthDay());
        assertEquals(expectedData.getObject().getUsername(), actualData.getObject().getUsername());


    }
}

/*
{ "1990-09-24", "UK", "FEMALE", "Kate", "Kate_456", "226-662-2261", "882-22-2881", "Middelton", "Kate" }
 */
