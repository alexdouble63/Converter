package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValueTest {

    @Test
    public void testGetName() {
        Value value = new Value("byte");
        assertEquals("byte",value.getName());
    }

    @Test
    public void testSetName() {
        Value value = new Value("byte");
        value.setName("bit");
        assertEquals("bit",value.getName());
    }


    @Test
    public void testIsVisited() {
        Value value = new Value("byte");
        assertEquals(false,value.isVisited());
    }


    @Test
    public void testSetVisited() {
        Value value = new Value("byte");
        value.setVisited(true);
        assertEquals(true,value.isVisited());
    }

}