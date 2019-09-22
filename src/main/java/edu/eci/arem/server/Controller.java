package edu.eci.arem.server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class Controller 
{
	public static ExecutorService executor= Executors.newCachedThreadPool();
    public static void main( String[] args ) throws ClassNotFoundException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
    	executor.execute(new AppServer());
    }
}
