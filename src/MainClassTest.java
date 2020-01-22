import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{
    @Test
    public void testGetLocalNumber()
    {
        if (this.getLocalNumber() == 14)
        {
            System.out.println("Passed, Local Number = 14");
        }
        else
        {
            Assert.fail("Fail, Local Number != 14");
        }
    }
}
