package data;

import org.junit.Test;

import static org.junit.Assert.*;

public class DistanceCalculatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testErrorParameter(){
        new DistanceCalculator(null,null,"s","f");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testErrorParameter2(){
        new DistanceCalculator("titi","tutu","s","f");

    }

    @Test
    public void testDistanceParisBerlin(){
        int distance = new DistanceCalculator("49.011415","2.547798","52.388059","13.516876").distance();
        System.out.println( distance);
        assertEquals(distance,876.79,20);
    }


}