package com.eyeball.utils.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.eyeball.utils.misc.Utils;

/**
 * 
 * A class with reflection related things such as invoking methods, getting fields and more.
 * 
 * @author Eyeball
 * 
 */
public class ReflectionHelper {

	/**
	 * 
	 * Gets a list of all the annotations.
	 * 
	 * @param className
	 *            The class name.
	 * @return All the annotations.
	 * @throws ClassNotFoundException
	 *             If the specified class cannot be found.
	 */
	public static Annotation[] getAnnotations(String className)
			throws ClassNotFoundException {

		Class<?> theClass = Class.forName(className);

		ArrayList<Annotation> annoArrayList = new ArrayList<Annotation>();

		Annotation[] annoDeclared = theClass.getDeclaredAnnotations();

		Annotation[] anno = theClass.getAnnotations();

		Utils.arrayCopy(annoArrayList, annoDeclared);

		Utils.arrayCopy(annoArrayList, anno);

		return annoArrayList.toArray(new Annotation[] {});

	}

	/**
	 * 
	 * Gets a list of all the annotations.
	 * 
	 * @param method
	 *            The method.
	 * @return All the annotations.
	 */
	public static Annotation[] getAnnotations(Method method) {

		ArrayList<Annotation> annoArrayList = new ArrayList<Annotation>();

		Annotation[] annoDeclared = method.getDeclaredAnnotations();

		Annotation[] anno = method.getAnnotations();

		Utils.arrayCopy(annoArrayList, annoDeclared);

		Utils.arrayCopy(annoArrayList, anno);

		return annoArrayList.toArray(new Annotation[] {});

	}

	/**
	 * 
	 * Gets all the annotations of the specified type.
	 * 
	 * @param className
	 *            The class to check
	 * @param type
	 *            The Annotation type.
	 * @return The annotation, or null if it does not exist.
	 * @throws ClassNotFoundException
	 *             If the class cannot be found.
	 */
	public static <A extends Annotation> A getAnnotationWithType(
			String className, Class<A> type) throws ClassNotFoundException {
		Class<?> c = Class.forName(className);
		A anno = c.getAnnotation(type);
		return anno;
	}

	/**
	 * 
	 * Gets all the annotations of the specified type.
	 * 
	 * @param method
	 *            The method.
	 * @param type
	 *            The Annotation type.
	 * @return The annotation, or null if it does not exist.
	 */
	public static <A extends Annotation> A getAnnotationWithType(
			Method method, Class<A> type) {
		A anno = method.getAnnotation(type);
		return anno;
	}

	/**
	 * 
	 * Gets a class by name.
	 * 
	 * @param className
	 *            The name of the class, with the package.
	 * @return The class if it exists.
	 * @throws ClassNotFoundException
	 *             If the class cannot be found.
	 */
	public static Class<?> getClass(String className)
			throws ClassNotFoundException {
		return Class.forName(className);
	}

	/**
	 * 
	 * Gets a field from a specified class.
	 * 
	 * @param className
	 *            The name of the class, with the package.
	 * @param fieldToGet
	 *            The name of the field.
	 * @param instance
	 *            The instance to get the field from. May be null if it is a
	 *            static field, otherwise a NullPointerException will be thrown
	 * @return The field.
	 * @throws NoSuchFieldException
	 *             If the field does not exist.
	 * @throws SecurityException
	 *             If reading of the field is not allowed by the security
	 *             manager.
	 * @throws ClassNotFoundException
	 *             If the class cannot be found.
	 * @throws IllegalArgumentException
	 *             If instance is not the same as the class in className.
	 * @throws IllegalAccessException
	 *             Should not happen.
	 * @throws NullPointerException
	 *             If the field is non-static and instance is null.
	 * 
	 * @throws ClassCastException If the field type and type is not the same.
	 * 
	 * @author Eyeball
	 */
	public static <A extends Object> A getField(String className,
			String fieldToGet, Object instance, Class<A> type)
			throws NoSuchFieldException, SecurityException,
			ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException, NullPointerException, ClassCastException {
		Class<?> clazz = ReflectionHelper.getClass(className);
		Field field = clazz.getDeclaredField(fieldToGet);
		field.setAccessible(true);
		Object returnType = field.get(instance);

		@SuppressWarnings("unchecked")
		A tReturn = (A) returnType;

		return tReturn;
	}

