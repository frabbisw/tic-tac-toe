package tic2;

import java.awt.Color;

public final class Constants {
	public final static int N = 3;
	public final static int W = 3;
	public final static int Inf = 99999;
	public final static Color primary = new Color(1,2,2);
	public final static Color secondary = new Color(1,100,2);
	public final static Color font = new Color(200,200,2);
	
	public static Integer[][] copy(Integer [][] arr)
	{
		Integer [][] cpy = new Integer [arr.length][arr[0].length];
		for(int i=0; i<cpy.length; i++)	for(int j=0; j<cpy[i].length; j++)	cpy[i][j]=arr[i][j];
		return cpy;
	}
}
