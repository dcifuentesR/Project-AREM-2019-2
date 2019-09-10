/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arem.handlers;

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
    
    public String process(Object[] parameters) {
    	try {
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
