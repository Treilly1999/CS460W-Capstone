/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Tyler
 */
public class Bill_Model {
    
    private int billTotal;    
    
    private Map<String, Integer> billBreakdown;
    
    public Bill_Model(Map<String, Integer> billBreakdown)
    {
        this.billBreakdown = billBreakdown;
        
        for(int i = 0; i < billBreakdown.size(); i++)
        {
            billTotal += billBreakdown.get(i);
        }
    }
    
    public void addBill(String bill, int amount)
    {
        billBreakdown.put(bill, amount);
    }
    public Map<String, Integer> getBillBreakdown() { return billBreakdown; }
    
    public int getBillTotal() { return billTotal; }
    
}
