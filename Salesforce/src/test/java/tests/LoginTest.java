package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void correctDataFilling() {
        loginSteps.login(LOGIN, PASSWORD);
    }
}
