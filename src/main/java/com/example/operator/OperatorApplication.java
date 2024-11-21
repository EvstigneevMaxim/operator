package com.example.operator;

import com.example.operator.util.Operator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class OperatorApplication {
	public static void main(String[] args) {
		ArrayList<String> list1 = new ArrayList<>(List.of("abc", "de", "f"));
		ArrayList<String> result1 = Operator.modify(list1).add("1234").add(list1).add("zzzzz").get();
		System.out.println(result1);
		// [abc, de, f, 1234, abc, de, f, 1234, zzzzz]
	}
}