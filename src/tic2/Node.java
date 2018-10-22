package tic2;

import java.util.ArrayList;

public class Node {
	double value;
	boolean leaf;
	ArrayList<Node>nodes;
	ArrayList<Key>keys;
	Integer [][] values;
	int N=Constants.N;
	int W=Constants.W;
	double Inf = Constants.Inf;
	int id;
	Key next;
	
	public Node(Integer [][] values, int id)
	{
		this.id=id;
		this.values=values;
		nodes=new ArrayList<>();
		keys=new ArrayList<>();
		calculateValue();
	}
	public void calculateValue() 
	{
		int t = values[0][0]+values[1][1]+values[2][2];
		if(t==3)	value=Inf;
		else if(t==-3)	value=-Inf;
		t = values[2][0]+values[1][1]+values[0][2];
		if(t==3)	value=Inf;
		else if(t==-3)	value=-Inf;
		
		int full=N*N;
		for(int i=0; i<N; i++)
		{
			int ta=0,tb=0;
			for(int j=0; j<N; j++)
			{
				if(values[i][j]==0)	
				{
					full--;
					ta=0;
				}
				if(values[j][i]==0)	tb=0;
				ta+=values[i][j];
				tb+=values[j][i];
				if(ta==W|tb==W)	value=Inf;
				if(ta==-W|tb==-W)	value=-Inf;
			}
		}
		if(full==N*N | value==Inf | value==-Inf)	leaf=true;		
	}
	
	public void extract() 
	{
		for(int i=0; i<N; i++)
		{
			for(int j=0; j<N; j++)
			{
				if(values[i][j]==0)
				{
					Integer[][] tmp = Constants.copy(values);
					tmp[i][j]=id;
					nodes.add(new Node(tmp, id*-1));
					keys.add(new Key(i, j));
				}
			}
		}
	}
	public boolean isLeaf()
	{
		return leaf;
	}
	public double getValue()
	{
		return value;
	}
	public Node getChild(int index)
	{
		return nodes.get(index);
	}
	public int size()
	{
		return nodes.size();
	}
}