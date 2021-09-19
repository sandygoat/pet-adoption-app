package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OwnerTest {

    @Test
    public void testOwner() {
        Owner hooman = new Owner("Karen", "1-778-456-7890");
        assertEquals("Karen", hooman.getOwnerName());
        assertEquals("1-778-456-7890", hooman.getPhoneNumber());
    }

    @Test
    public void testEqualsAndHashcode() {
        Owner hooman = new Owner("Karen", "1-778-456-7890");
        Owner sameOne = new Owner("Karen", "1-778-456-7890");
        Owner someone = new Owner("Donald", "1-234-456-7890");
        Date someday = new Date(12,12, 2020);

        assertTrue(hooman.equals(hooman));
        assertTrue(sameOne.equals(hooman));
        assertFalse(hooman.equals(someone));
        assertFalse(hooman.equals(someday));
        assertFalse(hooman.equals(null));
        assertEquals(hooman.hashCode(), sameOne.hashCode());
        assertFalse(hooman.hashCode() == someone.hashCode());
    }
}
