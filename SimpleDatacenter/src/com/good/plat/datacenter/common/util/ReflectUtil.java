package com.good.plat.datacenter.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReflectUtil {
	private Integer intVal;
	private Integer ints[];
	private ArrayList<Integer> list;
	private HashMap<String,Integer> hmap;
	private HashSet<Integer> hset;
	
	public static class MyClass<T>{
		private T val; 
	}
	
	public static void main(String[] args) {
		//System.out.println(ReflectUtil.getDeclaredFieldName(ReflectUtil.class));
		getDeclaredFields(ReflectUtil.class);
		//
		Method[] methods = ReflectUtil.class.getDeclaredMethods();
		for(Method m : methods){
			//System.out.println(m.getName() + "===============================");
			Class<?>[] pt = m.getParameterTypes();
			for(Class<?> p : pt){
				//System.out.println(p.getName());
				//System.out.println(p.getCanonicalName());
				//System.out.println(p.getModifiers());
			}
		}
		//
//		ReflectUtil obj = new ReflectUtil();
//		System.out.println(isInstance(Object.class,obj));//true
//		System.out.println(isInstance(Object.class,new Object()));//true
		
		//
//		System.out.println(List.class.isAssignableFrom(ArrayList.class));//true
//		System.out.println(ArrayList.class.isAssignableFrom(List.class));//false
//		System.out.println(List.class.isAssignableFrom(Map.class));//false
	
		//
//		Integer arr[] = new Integer[1];
//		System.out.println(arr.getClass().isArray());//true
//		System.out.println(arr.getClass().getTypeName());//java.lang.Integer[]
//		System.out.println(arr.getClass().getComponentType());//class java.lang.Integer
//		System.out.println(arr.getClass().getComponentType() == Integer.class);//true
		
		//
		
	}
	
	
	
	public static boolean isInstance(Class cls,Object obj){
		return cls.isInstance(obj);
	}
	
	public Class<?>[] getMethodParameterTypes(Method method){
		return method.getParameterTypes();
	}
	
	/**
	 * 用getDeclaredFields就能得到private 和public protend protected 的字段 ,如果用c.getFields();只能得到public类型的属性 
	 * @Title: getDeclaredMethods
	 * @Description: TODO
	 * @param cls
	 * @return
	 * Method[]
	 * @author dmw
	 * @date 2016年7月28日 上午9:33:31
	 */
	public static Method[] getDeclaredMethods(Class cls){
		Method[] methods = cls.getDeclaredMethods();
		
		return methods;
	}
	
	public static void setFieldValue(Field field,Object instance,Object value) throws IllegalArgumentException, IllegalAccessException{
		field.setAccessible(true);
		field.set(instance, value);
		field.setAccessible(false);
	}
	
	public static Method getDeclaredMethod2(Class cls,String methodName,Class...parameterTypes) throws NoSuchMethodException, SecurityException{
		return cls.getDeclaredMethod(methodName, parameterTypes);
	}
	
	public static Method[] getPublicMethod(Class cls){
		return cls.getMethods();
	}
	
	public static Field[] getPublicField(Class cls){
		return cls.getFields();
	}
	
	public static Object invokeMethod(Method method,Object instance,Object...args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		method.setAccessible(true);
		Object ret = method.invoke(instance, args);
		method.setAccessible(false);
		return ret;
	}
	
	public static void invokeSetter(Class cls,Object instance,String fieldName,Class parameterType,Object... val) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = getDeclaredMethod(cls, toSetterMethodName(fieldName),parameterType);
		method.invoke(instance, val);
	}
	
	public static Object invokeGetter(Class cls,Object instance,String fieldName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method method = getDeclaredMethod(cls, toGetterMethodName(fieldName));
		return method.invoke(instance);
	}
	

	
	public static Field[] getDeclaredFields(Class cls) {
		Field fields [] = cls.getDeclaredFields();
		if(fields != null){
			for(Field field : fields){
				//System.out.println(field.getName());
				//System.out.println(field.getType().getClass());//class java.lang.Class
				//System.out.println(field.getType());//class java.lang.Integer
				//	field.getType().newInstance();// 如果 field 为 Integer 则会报错
				//System.out.println(field.getType() == Integer.class);//true
				//System.out.println(field.getType().getComponentType());
//				try {
//					Type[] types = getCollectExcutableParamerizedType(field,cls);
//					for(Type c : types){
//						System.out.println((Class)c);
//					}
//				}catch (Exception e) {
//					e.printStackTrace();
//				}
			}
		}
		return fields;
	}
	
	/**
	 * 1. 
	 * @Title: detectCollectFieldComponentType
	 * @Description: TODO
	 * @param cls
	 * @param field
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * Class
	 * @author moxw
	 * @date 2016年7月27日 下午3:29:55
	 */
	public static Class detectCollectFieldComponentType(Class cls,Field field) throws NoSuchMethodException, SecurityException{
		if(Map.class.isAssignableFrom(field.getType())){
			
		}else if(Collection.class.isAssignableFrom(field.getType())
				|| List.class.isAssignableFrom(field.getType())
				|| Set.class.isAssignableFrom(field.getType())){
			return (Class) getCollectExcutableParamerizedType(field,cls)[0];
		}
		return null;
	}
	
	public static Type[] getCollectExcutableParamerizedType(Field field,Class cls) throws NoSuchMethodException, SecurityException{
		Method method = getDeclaredMethod(cls, toSetterMethodName(field.getName()),field.getType());
		Type[] type = method.getGenericParameterTypes();
		ParameterizedType pType = null;
		if(ParameterizedType.class.isAssignableFrom(type[0].getClass())){
			pType = (ParameterizedType) type[0];
			//[class java.lang.Integer],[class [Ljava.lang.Integer;],[java.util.ArrayList<java.lang.Integer>],[java.util.HashMap<java.lang.String, java.lang.Integer>],[java.util.HashSet<java.lang.Integer>]
			//private HashMap<String,Integer> hmap;
			System.out.println(pType.getRawType());//class java.util.HashSet
			System.out.println(pType.getActualTypeArguments());//[Ljava.lang.reflect.Type;@7852e922

			return pType.getActualTypeArguments();
		}
		return new Type[0];
	}
	
	
	@Deprecated
	public static List<String> getDeclaredFieldName(Class cls){
		List<String> nameList = new ArrayList();
		Field[] fields = getDeclaredFields(cls);
		if(fields != null){
			for(Field field : fields){
				nameList.add(field.getName());
			}
		}
		return nameList;
	}
	
	public static Object invokeMethodOnInstance(Object classInstance,Method method,Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return method.invoke(classInstance, args);
	}
	
	/**
	 * 
java.lang.Class
java.lang.String
[Ljava.lang.Class;(getName)
java.lang.Class[] (getCanonicalName)


	 * @param cls
	 * @param name
	 * @param parameterTypes
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static Method getDeclaredMethod(final Class cls,final String name,Class... parameterTypes) throws NoSuchMethodException, SecurityException{
		Method method = cls.getMethod(name, parameterTypes);
		return method;
	}
	
	
	
	public static String toGetterMethodName(String fieldName){
		return prependPrefixMethodName(fieldName,"get");
	}
	public static String toSetterMethodName(String fieldName){
		return prependPrefixMethodName(fieldName,"set");
	}
	
	public static String prependPrefixMethodName(String fieldName,String prefix){
		return prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	
	
	
	public Integer getIntVal() {
		return intVal;
	}

	public void setIntVal(Integer intVal) {
		this.intVal = intVal;
	}
	public Integer[] getInts() {
		return ints;
	}
	public void setInts(Integer[] ints) {
		this.ints = ints;
	}
	public ArrayList<Integer> getList() {
		return list;
	}
	public void setList(ArrayList<Integer> list) {
		this.list = list;
	}
	public HashMap<String, Integer> getHmap() {
		return hmap;
	}
	public void setHmap(HashMap<String, Integer> hmap) {
		this.hmap = hmap;
	}
	public HashSet<Integer> getHset() {
		return hset;
	}
	public void setHset(HashSet<Integer> hset) {
		this.hset = hset;
	}
	
	
}
