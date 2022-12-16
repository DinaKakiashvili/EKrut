package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Subscriber extends Customer implements Serializable {

	private String subscriberNumber;

	public Subscriber() {
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getSubscriberNumber() {
		return subscriberNumber;
	}

	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}

	public Subscriber(String id, String firstName, String lastName, String phoneNumber, String emailAddress,String creditCardNumber, String SubscriberNumber) {
		super(id,firstName,lastName,phoneNumber,emailAddress,creditCardNumber);
		this.subscriberNumber = SubscriberNumber;
	}

	
	 public String toString() { return
	 id+" "+firstName+" "+lastName+" "+phoneNumber+" "+emailAddress+" "
	 +creditCardNumber+" "+subscriberNumber; }
	 

}