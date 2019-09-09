/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arem.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author 2116387
 */
public class StaticMethodHandler implements Handler{
    
    private Method method;
    
    public StaticMethodHandler(Method method) {
    	this.method = method;
    }

    public String process() {
        try {
			return method.invoke(null, null).toString();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    public String process(Object[] parameters) {
    	try {
//    		Class<?> classes[] = method.getParameterTypes();
//    		for(int i=0;i<classes.length;i++)
//    			parameters[i]=classes[i].cast(parameters[i]);
    		for(Object o:parameters)
    			System.out.print(o.getClass().getTypeName());
			return method.invoke(null, parameters).toString();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }

	
    
}
