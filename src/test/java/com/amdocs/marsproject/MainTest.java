package com.amdocs.marsproject;

import org.junit.Test;

public class MainTest
{
    @Test(expected = IllegalArgumentException.class)
    public void testMainWithoutArguments()
    {
    	String[] args = {};
        Main.main(args);
    }

    @Test
    public void testMain()
    {
        String[] args = {"src/test/resources/input_test.txt"};
        Main.main(args);
    }
}
