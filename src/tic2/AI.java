package tic2;

public class AI 
{
	public static double maximizing(Node parent, double alpha, double beta)
	{
		if(parent.isLeaf())	return parent.getValue();
		parent.extract();
		
		double max = -Constants.Inf;
		for(int i=0; i<parent.size(); i++)
		{
			if(alpha>=beta)	break;
			double v = minimizing(parent.getChild(i),alpha,beta);
			if(v>max)	
			{
				max=v;
				parent.next=parent.keys.get(i);
				alpha=Math.max(max, alpha);
			}
		}
		return max;
	}
	public static double minimizing(Node parent, double alpha, double beta)
	{
		if(parent.isLeaf())	return parent.getValue();
		parent.extract();
		
		double min = Constants.Inf;
		for(int i=0; i<parent.size(); i++)
		{
			if(alpha>=beta)	break;
			double v = maximizing(parent.getChild(i),alpha,beta);
			if(v<min)	
			{
				min=v;
				parent.next=parent.keys.get(i);
				beta=Math.min(beta, min);
			}
		}
		return min;
	}

	public static void execute(Node node, TicTac user)
	{
		maximizing(node,-Constants.Inf,Constants.Inf);
		Key key = node.next;
		
		if(key!=null)	user.callBack(key.x, key.y);
	}
}