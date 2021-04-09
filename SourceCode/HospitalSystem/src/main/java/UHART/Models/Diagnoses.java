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
public enum Diagnoses {
	CHESTPAIN("chest pain"),
	ACUTEUPPERRESPIRATORYINFECTION("acute upper respiratory infection"),
	URINARYTRACTINFECTION("urinary tract infection"),
	HEADACHE("headache"),
	ABDOMINALPAIN("abdominal pain"),
	SYNCOPEANDCOLLAPSE("syncope and collapse"),
	NONINFECTIVEGASTROENTERITISANDCOLITIS("noninfective gastroenteritis and colitis"),
	DIZZINESSANDGIDDINESS("dizziness and giddiness"),
	LOWERBACKPAIN("lower back pain"),
	INJURYOFHEAD("injury of head");

	private String string;
	Diagnoses(String s){string = s;}
	public String toString() { return string;}
}
