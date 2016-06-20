
/*
ID: arviman1
LANG: JAVA
TASK: preface
 */

import java.io.*;
import java.util.*;
public class preface {	
	static String taskname = "preface";
	static int n;
	static char[] letters = new char[] { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
	static int[] values = new int[] { 1, 5, 10, 50, 100, 500, 1000 };
	
	//i before V or X..
	static String[] mem;
	private static String getRoman(int num)
	{
		int original = num;
		if(mem[num] != null)
			return mem[num];
		String str = "";
		
		while(num >= 1000)
		{
			str += mem[1000];
			num -= 1000;
		}
		if(num >= 900)
		{
			str += mem[900];
			num -= 900;
			str += getRoman(num);
			num = 0;
		}
		if(num >= 500)
		{
			str += mem[500];
			num -= 500;
		}
		if(num >= 400)
		{
			str += mem[400];
			num -= 400;
			str += getRoman(num);
			num = 0;
		}
		while(num >= 100)
		{
			str += "C";
			num -= 100;
		}
		if(num >= 90)
		{
			str += mem[90];
			num -= 90;
			str += getRoman(num);
			num = 0;
		}
		if(num >= 50)
		{
			str += mem[50];
			num -= 50;
			str += getRoman(num);
			num = 0;
		}	
		if(num >= 40)
		{
			str += mem[40];
			num -= 40;
			str += getRoman(num);
			num = 0;
		}
		while(num >= 10)
		{
			str += "X";
			num -= 10;
		}
		if(num == 9)
		{
			str += mem[9];
			num = 0;
		}
		if(num >= 5)
		{
			str += "V";
			num -= 5;
		}
		str += mem[num];
		mem[original] = str;
		return str;
	}
	private static void preCompute()
	{
		for(int i = 0; i < 4; ++i)
		{
			if(i < 4)
			{
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < i; ++j)
				{
					sb.append("I");
				}
				mem[i] = sb.toString();
			}
		}
		
		for(int i = 0; i < letters.length; i+=2) //negative qualifiers like (i)I (j)X		
			for(int j = i + 1; j <= (i+2) && j < letters.length; ++j)			
				mem[values[j] - values[i]] = letters[i] + "" + letters[j];			
		
		
		for(int i = 0; i < letters.length; i++)
			mem[values[i]] = "" + letters[i];
		
		/*for(int i = 0 ; i < 3000; ++i)
			if(mem[i] != null)
				System.out.println(i + " ---:--- " + mem[i]);*/
		
		
	}
	private static void print(Map<Character, Integer> map,PrintWriter pout)
	{
		 
		for(char letter : letters)
		{
			if(map.containsKey(letter))
			{
				pout.println(letter + " " + map.get(letter));
				System.out.println(letter + " " + map.get(letter));
			}
		}
	}
	private static void go()  throws IOException
	{	
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		mem = new String[3501];
		mem[0] = "";
		preCompute();
		for(int i = 1; i <= n; ++i)
		{
			String str = getRoman(i);
			//System.out.println(i + " : " + str);
			int strn = str.length();
			for(int j = 0 ; j < strn; ++j)
			{
				char c = str.charAt(j);
				if(map.get(c) == null)
					map.put(c, 1);
				else
					map.put(c, map.get(c) + 1);
			}
		}
		print(map, pout);
		pout.close();
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		
		n = scr.nextInt();		
		
		go();		//Call main
		
		//OUTPUT	
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
