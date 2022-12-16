package entities;
import java.io.Serializable;
@SuppressWarnings("serial")
public class Customer implements Serializable {

		protected String id;
		protected String firstName;
		protected String lastName;
		protected String phoneNumber;
		protected String emailAddress;
		protected String creditCardNumber;

		public Customer() {

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

		public Customer(String id, String firstName, String lastName, String phoneNumber, String emailAddress,String creditCardNumber) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.id = id;
			this.phoneNumber = phoneNumber;
			this.emailAddress = emailAddress;
			this.creditCardNumber = creditCardNumber;
		}

		
		 public String toString() { return
		 id+" "+firstName+" "+lastName+" "+phoneNumber+" "+emailAddress+" "
		 +creditCardNumber; }
		 

	}
