package Junit.src.test.java;

import Chara.Chara;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCharacter {
    @Test
    public void testSimple()
    {
        int i = 3;
        i = i + 2;
        assertEquals(5, i);
    }

}
