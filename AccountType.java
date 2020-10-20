package employeeinfo.Employee;

/*public enum AccountType {
	CHECKING, 
	SAVINGS, 
	RETIREMENT;
}*/

public class AccountType
{
	public String type;
	
	public AccountType(String t)
	{
		this.type=t;
	}
	
	@Override
	public String toString()
	{
		return type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
