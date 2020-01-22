import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{
    @Test
    public void testGetLocalNumber()
    {
        if (getLocalNumber() == 14)
        {
            System.out.println("Passed, Local Number = 14");
        }
        else
        {
            Assert.fail("Fail, Local Number != 14");
        }
    }

    @Test
    public void testGetClassNumber()
    {
        if (getClassNumber() > 45)
        {
            System.out.println("Passed, Class Number > 45");
        }
        else
        {
            Assert.fail("Fail, Class Number <= 45");
        }
    }
}
