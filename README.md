Programming Assignment-4 Part-B (Inheritance and Polymorphism)
(Total Marks 60)


Q#1)
A university department consists of professors and secretaries. Each professor and each secretary has a name, a salary, and a hire date. Use inheritance and polymorphism to create an application that represents the department and its professors and secretaries as objects, and provides a test class that creates 3 professors and 2 secretaries, and then outputs the combined total of all of their salaries. (10 Marks)

Start by creating classes

Professor
Secretary
DeptEmployee

having the following relationship:

Place instance fields and corresponding accessor/mutator methods in DeptEmployee to represent name and hire date (do not create accessors or mutators for salary). Do not put these fields in either the Professor or Secretary class. Also place in the Professor class an int field numberOfPublications, with corresponding accessor and mutator methods. Place in the Secretary class a double field overtimeHours, also with corresponding accessor and mutator methods.

Place a computeSalary method in DeptEmployee which simply returns the value stored in salary. Override the computeSalary method in Secretary so that the return value is the sum of the value of salary plus 12 times the number of overtime hours.
 
Then in the main method of a class named Main, create three instances of  Professor and two instances of Secretary (you can invent the values to pass into the constructor). In each of the Professor instances, set the value of numPublications to 10. And in each of the Secretary instances, set overtimeHours to 200. Finally, create an array of department employees:

		DeptEmployee[] department = new DeptEmployee[5]

and then populate the array with the Professor and Secretary instances you have just created. Then ask the user if he wishes to see the sum of all salaries in the department. If the user responds "Y", then loop through the department array and polymorphically read, and sum, all salaries, and output the result to the console.

Q#2)
Use the code in the package closedcurve in the assignment code folder as a starting point. (10 Marks)

Add a new class Rectangle having instance variables width and length of type double to the good package. Rectangle should inherit from ClosedCurve. Implement the computeArea method, as appropriate. Modify the Test class so that the area of Rectangle instance (Create one instance of Rectangle in main) is also computed. 

For your test, use these dimensions for your closed curves:

	triangle: sides 4, 5, 6
	square: side 3
	rectangle: width 3, length 7
	circle: radius 3

Output should look like this:

The area of this Triangle is 9.921567416492215
The area of this Square is 9.0
The area of this Rectangle is 21.0
The area of this Circle is 28.274333882308138


Q#3)
In this lab, we introduce some refinements to the package employeeinfo.Employee that will lead  to an application of polymorphism. The starting point for this lab is therefore use code provided in employeeinfo.Employee. (40 Marks)

We now add the following 3 requirements for managing Accounts:

•	when balance is read for checking account, a $5 monthly service charge
will be subtracted

•	when a withdrawal is made from a retirement account, a 2% penalty
is applied to the balance

•	when balance is read for savings, a 0.25% monthly interest rate is applied

Now, the algorithms for performing withdrawals, deposits, and reading balance differ (in some cases) among the different types of accounts. So you will implement these new requirements by introducing three subclasses of Account: CheckingAccount, SavingsAccount, and  RetirementAccount.

With these new subclasses, you will also introduce some improvements in the code. Here are areas that need improvement together with suggested improvements:

(1)	Each of the new subclasses will keep track of its own account type; however, you will not need to store this value as an instance variable any longer. Just provide the relevant value in the getAcctType() method of each subclass. For example, in CheckingAccount:
public AccountType getAcctType() {
	return Account.CHECKING;
}
As a result, you will need to modify the constructors in Account – they will no longer have an acctType argument.

(2)	In Q#3-1, Accounts are listed separately in the Employee class; these should instead be stored in some kind of a list or Array. This will allow you to add new types of Accounts in the (hypothetical) future without having to add new instance variables to Employee (recall the Open-Closed Principle).

//lab code…
public class Employee {
	private Account savingsAcct;
	private Account checkingAcct;
	private Account retirementAcct;
...
}

			//Replace with

public class Employee {
	private AraryList<Accounts> accounts;
      //in constructor you need to do accounts=new AccountList();
...
}
               
