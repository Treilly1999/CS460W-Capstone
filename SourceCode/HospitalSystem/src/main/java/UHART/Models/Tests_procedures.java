/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UHART.Models;

/**
 *
 * @author Jakob
 */
public enum Tests_procedures {
	ECHOCARDIOGRAM("echocardiogram", 2000),
	CTSCAN("ct scan", 3275),
	STRESSTEST("stress test", 175),
	ANGIOGRAM("angiogram", 1500),
	LATERALNECKXRAY("laterl neck x-ray", 200),
	URINETEST("urine test", 100),
	BLOODTEST("blood test", 100),
	SURGERY("surgery", 800),
	REHABILITATION("rehabilitation", 500);
	
	private String string; int price;
	Tests_procedures(String s, int p){string = s; price = p;}
	public String toString() {return string;}
	public int getPrice() {return price;}
}
