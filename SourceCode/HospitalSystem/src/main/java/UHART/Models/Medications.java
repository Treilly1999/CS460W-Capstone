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
public enum Medications {
	IBUPROFIN("ibuprofin"),
	ACETAMINOPHEN("acetaminophen"),
	SIMETHICONE("simethicone"),
	GANCICLOVIR("ganciclovir"),
	VALDANCICLOVIR("valdanciclovir"),
	FOSCARNET("foscarnet"),
	ASPIRIN("aspirin"),
	ANTIBIOTIC("antibiotic"),
	ANTISEIZURE("antiseizure"),
	ANTACID("antacid"),
	SUGAR("sugar");

	private String string;
	Medications(String s){string = s;}
	public String toString() { return string;}
}
