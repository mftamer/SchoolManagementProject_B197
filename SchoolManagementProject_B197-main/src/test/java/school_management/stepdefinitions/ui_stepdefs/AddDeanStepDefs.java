package school_management.stepdefinitions.ui_stepdefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.junit.Assert;
import school_management.pages.AddDeanPage;
import school_management.utilities.BrowserUtils;
import school_management.utilities.ConfigReader;
import school_management.utilities.Driver;
import school_management.utilities.WaitUtils;


public class AddDeanStepDefs {

    AddDeanPage addDean = new AddDeanPage();
    Faker faker = new Faker();

    String name;
    static int userId;

    public static String ListSsnNo;

    @Given("admin goes to the url")
    public void admin_goes_to_the_url() {
        Driver.getDriver().get(ConfigReader.getProperty("homepage_url"));
    }
    @Given("clicks on login option")
    public void clicks_on_login_option() {
        addDean = new AddDeanPage();
        if (addDean.loginLink.isDisplayed()) {
            addDean.loginLink.click();
        } else {
            addDean.menuButton.click();
        }
    }

    @Given("enters {string} in username input field")
    public void enters_in_username_input_field(String username) {
        addDean.usernameInput.sendKeys(username);
    }

    @Given("enters {string} in password field")
    public void enters_in_password_field(String password) {
        addDean.passwordInput.sendKeys(password);
    }
    @Given("clicks on the login button")
    public void clicks_on_the_login_button() {
        BrowserUtils.clickWithTimeOut(addDean.loginButton, 2);
    }
    @Given("clicks on the Menu button")
    public void clicks_on_the_menu_button() {
        addDean.menuButton.click();
    }
    @Given("clicks on Dean Management option")
    public void clicks_on_dean_management_option() {
        addDean.deanManagementButton.click();
    }
    @Given("enters {string} in the name field")
    public void enters_in_the_name_field(String str) {
        name = faker.name().firstName();
        if (str.equals("a name")) {
            addDean.name.sendKeys(name);
        } else {
            addDean.name.sendKeys(str);
        }
    }
    @Given("enters {string} in the Surname field")
    public void enters_in_the_surname_field(String str) {
        String surname = faker.name().lastName();
        if (str.equals("a surname")) {
            addDean.surname.sendKeys(surname);
        } else {
            addDean.surname.sendKeys(str);
        }
    }
    @Given("enters {string} in the Birth Place field")
    public void enters_in_the_birth_place_field(String str) {
        String birthPlace = faker.lorem().word();
        if (str.equals("a place name")) {
            addDean.birthPlace.sendKeys(birthPlace);
        } else  {
            addDean.birthPlace.sendKeys(str);
            WaitUtils.waitFor(2);
        }
    }
    @Given("enters {string} in Gender field")
    public void enters_in_gender_field(String str) {
        String gender = "";
        if (str.equalsIgnoreCase("FEMALE")) {
            addDean.genderFemale.click();
            gender = "FEMALE";
        } else if (str.equalsIgnoreCase("MALE")) {
            addDean.genderMale.click();
            gender = "MALE";
        } else {
            System.out.println("You entered incorrect data.");
        }
        WaitUtils.waitFor(2);
    }
    @Given("enters {string} in DateOfBirth field")
    public void enters_in_date_of_birth_field(String date) {
        addDean.birthDay.sendKeys(date);
        WaitUtils.waitFor(2);
    }
    @Given("enters {string} in Phone Number field")
    public void enters_in_phone_number_field(String str) {
        if (str.equals("a phone number")) {
            String ListPhoneNo = faker.regexify("[0-9]{3}-[0-9]{3}-[0-9]{4}");
            addDean.phoneNumber.sendKeys(ListPhoneNo);
        } else {
            addDean.phoneNumber.sendKeys(str);
        }
    }

    @Given("enters ssn in SSN field")
    public void enters_ssn_in_ssn_field() {
        ListSsnNo = "523-65-6879";
        addDean.ssn.sendKeys(ListSsnNo);
        WaitUtils.waitFor(2);
    }

    @Given("enters {string} in Username field")
    public void enters_in_username_field(String str) {
        String username = faker.random().nextInt(1000,9999)+ "Kate";
        if (str.equals("a user name")) {
            addDean.username.sendKeys(username);
        } else {
            addDean.username.sendKeys(str);
        }
    }

    @Given("enters password in Password field")
    public void enters_password_in_password_field() {
        addDean.deanPassword.sendKeys("Kate_456");
    }
    @Given("clicks on Submit button")
    public void clicks_on_submit_button() {
        addDean.deanSubmit.click();
        WaitUtils.waitFor(2);
    }
    @Then("verifies Dean is created")
    public void verifies_dean_is_created() {
        Assert.assertTrue(addDean.deanSavedPopUp.isDisplayed());
    }

}
