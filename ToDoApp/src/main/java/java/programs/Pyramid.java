package java.programs;

import java.util.Scanner;



public class Pyramid {
		public static void main(String []args) {
			System.out.println("Enter row count\n");
			Scanner s= new Scanner(System.in);
			int noofrows=s.nextInt();
			int  rowcount=1;
			for(int i=noofrows;i>=0;i--)
			{
				for(int j=1;j<=i;j++)
					System.out.print(" ");
				
				for (int j = 1; j <= rowcount; j++)
	            {
	                System.out.print(j+" ");
	            }
	 
	            System.out.println();
	 
	            //Incrementing the rowCount
	 
	            rowcount++;
			
			}
		}


}
