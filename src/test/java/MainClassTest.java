import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    MainClass Main = new MainClass();

    @Test
    public void testGetLocalNumber() {

        Assert.assertEquals("Число не соотвествует 14", 14, Main.getLocalNumber());
    }
}