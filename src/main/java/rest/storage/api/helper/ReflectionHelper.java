package rest.storage.api.helper;

import java.lang.reflect.Field;

import com.google.appengine.api.datastore.Entity;


public class ReflectionHelper {
	public static void setPropertiesFromEntity(Class cl, Object target, Entity source) {
		try
		{
			for(Field f : cl.getDeclaredFields()) {
				cl.getDeclaredMethod(getUppercaseSET(f.getName()), f.getType()).invoke(target, source.getProperty(f.getName()));
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public static Entity setPropertiesToEntity(Class cl, Object source, Entity target) {
		try
		{
			for(Field f : cl.getDeclaredFields()) {
				
				Object value = cl.getMethod(getUppercaseGET(f.getName())).invoke(source);
				target.setProperty(f.getName(), value);
				//System.out.println(String.format("Reflector | %s =====> %s ", f.getName(), value));
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return target;
	}
	
	private static String getUppercaseGET(String s) {
		char[] stringArray = s.toCharArray();
		stringArray[0] = Character.toUpperCase(stringArray[0]);
		return "get" + new String(stringArray);
	}
	
	private static String getUppercaseSET(String s) {
		char[] stringArray = s.toCharArray();
		stringArray[0] = Character.toUpperCase(stringArray[0]);
		return "set" + new String(stringArray);
	}
}
