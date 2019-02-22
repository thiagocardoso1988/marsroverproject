package com.amdocs.marsproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main
{

    public static void main(String[] args)
    {
        try (BufferedReader stdin = new BufferedReader(new FileReader(new File(args[0]))))
        {
            processArguments(stdin);
            System.out.println("Mars Rover Simulator completed.");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
        	throw new IllegalArgumentException("File argument is mandatory");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void processArguments(BufferedReader pStdin) throws IOException
    {
        String line;
        Plateau myPlateau = null;
        Rover myRover = null;

        while ((line = pStdin.readLine()) != null && line.length()!= 0)
        {
            String[] input = line.split(" ");
            if (input.length == 2 && myPlateau == null)
            {
                myPlateau = new Plateau(Integer.valueOf(input[0]), Integer.valueOf(input[1]));
            }
            else if(input.length == 3 && myRover == null)
            {
                myRover = new Rover(Integer.valueOf(input[0]), Integer.valueOf(input[1]), Direction.valueOf(input[2]),
                        myPlateau);
            }
            else if(input.length == 1 && myRover != null)
            {
                myRover.sendCommands(input[0]);
                myRover = null;
            }
            else
            {
            	throw new RuntimeException(String.format("Unknown input was given (%s)", (Object[]) input));
            }
        }
    }
}
