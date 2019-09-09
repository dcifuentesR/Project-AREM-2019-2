/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arem.server;

import net.sf.image4j.codec.ico.ICODecoder;
import net.sf.image4j.codec.ico.ICOEncoder;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

/**
 *
 * @author 2116387
 */
public class AppServer {

	private static Map<String, Handler> URLHandlerMap = new HashMap<String, Handler>();

	public AppServer() {

	}

	public static void initialize() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		
		Reflections reflections = new Reflections("edu.eci.arem.apps", new SubTypesScanner(false));
		Set<Class<? extends Object>> classes = reflections.getSubTypesOf(Object.class);
		for(Class c:classes)
			for(Method m:c.getMethods())
				if(m.isAnnotationPresent(Web.class)) 
					URLHandlerMap.put("/apps/"+m.getAnnotation(Web.class).value(), new StaticMethodHandler(m));
	}

	public static void listen() throws IOException {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(getPort());
		} catch (IOException e) {
		}

		Socket clientSocket = null;

		PrintWriter out;
		BufferedReader in;

		while (true) {
			try {
				System.out.println("Ready to recieve...");
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("Accept failed...");
				e.printStackTrace();
			}
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine = null;
			String request = null;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.matches("(GET)+.*"))
					request = inputLine.split(" ")[1];
				if (!in.ready())
					break;
			}
			System.out.println(request);
			request = request == null ? "/error.html" : request;
            request = request.equals("/") ? "/index.html" : request;
			if (request.matches("(/apps).*")) {
				System.out.print(URLHandlerMap.keySet());
				handleMethodCalls(out, request);

			} else if (request.matches(".*(.html)")) {
				handleHtml(out, request);
			} else if (request.matches(".*(.PNG)")) {
				handleImages(out, clientSocket.getOutputStream(), request);
			} else if (request.matches(".*(.ico)")) {
				handleFavicon(out, clientSocket.getOutputStream(), request);
			}

			
			out.close();
			in.close();
			
		}
//		clientSocket.close();
//		serverSocket.close();

	}

	private static void handleMethodCalls(PrintWriter out, String request) {
		Object[] parameters = getParameters(request);
		request = request.contains("?") ? request.substring(0, request.indexOf("?")) : request;

		if (URLHandlerMap.containsKey(request)) {
			out.println("HTTP/1.1 200 OK\r");
			out.println("Content-Type: text/html\r");
			out.println("\r");
			out.println(URLHandlerMap.get(request).process(parameters));
		}
	}

	private static Object[] getParameters(String request) {
		Object parameters[] = null;

		if (request.matches("(/apps/)[a-z]+[?]+[A-Z,=,&,a-z,0-9,.]*")) {
			String[] s = request.split("[?]")[1].split("[&]");
			parameters = new Object[s.length];
			for (int i = 0; i < parameters.length; i++)
				parameters[i] = s[i].split("=")[1];
		}
		
		return parameters;
	}

	public static void handleImages(PrintWriter out, OutputStream outStream, String request) {
		try {
			out.println("HTTP/1.1 200 OK\r");
			out.println("Content-Type: image/png\r");
			out.println("\r");
			BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir") + "/testFiles" + request));

			ImageIO.write(image, "png", outStream);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void handleHtml(PrintWriter out, String request) {
		StringBuilder response = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(System.getProperty("user.dir") + "/testFiles" + request));
			String inputfile;
			while ((inputfile = reader.readLine()) != null)
				response.append(inputfile);
			out.println("HTTP/1.1 200 OK\r");
			out.println("Content-Type: text/html\r");
			out.println("\r");
			out.println(response.toString());

			reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void handleFavicon(PrintWriter out, OutputStream outStream, String request) throws IOException {
		out.println("HTTP/1.1 200 OK \r");
		out.println("Content-Type: image/vnd.microsoft.icon \r");
		out.println("\r");
		
		List<BufferedImage> images = ICODecoder.read(new File(System.getProperty("user.dir") + "/testFiles" + request));
        ICOEncoder.write(images.get(0), outStream);
	}
	
	
	private static int getPort() {
		return System.getenv("PORT") != null? Integer.parseInt(System.getenv("PORT")) : 4567;
	}

}
