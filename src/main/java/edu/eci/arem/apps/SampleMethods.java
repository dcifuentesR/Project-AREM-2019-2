package edu.eci.arem.apps;

import edu.eci.arem.annotations.Web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2116387
 */
public class SampleMethods {
    
    @Web(value="hola")
    public static String greet(){
        return "hola como estas?";
    }
    
    @Web(value="product")
    public static String multiply(String num1,String num2) {
    	return "El producto es: "+Integer.toString(Integer.parseInt(num1)*Integer.parseInt(num2));
    }
    
}
