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
	IBUPROFIN("ibuprofin", 15),
	ACETAMINOPHEN("acetaminophen", 3),
	SIMETHICONE("simethicone", 5),
	GANCICLOVIR("ganciclovir", 1500),
	VALGANCICLOVIR("valganciclovir", 230),
	FOSCARNET("foscarnet", 410),
	ASPIRIN("aspirin", 10),
	ANTIBIOTIC("antibiotic", 30),
	ANTISEIZURE("antiseizure", 45),
	ANTACID("antacid", 15),
	SUGAR("sugar", 10);
	
	private String string; int price;
	Medications(String s, int p){string = s; price = p;}
	public String toString() {return string;}
	public int getPrice() {return price;}	
}
