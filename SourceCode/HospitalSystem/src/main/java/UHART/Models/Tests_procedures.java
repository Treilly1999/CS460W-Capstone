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
	ECHOCARDIOGRAM("echocardiogram"),
	CTSCAN("ct scan"),
	STRESSTEST("stress test"),
	ANGIOGRAM("angiogram"),
	LATERALNECKXRAY("laterl neck x-ray"),
	URINETEST("urine test"),
	BLOODTEST("blood test"),
	SURGERY("surgery"),
	REHABILITATION("rehabilitation");

	private String string;
	Tests_procedures(String s){string = s;}
	public String toString() { return string;}
}
