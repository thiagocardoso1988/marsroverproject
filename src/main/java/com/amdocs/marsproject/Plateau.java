package com.amdocs.marsproject;

public class Plateau
{
    private int width;
    private int height;

    /**
     * Instantiates a new plateau board where the {@link Rover}'s can walk on. It has a zero-based notation, so its
     * first cell, considering a 1x1 size plateau, will be 0x0.
     * @param pWidth how much lines this plateau shall have
     * @param pHeight how much columns this plateau shall have
     */
	public Plateau(int pWidth, int pHeight)
    {
        if (pWidth < 1 || pHeight < 1)
        {
            String errorMsg = "Plateau size must be greater than zero (Passed size: width: %i, height: %s)";
            throw new RuntimeException(String.format(errorMsg, pWidth, pHeight));
        }
        width = pWidth;
        height = pHeight;
    }

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return width;
	}

    /**
     * Validate the {@link Rover}'s position, based on its position against the plateau size (both width and height)
     * @param pPosX line number in where the Rover is placed
     * @param pPosY column number in where the Rover is placed
     */
    public boolean validateLocation(int pPosX, int pPosY)
    {
//        if (pPosX > width || pPosY > height || pPosX < 0 || pPosY < 0)
//        {
//            String errorMsg = "Rover is out of the plateau's boundaries (Plateau width: %d, height: %d - " +
//                    "Rover Position X: %d, Y: %d)";
//            throw new RuntimeException(String.format(errorMsg, width, height, pPosX, pPosY));
//        }
    	return !(pPosX > width || pPosY > height || pPosX < 0 || pPosY < 0);
    }
}
