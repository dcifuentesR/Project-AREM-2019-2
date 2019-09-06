package edu.eci.arem.apps;

import edu.eci.arem.server.Web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2116387
 */
public class mean {
    
    @Web(value="hola")
    public static String calcMean(){
    	System.out.print("hola????");
        return "hola como estas?";
    }
    
    @Web(value="othermethod")
    public static String otherMethod(int num1,double num2) {
    	return Double.toString(num1*num2);
    }
    
}
