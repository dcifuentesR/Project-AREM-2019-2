/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arem.server;

/**
 *
 * @author 2116387
 */
public interface Handler {
    
    public String process();
    
    public String process(Object[] parameters);
    
}
