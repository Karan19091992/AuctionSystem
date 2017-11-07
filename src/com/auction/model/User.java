/**
 * 
 */
package com.auction.model;

/**
 * @author Karansinh
 *
 */
public class User {

	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private int age;
	private String userName;
	private String password;

	public User(String firstname, String lastname, String email, int age, String username, String password) {

		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.age = age;
		this.userName = username;
		this.password = password;
	}

	public User(int userid, String firstname, String lastname, String email, int age, String username,
			String password) {

		this.userId = userid;
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.age = age;
		this.userName = username;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userid) {
		this.userId = userid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userid=" + userId + ", firstname=" + firstName + ", lastname=" + lastName + ", email=" + email
				+ ", age=" + age + ", username=" + userName + ", password=" + password + "]";
	}

}
