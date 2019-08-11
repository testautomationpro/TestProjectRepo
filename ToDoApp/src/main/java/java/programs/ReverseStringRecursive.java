package java.programs;

public class ReverseStringRecursive {
	
	public static void main(String []str) {
		String s="MyJava";
	
		System.out.println(recursiveReverse(s));
	}
	
		static String recursiveReverse(String s)
		{
			if((null==s) || (s .length() <= 1))
			{
				return s;
			}
				return recursiveReverse(s.substring(1)) + s.charAt(0);
						
		
	}

}
