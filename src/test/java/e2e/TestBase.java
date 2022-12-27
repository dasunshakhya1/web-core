package e2e;

import core.ElementHandler;
import org.testng.annotations.AfterClass;

public abstract class TestBase {



    @AfterClass
    public void tearDown(){
        ElementHandler.quiteDriver();
    }
}
