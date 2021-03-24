
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
