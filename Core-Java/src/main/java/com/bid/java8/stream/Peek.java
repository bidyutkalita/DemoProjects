package com.bid.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Peek {
	public static void main(String[] args) {
		List<String> stringList = new ArrayList<String>();

		stringList.add("abc");
		stringList.add("def");

		Stream<String> stream = stringList.stream();

		Stream<String> streamPeeked = stream.peek((value) -> {
		    System.out.println("value");
		});
//		streamPeeked.forEach((action)->{System.out.println(action);});
	}

}
