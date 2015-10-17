/**package Logic.Test;


import Logic.Cell;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class CellTest{

    @Before


    @Test
    public void testAddRandom() throws Exception {
        Cell cell = new Cell(0,0);
        cell.excludeValue(2);
        cell.excludeValue(4);
        cell.excludeValue(6);
        cell.excludeValue(8);
        cell.addRandom();
        assertTrue(cell.getValue() % 2 != 0);
    }

    @Test
    public void testExcludeValue() throws Exception {
        Cell cell = new Cell(0,0);
        cell.excludeValue(2);
        assertFalse(cell.availableValues().contains(2));
    }

    @Test
    public void testAvailableValues() throws Exception {
        Cell cell = new Cell(0,0);
        ArrayList<Integer> test = new ArrayList<Integer>();
        for(int i = 1; i <= 9; i++){
            assertTrue(cell.availableValues().contains(i));
        }
        cell.excludeValue(1);
        for(int i = 2; i <= 9; i++){
            assertTrue(cell.availableValues().contains(i));
        }
    }
    @Test
    public void testReset() throws Exception {
        Cell cell = new Cell(0,0);
        cell.excludeValue(2);
        cell.excludeValue(4);
        cell.excludeValue(6);
        cell.excludeValue(8);
        cell.addRandom();
        cell.reset();
        assertTrue(cell.getValue() == 0);
        assertEquals(9, cell.availableValues().size());
    }

    @Test
    public void testClearMemory() throws Exception {
        Cell cell = new Cell(0,0);
        cell.excludeValue(2);
        cell.excludeValue(4);
        cell.excludeValue(6);
        cell.excludeValue(8);
        cell.addRandom();
        cell.clearMemory();
        assertEquals(9, cell.availableValues().size());
    }

}              */