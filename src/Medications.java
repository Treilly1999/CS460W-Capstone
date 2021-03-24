
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
