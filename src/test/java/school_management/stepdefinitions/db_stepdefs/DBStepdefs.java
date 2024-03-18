package school_management.stepdefinitions.db_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import school_management.pojos.DeanPojo;
import school_management.pojos.ObjectPojo;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static school_management.stepdefinitions.api_stepdefs.DeanControllerStepdefs.userId;
import static school_management.stepdefinitions.api_stepdefs.DeanControllerStepdefs.objectPojo;


public class DBStepdefs {
    Connection connection;
    Statement statement;

    ResultSet resultSet;

    ObjectPojo objectPojo;


    @Given("User sets connection")
    public void userSetsConnection() throws SQLException {

        connection = DriverManager.getConnection("jdbc:postgresql://managementonschools.com:5432/school_management","select_user","43w5ijfso");

    }

    @And("User creates statement")
    public void userCreatesStatement() throws SQLException {
        statement = connection.createStatement();
    }

    @When("User executes query for created dean")
    public void userExecutesQueryForCreatedDean() throws SQLException {
        String query = "SELECT * FROM dean where username = 'Kate'";
        resultSet = statement.executeQuery(query);
    }

    @Then("validates result set")
    public void validatesResultSet() throws SQLException {
        resultSet.next();
//   1. Create the Pojo object and set the values for assertion
        objectPojo = new ObjectPojo(userId, "Kate", "Kate", "Middelton", "1990-09-24", "882-22-2881", "UK", "226-662-2261", "FEMALE");
//  OR 2.  import objectPojo already set and initiated in api_stepdefs


        assertEquals(objectPojo.getUsername(), resultSet.getString("username"));
        assertEquals(objectPojo.getName(), resultSet.getString("name"));
        assertEquals(objectPojo.getSurname(), resultSet.getString("surname"));
        assertEquals(objectPojo.getBirthPlace(), resultSet.getString("birth_place"));
        assertEquals(objectPojo.getBirthDay(), resultSet.getString("birth_day"));
        assertEquals(objectPojo.getPhoneNumber(), resultSet.getString("phone_number"));
        assertEquals(objectPojo.getSsn(), resultSet.getString("ssn"));
        assertEquals(1, resultSet.getInt("gender"));
    }

    @And("User terminates connection")
    public void userTerminatesConnection() throws SQLException {
        statement.close();
        connection.close();
    }
}