Hint:  Example of defining a list in Java is:

import java.util.ArrayList;

public class Test 
{
 public ArrayList<Integer> al=new ArrayList();
}

(3)	Since we do not wish to store different account types in separate instance variables (since you will be storing accounts in a list or array, as above), the createXXX methods need to be modified:
//lab code…
public void createNewSavings(double startBalance){
	savingsAcct = new Account(this,AccountType.SAVINGS,startBalance);
}
public void createNewChecking(double startBalance){
	checkingAcct = new Account(this,AccountType.CHECKING,startBalance);
}
public void createNewRetirement(double startBalance){
retirementAcct = new Account(this,AccountType.RETIREMENT,startBalance);
}

Keep these methods, but change their implementation by adding the newly created Account objects to the AccountList. For example:

	public void createNewSavings(double startBalance) {
	Account acct = new SavingsAccount(this,startBalance);
	//accounts is the name of the AccountList variable
		accounts.add(acct);
// Here I defined it as List you can use an array too with fixed size
	}
(4)	The deposit method in Employee needs to be recoded to make use of the 
AccountList. 	
//lab code…
public void deposit(AccountType acctType, double amt){
		switch(acctType){
			case CHECKING:
				checkingAcct.makeDeposit(amt);
				break;
			case SAVINGS:
				savingsAcct.makeDeposit(amt);
				break;
			case RETIREMENT:
				retirementAcct.makeDeposit(amt);
				break;
			default:				
		}
	}

Improve this by changing the signature of deposit to 
deposit(int accountIndex, double amt)
The accountIndex represents a selection that the User of our application makes in choosing one of the accounts in which to make a deposit; the selected accountIndex will correspond to the Account instance stored in the AccountList inside an Employee object.

The deposit can then be accomplished by these lines:
		//For array type it will be accounts[acctIndex]
		Account selected = accounts.get(acctIndex);
		selected.makeDeposit(amt);

(Notice the nice use of polymorphism here.)

(5)	Similar changes should be made to the Employee withdraw method. If the withdrawal cannot be made (because of insufficient funds), a message should appear in the console indicating this fact.

(6)	Implement the new rules for reading balances and making withdrawals (listed at the top of this lab) in the following way. Provide the standard behavior for these functions (reading balance just returns the balance; making withdrawal just deducts the amount from the balance) in the Account superclass. But in cases where one of the rules above requires further processing, implement the additional processing by overriding the Account method in the relevant subclass. 

For example, here is the implementation of  getBalance in SavingsAccount:
	public double getBalance() {
		double baseBalance = super.getBalance();
		double interest = (0.25/100)*baseBalance;
		return baseBalance + interest;
	}
(One more improvement: 0.25 should be represented as a constant in SavingsAccount)

(7)	The getFormattedAccountInfo method of Employee was implemented by checking whether each type of Account was null, and if not, reading the account information from it, and accumulating the results into an output String.

Now, all this can be accomplished by looping through the AccountList and gathering the same information.

(8)	In the main method, we now want to make this a more interesting console application. 

When the application starts, the User should see:

A. See a report of all accounts.
B. Make a deposit.
C. Make a withdrawal.
Make a selection (A/B/C):

If A is selected, then output the formatted report that you generated from previous code:

If B is selected, the User should then interact with the system as in the following:

A. See a report of all accounts.
B. Make a deposit.
C. Make a withdrawal.
Make a selection (A/B/C): B

0. Jim Daley
1. Bob Reuben
2. Susan Randolph
Select an employee: (type a number) 2

0. checking
1. savings
2. retirement
Select an account: (type a number) 1

Deposit amount: 300.00

		After the deposit is made, the User should see:

			$300.0 has been deposited in the 
savings account of Susan Randolph

The indexed list of Employee names, as displayed above, should be obtained by reading the array of Employees (created in the main method, as in Lab code) However, the main method should not have access to the actual AccountList stored in Employee (violation of encapsulation).  Is there a way we can protect this data?

