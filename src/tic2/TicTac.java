package tic2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TicTac extends JFrame
{
	int N=Constants.N;
	int height=N*100;
	int width=N*100;
	int vx=100;
	int vy=100;
	Integer values [][] = new Integer [N][N];
	JButton [][] buttons = new JButton [N][N];
	TicTac User;
	
	public TicTac() {
		// TODO Auto-generated constructor stub
		User = this;
		this.setBounds(300, 100, width+vx*2, height+vy*2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("TicTacToe");
		this.setVisible(true);
		this.setLayout(null);
		final TicTac User = this;
		
		JButton hstart = new JButton("Start(User)");
		hstart.setBounds(100, 40, 150, 25);
		this.add(hstart);
		hstart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				for(int i=0; i<N; i++)
					for(int j=0; j<N; j++)
					{
						values[i][j]=0;
						buttons[i][j].setText("");
					}
			}
		});
		
		JButton pstart = new JButton("Start(Computer)");
		pstart.setBounds(250, 40, 150, 25);
		this.add(pstart);
		pstart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				for(int i=0; i<N; i++)
					for(int j=0; j<N; j++)
					{
						values[i][j]=0;
						buttons[i][j].setText("");
					}
				AI.execute(new Node(values, 1), User);
			}
		});
		
		JPanel parent = new JPanel();
		parent.setLayout(null);
		parent.setBackground(Constants.secondary);
		parent.setBounds(0, 0, width+vx*2, height+vy*2);
		this.add(parent);
		
		
		class ButtonActionListener implements ActionListener
		{
			TicTac User;
			int indI, indJ;
			public ButtonActionListener(int indI, int indJ, TicTac User)
			{
				this.User=User;
				this.indI=indI;
				this.indJ=indJ;
			}
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(values[indI][indJ]==0)	
				{
					buttons[indI][indJ].setText("x");
					User.execute(indI,indJ);
				}
			}
		}
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
			{
				values[i][j]=0;
				buttons[i][j]=new JButton();
				buttons[i][j].setFont(new Font("Verdana", Font.BOLD, 50));
				buttons[i][j].setBackground(Constants.primary);
				buttons[i][j].setForeground(Constants.font);
				parent.add(buttons[i][j]);
				buttons[i][j].setBounds(vx+j*vx, vy+i*vy, vx, vy);
				buttons[i][j].addActionListener(new ButtonActionListener(i,j,User));
			}
	}
	public void execute(int indI, int indJ) 
	{
		values[indI][indJ]=-1;
		Integer[][] tmp = Constants.copy(values);
		Node node = new Node(tmp, 1);
		AI.execute(node, this);
	}
	public void callBack(int i, int j) 
	{
		values[i][j]=1;
		buttons[i][j].setText("o");
	}
}