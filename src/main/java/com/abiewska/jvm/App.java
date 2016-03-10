package com.abiewska.jvm;

import java.lang.reflect.InvocationTargetException;

public class App {

	public static void main(String[] args) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			InstantiationException, ClassNotFoundException,
			NoSuchFieldException, SecurityException, NoSuchMethodException {

		RunTestAccess runTest = new RunTestAccess();
		runTest.warmup();
		for (int i = 0; i < 30; i++) {
			runTest.runTestAccess();
		}
		
		runTest.print();

	}

}
