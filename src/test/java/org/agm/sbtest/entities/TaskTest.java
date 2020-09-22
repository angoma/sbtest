package org.agm.sbtest.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TaskTest {

    private Task obj1;
    private Task obj2;
    private Task obj3;

    @Before
    public void setup() {
        obj1 = new Task();
        obj1.setId(1l);

        obj2 = new Task();
        obj2.setId(2l);

        obj3 = new Task();
        obj3.setId(1l);
    }

    @Test
    public void testEquals() {
        assertTrue(obj1.equals(obj1));
        assertFalse(obj1.equals(obj2));
        assertFalse(obj2.equals(obj1));
        assertTrue(obj1.equals(obj3));
        assertTrue(obj3.equals(obj1));
        assertFalse(obj1.equals(null));
        assertFalse(obj1.equals(""));
    }

    @Test
    public void testHashCode() {
        assertTrue(obj1.hashCode() == obj1.hashCode());
        assertFalse(obj1.hashCode() == obj2.hashCode());
        assertFalse(obj2.hashCode() == obj1.hashCode());
        assertTrue(obj1.hashCode() == obj3.hashCode());
        assertTrue(obj3.hashCode() == obj1.hashCode());
        assertNotNull(obj1.hashCode());
        assertFalse(obj1.hashCode() == "".hashCode());
    }
}
