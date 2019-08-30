/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arem.server;
import edu.eci.arem.apps.mean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 2116387
 */
public class AppServer {
    
    private Map<String,Handler> URLHandlerMap = new HashMap();
    
    public AppServer(){
        
    }

    public static void initialize() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class mean = Class.forName("edu.eci.arem.apps.mean");
        
        for(Method m:mean.getMethods()){
            if(m.isAnnotationPresent(Web.class))
                System.out.print(m.invoke(null, null));
        }
        
    }

    public static void listen() throws IOException {
        ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(35000);
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		Socket clientSocket = null;
		
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(
						clientSocket.getInputStream()));
                
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();

    }

}
