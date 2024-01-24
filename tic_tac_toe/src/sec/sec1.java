package sec;

import java.util.Scanner;

public class sec1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("");
//		in.nextInt();
//		Scanner in = new Scanner(System.in);
//		tic-tac-toe:
		int[][] board=new int[3][3];
		Scanner in = new Scanner(System.in);
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
				board[i][j]=in.nextInt();
		}
		int oops = 0;
		int xist = 0;
//		非常重要也是非常好用的一步：创建假的布尔数，在有棋子排成三颗时，布尔数变真的，防止再去检测其他行列或者对角线，重复输出winner；其次还能在始终没有赢家也就是布尔数保持假的的时候，能够输出平局的答案。
		boolean haswinner = false;
//		检查行
		for(int i=0;i<3;i++)
		{
			oops = 0;
			xist = 0;
			for(int j=0;j<3;j++)
			{
				if(board[i][j]==0)
					oops ++;
				else
					xist ++;		
			}
			if(oops == 3)
			{
				System.out.println("oops win");
				haswinner = true;
				break;
			}
				
			else if(xist==3)
			{
				System.out.println("xist win");
				haswinner = true;
				break;
			}
			
		}
//		检查列
		if(!haswinner)
		{
			for(int j=0;j<3;j++)
			{
				oops = 0;
				xist = 0;
				for(int i=0;i<3;i++)
				{
					if(board[i][j]==0)
						oops ++;
					else
						xist ++;		
				}
				if(oops == 3)
				{
					System.out.println("oops win");
					haswinner = true;
					break;
				}
					
				else if(xist==3)
				{
					System.out.println("xist win");
					haswinner = true;
					break;
				}	
			}
		}
		
//		检查对角线(0,0)(1,1)(2,2)
		if(!haswinner)
		{
			oops = 0;
			xist = 0;
			for(int i=0;i<3;i++)
			{	
				if(board[i][i]==0)
					oops ++;
				else
					xist ++;		
				if(oops == 3)
				{
					System.out.println("oops win");
					haswinner = true;
				}	
				else if(xist==3)
				{
					System.out.println("xist win");
					haswinner = true;
				}	
			}
		}
//		检查反对角线（0，2）（1，1）（2，0）
		if (!haswinner)
		{
			oops = 0;
			xist = 0;
			for(int i = 0; i<3;i++)
			{
				if(board[i][2-i]==0)
					oops ++;
				else
					xist ++;		
				if(oops == 3)
				{
					System.out.println("oops win");
					haswinner = true;
				}
				else if(xist==3)
				{
					System.out.println("xist win");
					haswinner = true;
				}
			}		
		}
		if (!haswinner)
			System.out.println("tie");
		
		
			
		
		
		
				
				
	}

}