	/**
	 * 
	 * Gets a method from a class.
	 * 
	 * @param className
	 *            The class which has the method.
	 * @param methodName
	 *            The method's name.
	 * @param argumentTypes
	 *            The arguments that the method takes.
	 * @return The method.
	 * @throws NoSuchMethodException
	 *             If the specified method does not exist.
	 * @throws SecurityException
	 *             If the security manager does not allow getting of the method.
	 * @throws ClassNotFoundException
	 *             If the specified class cannot be found.
	 * 
	 * @author Eyeball
	 * 
	 */
	public static Method getMethod(String className, String methodName,
			Class<?>... argumentTypes) throws NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		Class<?> clazz = ReflectionHelper.getClass(className);
		Method method = clazz.getDeclaredMethod(methodName, argumentTypes);
		method.setAccessible(true);
		return method;
	}

	/**
	 * 
	 * Invokes a method with a return type..
	 * 
	 * @param className
	 *            The class that has the method.
	 * @param methodName
	 *            The method's name.
	 * @param instance
	 *            The instance. If it is a static method, this may be null.
	 *            Otherwise a NullPointerException will be thrown.
	 * @param arguments
	 *            The method's arguments.
	 * @throws ClassNotFoundException
	 *             If the specified class cannot be found.
	 * @throws NoSuchMethodException
	 *             If the specified method does not exist.
	 * @throws SecurityException
	 *             If the security manager does not allow reading and getting of
	 *             the method.
	 * @throws IllegalAccessException
	 *             Should not happen.
	 * @throws IllegalArgumentException
	 *             If instance is not the same as the class in className.
	 * @throws InvocationTargetException
	 *             If an exception was thrown during invocation of the method.
	 * @throws NullPointerException
	 *             If instance is null and it is not a static method.
	 * 
	 * @return tHE RESULT.
	 */
	public static <A> A invokeMethod(String className, String methodName,
			Object instance, Class<A> type, Object... arguments)
			throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NullPointerException {

		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();

		for (Object object : arguments) {
			Class<?> c = object.getClass();
			classes.add(c);
		}

		Class<?>[] clazzes = classes.toArray(new Class[] {});

		Method method = ReflectionHelper.getMethod(className, methodName,
				clazzes);

		@SuppressWarnings("unchecked")
		A toReturn = (A) method.invoke(instance, arguments);

		return toReturn;
	}

	/**
	 * 
	 * Invokes a method with no return type..
	 * 
	 * @param className
	 *            The class that has the method.
	 * @param methodName
	 *            The method's name.
	 * @param instance
	 *            The instance. If it is a static method, this may be null.
	 *            Otherwise a NullPointerException will be thrown.
	 * @param arguments
	 *            The method's arguments.
	 * @throws ClassNotFoundException
	 *             If the specified class cannot be found.
	 * @throws NoSuchMethodException
	 *             If the specified method does not exist.
	 * @throws SecurityException
	 *             If the security manager does not allow reading and getting of
	 *             the method.
	 * @throws IllegalAccessException
	 *             Should not happen.
	 * @throws IllegalArgumentException
	 *             If instance is not the same as the class in className.
	 * @throws InvocationTargetException
	 *             If an exception was thrown during invocation of the method.
	 * @throws NullPointerException
	 *             If instance is null and it is not a static method.
	 * 
	 */
	public static void invokeMethod(String className, String methodName,
			Object instance, Object... arguments)
			throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NullPointerException {

		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();

		for (Object object : arguments) {
			Class<?> c = object.getClass();
			classes.add(c);
		}

		Class<?>[] clazzes = classes.toArray(new Class[] {});

		Method method = ReflectionHelper.getMethod(className, methodName,
				clazzes);

		method.invoke(instance, arguments);
	}

	/**
	 * 
	 * Checks if a class exists.
	 * 
	 * @param className
	 *            The class to find.
	 * @return If it was found.
	 */
	public static boolean classExists(String className) {
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}

}
