package com.amdocs.marsproject;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Test class for {@link Plateau} entity
 */
public class PlateauTest
{
    /**
     * Test if a {@link RuntimeException} is thrown when creating a zero by zero plateau size
     */
    @Test(expected = RuntimeException.class)
    public void testIfPlateauSizeCanBeZero()
    {
        new Plateau(0, 0);
    }

    /**
     * Test if a {@link RuntimeException} is thrown when creating a negative plateau size
     */
    @Test(expected = RuntimeException.class)
    public void testIfPlateauSizeCanBeNegative()
    {
        new Plateau(-1, -1);
    }

    /**
     * Test if return false when validating a cell outside the plateau's boundaries
     */
    @Test
    public void testIfValidateRoverMoveOutsideBoundaries()
    {
        Plateau myPlateau = new Plateau(1, 1);
        assertFalse(myPlateau.validateLocation(2,2));
    }

    /**
     * Test if a successful validation is made when moving a {@link Rover} inside the plateau's boundaries
     *
     */
    @Test
    public void testIfValidateRoverMoveInsideBoundaries()
    {
        Plateau myPlateau = new Plateau(1, 1);
        myPlateau.validateLocation(0,0);
    }
}