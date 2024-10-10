package com.github.ishanmihir19.learn.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.github.ishanmihir19.learn.domain.Student;
import com.github.ishanmihir19.learn.interfaces.MyFunctionalInterface;

@Service
@SuppressWarnings("unused")
public class JavaStreamService {

	private static List<Student> allStudents;

	static {
		allStudents = Arrays.asList(new Student("Jasmine Kaur", "jm@jmx.com", 23, "CSE", 79),
				new Student("Karan Jindal", "kj@apple.com", 22, "EEE", 65),
				new Student("Sherry Kaur", "sk@uiet.com", 25, "ECE", 30),
				new Student("Ashutosh Rana", "ashu@uiet.com", 27, "ECE", 72),
				new Student("Siddharth Malhotra", "sid@iitk.com", 23, "CSE", 89),
				new Student("Siddharth Malhotra", "sid@iima.com", 23, "MBA", 99));
	};

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		// Reading data using readLine
		String name = reader.readLine();

		// Printing the read line
		System.out.println(name);

		getHighestScoreByDept(allStudents);
	}

	/**
	 * Learn Streams
	 */
	private static void learnStreams() {
		Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
		Stream<String> buildedStream = Stream.<String>builder().add("a").add("b").add("c").build();
		Stream<String> generatedStream = Stream.generate(() -> "Abc").limit(5);
		Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);
		System.out.println(Stream.iterate(2, n -> n * 2).limit(5).collect(Collectors.toList()));

		IntStream intStream = IntStream.of(1, 2, 3);
		int intStream2 = IntStream.range(14, 15).sorted().peek(System.out::println).max().getAsInt();
		System.out.println("Max Int : " + intStream2);
		Random random = new Random();
		DoubleStream doubleStream = random.doubles(3);
		// System.out.println(streamIterated.collect(Collectors.toList()));

		Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e)).map(String::toUpperCase)
				.peek(e -> System.out.println("Mapped value: " + e)).collect(Collectors.toList());
	}

	/**
	 * STREAMS.MATCH
	 *
	 * @param allStudents
	 */
	private static void learnMatch(List<Student> allStudents) {
		System.out.println("All Match : " + allStudents.stream().allMatch(s -> s.getAge() == 25));
		System.out.println("Any Match : " + allStudents.stream().anyMatch(s -> s.getAge() == 25));
		System.out.println("None Match : " + allStudents.stream().noneMatch(s -> s.getAge() == 25));
	}

	/**
	 * STREAMS.GROUPINGBY
	 *
	 * @param allStudents
	 */
	private static void learnGroupingBy(List<Student> allStudents) {
		Map<String, Optional<Student>> collect = allStudents.stream().collect(
				Collectors.groupingBy(Student::getDept, Collectors.maxBy((s1, s2) -> s1.getScore() - s2.getScore())));
		System.out.println(collect);
	}

	/**
	 * STREAMS.REDUCE
	 *
	 * @param allStudents
	 */
	private static void learnReduce(List<Student> allStudents) {

		List<String> strings = Arrays.asList("Through", "the", "thick", "forest", "of", "Gambia");
		System.out.println(strings.stream().reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2).get());

		OptionalInt reduceSum = allStudents.stream().mapToInt(Student::getScore).reduce(Integer::sum);
		System.out.println(reduceSum.getAsInt());
	}

	/**
	 * STREAMS.PREDICATE
	 *
	 * @param allStudents
	 */
	private static void learnPredicate(List<Student> allStudents) {
		Predicate<Student> testAgePredicate = t -> t.getAge() >= 23;
		Predicate<Student> finalPredicate = testAgePredicate.and(t -> t.getScore() > 80).or(t -> t.getAge() % 2 == 0)
				.negate();
		System.out.println(finalPredicate.test(allStudents.get(0)));
		System.out.println(allStudents.stream().filter(t -> t.getScore() > 50).filter(t -> t.getAge() >= 23)
				.collect(Collectors.toList()).size());

		BiPredicate<Integer, Integer> bi = (i, j) -> i + j > 2;

	}

	/**
	 * STREAMS.FUNCTION
	 *
	 * @param allStudents
	 */
	private static void learnFunctions(List<Student> allStudents) {
		Function<List<Student>, List<Student>> func = (s) -> {
			List<Student> newStudents = new ArrayList<>();
			for (Student student : s) {
				if (student.getAge() > 22 && student.getScore() > 80) {
					newStudents.add(student);
				}
			}
			return newStudents;
		};

		Function<Integer, Integer> doubleIt = i -> i * 2;
		Function<Integer, Integer> squareIt = i -> i * i;

		System.out.println(doubleIt.andThen(squareIt).apply(3));
		System.out.println(squareIt.andThen(doubleIt).apply(3));

		BiFunction<Integer, Integer, Integer> add = (i, j) -> i + j;
		BiFunction<Integer, Integer, Integer> multiply = (i, j) -> i * j;
		Integer addAndDouble = add.andThen(doubleIt).apply(2, 3);
		Integer multiplyAndDouble = multiply.andThen(doubleIt).apply(2, 3);
		System.out.println("Add and Double : " + addAndDouble);
		System.out.println("Multiple and Double : " + multiplyAndDouble);
	}

	/**
	 * STREAMS.CONSUMER
	 *
	 * @param allStudents
	 */
	private static void learnConsumer(List<Student> allStudents) {

		Consumer<Student> sConsumer = Student::getAge;
		sConsumer.accept(allStudents.get(0));

		Consumer<Integer> doubleIt = i -> System.out.println("Doubling it : " + i * 2);
		Consumer<Integer> squareIt = i -> System.out.println("Squaring it : " + i * i);

		doubleIt.andThen(squareIt).accept(3);
		squareIt.andThen(doubleIt).accept(3);
	}

	/**
	 * STREAMS.SUPPLIER
	 */
	private static void learnSupplier() {
		Supplier<Integer> supplier = () -> Year.now().getValue();
		System.out.println(supplier.get());
	}

	/**
	 * SOLVE : count occurrence of each alphabet in a sentence.
	 */
	private static void countOfEachAlphabet() {
		String checkString = "StringBuilder notification";
		Map<String, Long> collect = Stream.of(checkString.split(""))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(collect);
	}

	/**
	 * SOLVE : Get highest score by department
	 *
	 * @param allStudents
	 */
	private static void getHighestScoreByDept(List<Student> allStudents) {

		TreeMap<String, Optional<Student>> collect = allStudents.stream().collect(Collectors.groupingBy(
				Student::getDept, TreeMap::new, Collectors.maxBy((s1, s2) -> s1.getScore() - s2.getScore())));
		System.out.println(collect);
	}

	/**
	 * SOLVE : Find duplicate names in a list
	 *
	 * @param allStudents
	 */
	private static void findDuplicates(List<Student> allStudents) {

		List<String> listOfAllNames = allStudents.stream().map(Student::getName).toList();

		// Approach 1
		Map<String, Long> map = allStudents.stream().map(Student::getName)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		Set<String> approach1 = map.entrySet().stream().filter(entry -> entry.getValue() > 1)
				.map(entry -> entry.getKey()).collect(Collectors.toSet());
		System.out.println(approach1);

		// Approach 2
		Set<String> approach2 = listOfAllNames.stream().filter(s -> (Collections.frequency(listOfAllNames, s) > 1))
				.collect(Collectors.toSet());
		System.out.println(approach2);

		// Approach 3
		Set<String> uniqueNames = new HashSet<>();
		Set<String> approach3 = listOfAllNames.stream().filter(s -> !uniqueNames.add(s)).collect(Collectors.toSet());
		System.out.println(approach3);
	}

	/**
	 * SOLVE : reverse a string using streams
	 */
	private static void reverseStringUsingStream() {
		String aString = "string";
		StringBuilder sb = new StringBuilder();
		aString.chars().mapToObj(c -> (char) c).forEachOrdered(p -> sb.insert(0, p));
		System.out.println(sb);
		System.out.println(new StringBuilder(aString).reverse());
	}

	/**
	 * STREAMS.STATISTICS
	 */
	private static void summaryStatistics() {
		List<Integer> list = Arrays.asList(1, 3, 55, 22, 53, 23, 232);
		IntSummaryStatistics stats = list.stream().collect(Collectors.summarizingInt(t -> t));
		System.out.println(stats.getAverage());
		System.out.println(stats.getCount());
		System.out.println(stats.getMax());
		System.out.println(stats.getMin());
		System.out.println(stats.getSum());
	}

	/**
	 * SOLVE : Multiple Stream questions
	 */
	private static void streamQuestions() {

		List<Integer> list = Arrays.asList(1, 3, 55, 22, 53, 23, 232, 55, 1);
		List<Integer> list2 = Arrays.asList(2, 4, 1, 5, 3, 6, 2);
		/**
		 * QUESTION 1 : Find all starting with 2
		 */
		System.out.println("QUESTION 1 : Find all starting with 2");
		list.stream().map(i -> i.toString()).filter(s -> s.startsWith("2")).forEach(System.out::println);
		System.out.println();

		/**
		 * QUESTION 2 : Find all duplicate values
		 */
		System.out.println("QUESTION 2 : Find all duplicate values");
		list.stream().filter(i -> Collections.frequency(list, i) > 1).distinct().forEach(System.out::println);
		System.out.println();

		/**
		 * QUESTION 3 : Sort in reverse order
		 */
		System.out.println("QUESTION 3 : Sort in reverse order ");
		list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
		System.out.println();

		/**
		 * QUESTION 4 : Find if list contains duplicates
		 */
		System.out.println("QUESTION 4 : Find if list contains duplicates ");
		list.stream().filter(i -> Collections.frequency(list, i) > 1).count(); // OR
		System.out.println("Has duplicates : " + (list.stream().distinct().count() != list.size()));
		System.out.println();

		/**
		 * QUESTION 5 : Square the number and filter all above 50
		 */
		System.out.println("QUESTION 5 : Square the number and filter all above 50");
		list.stream().map(t -> t * t).filter(s -> s < 50).forEach(System.out::println);
		System.out.println();

		/**
		 * QUESTION 6 : Sort an array first and then create stream
		 */
		System.out.println("QUESTION 6 : Sort an array first and then create stream ");
		list.sort((t1, t2) -> t1 - t2);
		list.stream().forEach(System.out::println);
		System.out.println();

		/**
		 * QUESTION 7 : Concat two streams
		 */
		System.out.println("QUESTION 7 : Concat two streams");
		Stream.concat(list.stream(), list2.stream()).forEach(System.out::print);
		System.out.println();

		/**
		 * QUESTION 8 :Print 5 random integers
		 */
		System.out.println("QUESTION 8 : Print 5 random integers");
		Random random = new Random();
		random.ints(5).forEach(System.out::println); // OR
		Stream.generate(random::nextInt).limit(5).forEach(System.out::println);
		System.out.println();

		/**
		 * QUESTION 9 : Use optional to find if a list is null or not.
		 */
		System.out.println("QUESTION 9 : Use optional to find if a list is null or not. ");
		Optional.ofNullable(list).ifPresentOrElse(System.out::println, () -> System.out.println("It's a null list"));
		Optional.ofNullable(null).ifPresentOrElse(System.out::println, () -> System.out.println("It's a null list"));
		System.out.println();

		/**
		 * QUESTION 10 : Find the number of occurrence of each number in list
		 */
		System.out.println("QUESTION 10 : Find the number of occurrence of each number in list. ");
		list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.forEach(t -> System.out.println(t.getKey() + " : " + t.getValue()));
		;
		System.out.println();

		/**
		 * QUESTION 11 : Find the sum of marks of all students
		 */
		System.out.println("QUESTION 11 : Find the sum of marks of all students .");
		System.out.println(allStudents.stream().map(Student::getScore).reduce(0, (a, b) -> a + b)); // OR
		System.out.println(allStudents.stream().mapToInt(Student::getScore).sum()); // OR
		System.out.println(
				allStudents.stream().map(Student::getScore).collect(Collectors.summarizingInt(t -> t)).getSum());
		System.out.println();

		/**
		 * QUESTION 12 : LEETCODE https://leetcode.com/problems/single-number/
		 */
		System.out.println("QUESTION 12 : LEETCODE https://leetcode.com/problems/single-number/");
		int[] nums = { 4, 1, 2, 1, 2 };
		List<Integer> list3 = IntStream.of(nums).boxed().collect(Collectors.toList());
		int i = IntStream.of(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream().filter(t -> t.getValue() == 1).mapToInt(t -> t.getKey()).sum();
		System.out.println("Answer is " + i);

		int result = 0;
		for (int j = 0; j < nums.length; j++) {
			result = result ^ nums[i];

			System.out.println("result : " + result + ", j:" + j + " nums[i]: " + nums[j]);
		}
		System.out.println(result);

		/**
		 * QUESTION 13 : LEETCODE
		 * https://leetcode.com/problems/valid-anagram/description/
		 */
		System.out.println("QUESTION 13 : LEETCODE https://leetcode.com/problems/single-number/");
		String s = "anagram";
		String t = "nagaram";
		if (s.length() != t.length()) {
			System.out.println(s + " and " + t + " are NOT Anagrams");
		} else {
			Map<Character, Long> mapS = s.chars().mapToObj((j) -> (char) j)
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			Map<Character, Long> mapT = t.chars().mapToObj((j) -> (char) j)
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

			System.out.println(s + " and " + t + " are Anagrams : " + mapS.equals(mapT));
			System.out.println();
		}

		/**
		 * QUESTION 14 : LEETCODE
		 * https://leetcode.com/problems/valid-parentheses/description/
		 */
		System.out.println("QUESTION 14 : LEETCODE https://leetcode.com/problems/valid-parentheses/description/");
		s = "([)]";
		Stack<Character> charsStack = new Stack<>();
		boolean isBalanced = true;
		for (i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
				charsStack.push(s.charAt(i));
			} else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
				if (charsStack.isEmpty()) {
					isBalanced = false;
					break;
				} else {
					char outGoingChar = charsStack.pop();
					if ((s.charAt(i) == ')' && outGoingChar != '(') || (s.charAt(i) == ']' && outGoingChar != '[')
							|| (s.charAt(i) == '}' && outGoingChar != '{')) {
						isBalanced = false;
						break;
					}
				}
			}
		}
		if (isBalanced && charsStack.isEmpty()) {
			System.out.println("String is balanced...");
		} else {
			System.out.println("String is NOT balanced...");
		}
	}

	public void test() {
		streamQuestions();
	}

}
