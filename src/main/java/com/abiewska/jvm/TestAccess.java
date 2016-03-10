package com.abiewska.jvm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestAccess {
	public final static int M = 10000;

	public double readSimple() {
		double start, end;
		Animal test = new Animal();
		int tweight;

		start = System.nanoTime();
		for (int i = 0; i < M; i++) {
			tweight = test.weight;
		}
		end = System.nanoTime();

		return (end - start);
	}

	public double writeSimple() {
		double start, end;
		Animal test = new Animal();

		start = System.nanoTime();
		for (int i = 0; i < M; i++) {
			test.weight = 55;
		}
		end = System.nanoTime();

		return (end - start);
	}

	public double readSimpleReflection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			NoSuchFieldException, SecurityException {
		double start, end;

		Class<?> cls = Class.forName("com.abiewska.jvm.Animal");
		Object test = cls.newInstance();

		int tweight;

		start = System.nanoTime();
		for (int i = 0; i < M; i++) {
			Field field = test.getClass().getField("weight");
			tweight = field.getInt(test);
		}
		end = System.nanoTime();

		return (end - start);
	}

	public double writeSimpleReflection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			NoSuchFieldException, SecurityException {
		double start, end;

		Class<?> cls = Class.forName("com.abiewska.jvm.Animal");
		Object test = cls.newInstance();

		start = System.nanoTime();
		for (int i = 0; i < M; i++) {
			Field field = test.getClass().getField("weight");
			field.setInt(test, 67);
		}
		end = System.nanoTime();

		return (end - start);
	}

	public double readReference() {
		double start, end;
		Animal test = new Animal();
		String tname;

		start = System.nanoTime();
		for (int i = 0; i < M; i++) {
			tname = test.name;
		}
		end = System.nanoTime();

		return (end - start);
	}

	public double writeReference() {
		double start, end;
		Animal test = new Animal();

		start = System.nanoTime();
		for (int i = 0; i < M; i++) {
			test.name = "Kot";
		}
		end = System.nanoTime();

		return (end - start);
	}

	public double readReferenceReflection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			NoSuchFieldException, SecurityException {
		double start, end;

		Class<?> cls = Class.forName("com.abiewska.jvm.Animal");
		Object test = cls.newInstance();

		String tname;

		start = System.nanoTime();
		for (int i = 0; i < M; i++) {
			Field field = test.getClass().getField("name");
			tname = (String) field.get(test);
		}
		end = System.nanoTime();

		return (end - start);
	}

	public double writeReferenceReflection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			NoSuchFieldException, SecurityException {
		double start, end;

		Class<?> cls = Class.forName("com.abiewska.jvm.Animal");
		Object test = cls.newInstance();

		start = System.nanoTime();
		for (int i = 0; i < M; i++) {
			Field field = test.getClass().getField("name");
			field.set(test, "Kaczka");
		}
		end = System.nanoTime();

		return (end - start);
	}

	public double method() {
		double start, end;
		Animal test = new Animal();

		start = System.nanoTime();
		for (int i = 0; i < M; i++) {
			test.voice("Miau");
		}
		end = System.nanoTime();

		return (end - start);
	}

	public double methodReflection() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException {
		double start, end;

		Class<?> cls = Class.forName("com.abiewska.jvm.Animal");
		Object test = cls.newInstance();

		start = System.nanoTime();
		for (int i = 0; i < M; i++) {
			Method method = test.getClass().getMethod("voice", String.class);
			method.invoke(test, "Hau");
		}
		end = System.nanoTime();

		return (end - start);
	}
}
