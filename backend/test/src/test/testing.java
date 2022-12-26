package test;

public class testing {

	public static void main(String[] args) {

		String s1 = "naresh";
		String s4 = "naresh";

		String s3 = new String("naresh");

		System.out.println(s1);

		String s2 = s1.concat("chary");

		System.out.println(s2);

		System.out.println(s1.hashCode() + " " + s4.hashCode());
		
		

	}

}
