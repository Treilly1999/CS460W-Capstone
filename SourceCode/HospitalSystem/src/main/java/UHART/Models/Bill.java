package UHART.Models;

import java.util.ArrayList;

public class Bill {
	private int price;
	private ArrayList<Tests_procedures> tests_procedures;
    private ArrayList<Medications> medications;
    
    public Bill()
    {
    	price = 0;
    }
    
    public void markPaid()
    {
    	price = 0;
    	tests_procedures.clear();
    	medications.clear();
    }
    
    public void addItem(Tests_procedures t)
    {
    	price = price + t.getPrice();
    	tests_procedures.add(t);
    }
    
    public void addItem(Medications m)
    {
    	price = price + m.getPrice();
    	medications.add(m);
    }
    
    public String toString()
    {
    	String bill = "Bill \n" + String.format("15%s", "Item") + String.format("%s", "Price") + "\n";
    	String temp;
    	for(int i=0; i<medications.size(); i++)
    	{
    		temp = String.format("15%s", medications.get(i).toString()) + String.format("%d", medications.get(i).getPrice());
    		bill += temp + "\n";
    	}
    	for(int j=0; j<tests_procedures.size(); j++)
    	{
    		temp = String.format("15%s", tests_procedures.get(j).toString()) + String.format("%d", tests_procedures.get(j).getPrice());
    		bill += temp + "\n";
    	}
    	bill += "Total cost: " + price;
    	return bill;
    }
    
    public int getPrice()
    {
    	return price;
    }
}