
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
