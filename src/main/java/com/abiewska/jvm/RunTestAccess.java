package com.abiewska.jvm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

public class RunTestAccess {

	public static ArrayList<Double> timeReadSimple = new ArrayList<Double>();
	public static ArrayList<Double> timeWriteSimple = new ArrayList<Double>();
	public static ArrayList<Double> timeReadSimpleReflection = new ArrayList<Double>();
	public static ArrayList<Double> timeWriteSimpleReflection = new ArrayList<Double>();

	public static ArrayList<Double> timeReadReference = new ArrayList<Double>();
	public static ArrayList<Double> timeWriteReference = new ArrayList<Double>();
	public static ArrayList<Double> timeReadReferenceReflection = new ArrayList<Double>();
	public static ArrayList<Double> timeWriteReferenceReflection = new ArrayList<Double>();

	public static ArrayList<Double> timeMethod = new ArrayList<Double>();
	public static ArrayList<Double> timeMethodReflection = new ArrayList<Double>();

	public void runTestAccess() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException, NoSuchFieldException {
		TestAccess test = new TestAccess();

		timeReadSimple.add(test.readSimple());
		timeWriteSimple.add(test.writeSimple());
		timeReadSimpleReflection.add(test.readSimpleReflection());
		timeWriteSimpleReflection.add(test.writeSimpleReflection());
		timeReadReference.add(test.readReference());
		timeWriteReference.add(test.writeReference());
		timeReadReferenceReflection.add(test.readReferenceReflection());
		timeWriteReferenceReflection.add(test.writeReferenceReflection());
		timeMethod.add(test.method());
		timeMethodReflection.add(test.methodReflection());

	}

	public void removeOutsiders(ArrayList<Double> times) {

		times.remove(times.indexOf(Collections.min(times)));
		times.remove(times.indexOf(Collections.max(times)));
	}

	public double averageTimes(ArrayList<Double> times) {
		double avTime = 0;
		for (int i = 0; i < times.size(); i++) {
			avTime += times.get(i);
		}
		return Math.round((avTime / times.size()) * 100.0) / 100.0;
	}

	public void prepareTimes() {
		removeOutsiders(timeReadSimple);
		removeOutsiders(timeWriteSimple);
		removeOutsiders(timeReadSimpleReflection);
		removeOutsiders(timeWriteSimpleReflection);
		removeOutsiders(timeReadReference);
		removeOutsiders(timeWriteReference);
		removeOutsiders(timeReadReferenceReflection);
		removeOutsiders(timeWriteReferenceReflection);
		removeOutsiders(timeMethod);
		removeOutsiders(timeMethodReflection);
	}

	public void warmup() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		Animal test = new Animal();
		Class<?> cls = Class.forName("com.abiewska.jvm.Animal");
		Object rtest = cls.newInstance();
		for (int i = 0; i < 10000; i++) {
			test.weight = 56;
			Field field = rtest.getClass().getField("weight");
			field.setInt(rtest, 65);
		}

	}

	public void print() {
		prepareTimes();
		
		System.out.println("W ramach testu 10000 wywolan.");
		
		System.out.println("Sredni czas odczytu pole publiczne, typ prosty: "
				+ averageTimes(timeReadSimple) + " ns");
		System.out.println("Sredni czas zapisu pole publiczne, typ prosty: "
				+ averageTimes(timeWriteSimple) + " ns");

		System.out
				.println("Sredni czas odczytu pole publiczne, typ prosty, refleksja: "
						+ averageTimes(timeReadSimpleReflection) + " ns");
		System.out
				.println("Sredni czas zapisu pole publiczne, typ prosty, refleksja: "
						+ averageTimes(timeWriteSimpleReflection) + " ns");

		System.out
				.println("Sredni czas odczytu pole publiczne, typ referencyjny: "
						+ averageTimes(timeReadReference) + " ns");
		System.out
				.println("Sredni czas zapisu pole publiczne, typ referencyjny: "
						+ averageTimes(timeWriteReference) + " ns");

		System.out
				.println("Sredni czas odczytu pole publiczne, typ referencyjny, refleksja: "
						+ averageTimes(timeReadReferenceReflection) + " ns");
		System.out
				.println("Sredni czas odczytu pole publiczne, typ referencyjny, refleksja: "
						+ averageTimes(timeWriteReferenceReflection) + " ns");

		System.out.println("Sredni czas wywolania metody: "
				+ averageTimes(timeMethod) + "");
		System.out.println("Sredni czas wywolania metody, refleksja: "
				+ averageTimes(timeMethodReflection) + " ns");

	}

}
