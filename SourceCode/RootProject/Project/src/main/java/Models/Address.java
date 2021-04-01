/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Tyler
 */
public class Address {
    
    private String street;
    private String zipcode;
    private String state;
    private String country;
    private String city;
    
    public Address(String street, String zipcode, String state, String country, String city)
    {
        this.street = street;
        this.zipcode = zipcode;
        this.state = state;
        this.country = country;
        this.city = city;
    }
    
    public String getStreet() { return street; }
    public String getZipcode() { return zipcode; }
    public String getState() { return state; }
    public String getcountry() { return country; }
    public String getCity() { return city; }
    
}
