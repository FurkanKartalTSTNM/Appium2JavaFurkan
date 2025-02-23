package tests.driver;

import io.appium.java_client.remote.AppiumCommandExecutor;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.http.HttpRequest;

import java.net.URL;
import java.util.Collections;
import java.util.Date;

import static tests.driver.TestiniumDriver.TESTINIUM_ENVIRONMENT;
import static tests.driver.TestiniumDriver.buildCommandResultLogs;


public class TestiniumCommandExecutor extends AppiumCommandExecutor {

    public TestiniumCommandExecutor(URL remoteServer) {
        super(Collections.emptyMap(), remoteServer);
        start();
    }

    @Override
    public Response execute(Command command) {
        Date startDate = new Date();
        Response response = super.execute(command);
        HttpRequest encodedCommand = super.getCommandCodec().encode(command);
        buildCommandResultLogs(command, startDate, response, encodedCommand);
        return response;
    }

    public static void start(){
        TESTINIUM_ENVIRONMENT.init();
    }

}
