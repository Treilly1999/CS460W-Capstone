
public abstract class StaffMember {
	int id, phoneNumber;
	String name, address;
	
	public void login() 
	{
		return; //put login stuff here
	}
	public void logout()
	{
		return; //put logout stuff here
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int i)
	{
		id = i;
	}
	
	public int getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public void setPhoneNumber(int i)
	{
		phoneNumber = i;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String s)
	{
		name = s;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String s)
	{
		address = s;
	}
}
