import java.io.*;
import java.util.*;
public class bteams 
{
	public static List<List<Integer>> subsets = new ArrayList<List<Integer>>();
	public static int difference=1000000; //maximum difference (1,000,000 - 0)
	
	public static void main (String[] args) throws IOException
	{
		Scanner textFile = new Scanner(new File("bteams.in"));
		int[] inputNums=new int[12];
		//read in skill level values
		for (int i=0; i<12; i++)
		{
			inputNums[i]=textFile.nextInt();
		}
	    
		//create ArrayList to represent 4 teams of three players
		for (int i = 0; i <4; i++)
	    {
	        subsets.add(new ArrayList<Integer>(3));
	    }
		
	    solve (inputNums, 0);
	    PrintWriter pw = new PrintWriter(new File("bteams.out"));
        pw.print(difference);
        pw.close();
	}
	
	/*
	 * determines every possible team configuration
	 * @param inputNums is an array of twelve skill level values
	 * @param i is the index of the skill level value being assigned to a team
	 */
	public static void  solve(int[] inputNums, int i) 
	{
	    if (i == inputNums.length) //all the teams are full
	    {
	    	difference ();
	    } 
	    else 
	    {
	        for (int j = 0; j < subsets.size(); j++) //attempt to insert integer if not full
	        {
	            if (subsets.get(j).size() < 3) //not full
	            {
	                subsets.get(j).add(inputNums[i]);//add the value to the team
	                solve(inputNums, i+1); // do recursion (eventually leading to a full team)
	                //remove the value so that a different value can be added to this same team to generate each possible combo
	                subsets.get(j).remove((Integer)inputNums[i]); 
	                if (subsets.get(j).size() == 0) //empty
	                {
	                     break;
	                }                    
	            }
	        }
	    }
	}
	
	/*
	 * computes the difference between the max and min totals
	 */
	public static void difference ()
	{
		//computer the totals
		int total1=(subsets.get(0).get(0).intValue())+(subsets.get(0).get(1).intValue())+(subsets.get(0).get(2).intValue());
		int total2=(subsets.get(1).get(0).intValue())+(subsets.get(1).get(1).intValue())+(subsets.get(1).get(2).intValue());
		int total4=(subsets.get(3).get(0).intValue())+(subsets.get(3).get(1).intValue())+(subsets.get(3).get(2).intValue());
		int total3=(subsets.get(2).get(0).intValue())+(subsets.get(2).get(1).intValue())+(subsets.get(2).get(2).intValue());
		
		//determine the max and min values
		int max=total1;
		int min=total1;
		if (total2>max)
		{
			max=total2;
		}
		if (total2<min)
		{
			min=total2;
		}
		if (total3>max)
		{
			max=total3;
		}
		if (total3<min)
		{
			min=total3;
		}
		if (total4>max)
		{
			max=total4;
		}
		if (total4<min)
		{
			min=total4;
		}
		int temp=max-min;
		if (temp<difference) //this configuration of teams creates a difference less than every previous configuration of teams
		{
			difference=temp;
		}
	}
}
