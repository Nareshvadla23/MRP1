import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;

import com.mongo.member.Testinter;

public class interimp {

	public static void main(String[] args) {

		Testinter test = (a, b) -> {
			return a + b;
		};

		System.out.println(test.add(2, 3));

		Random random = new Random();
		random.ints().limit(5).sorted().forEach(System.out::println);

		Integer[] arr = { 100, 100, 9, 8, 200 };

		List<Integer> list = Arrays.asList(arr);

		Integer min = list.stream().min(Comparator.naturalOrder()).get();
		Integer max = list.stream().max(Comparator.naturalOrder()).get();
		System.out.println(min);
		System.out.println(max);

		Integer b = list.stream().mapToInt((a) -> {
			return a;
		}).sum();

		System.out.println(b);

		double d = list.stream().mapToInt(a -> a * a).filter(a -> a >= 100).average().getAsDouble();

		System.out.println(d);
		Integer Fa = list.stream().findAny().get();
		Integer FF = list.stream().findFirst().get();

		System.out.println(Fa);
		System.out.println(FF);

		List<String> l1 = Arrays.asList("naresh,vasu");
		List<String> l2 = Arrays.asList("namo,rasu");
		List<String> l3 = Arrays.asList("nagi,easu");
		List<List<String>> la = new ArrayList<>();
		la.add(l1);
		la.add(l2);
		la.add(l3);
		la.stream().flatMap(a -> a.stream()).filter(c -> c.startsWith("n")).forEach(System.out::println);

		StringJoiner j = new StringJoiner(" ","(",")");
		j.add("Hi");
		j.add("naresh");
		j.add("How are you");
		System.out.println(j);
		
		Integer[] arr1 = { 100, 100, 9, 8, 200, 200};
		
		Set<Integer> set = new HashSet<>();
		
		List<Integer> sl= Arrays.asList(arr1);
		sl.stream().filter(a->!set.add(a)).forEach(System.out::println);

	}

}
