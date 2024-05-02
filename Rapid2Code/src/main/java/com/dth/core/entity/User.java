/*
 * The MIT License
 *
 * Copyright (c) 2024, DATATHUNDER Inc, Rapid2Code
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WßITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.dth.core.entity;

import java.util.Date;

public class User extends Auditable
{

	private long id;

	private String userName;
	private String userType;

	// free with adv
	// payment by credit card
	// organization paid for
	// etc.
	private String accountType;
	private String paymentRef;
	private String firstName;
	private String lastName;
	private String preferEmailContact;
	private String textNumber;
	private String email;
	private String alterEmail;
	private String password;
	private String secretQuestion1;
	private String secretAnswer1;
	private String secretQuestion2;
	private String secretAnswer2;
	private Date expires;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserType()
	{
		return userType;
	}

	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	public String getPaymentRef()
	{
		return paymentRef;
	}

	public void setPaymentRef(String paymentRef)
	{
		this.paymentRef = paymentRef;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getPreferEmailContact()
	{
		return preferEmailContact;
	}

	public void setPreferEmailContact(String preferEmailContact)
	{
		this.preferEmailContact = preferEmailContact;
	}

	public String getTextNumber()
	{
		return textNumber;
	}

	public void setTextNumber(String textNumber)
	{
		this.textNumber = textNumber;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getAlterEmail()
	{
		return alterEmail;
	}

	public void setAlterEmail(String alterEmail)
	{
		this.alterEmail = alterEmail;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getSecretQuestion1()
	{
		return secretQuestion1;
	}

	public void setSecretQuestion1(String secretQuestion1)
	{
		this.secretQuestion1 = secretQuestion1;
	}

	public String getSecretAnswer1()
	{
		return secretAnswer1;
	}

	public void setSecretAnswer1(String secretAnswer1)
	{
		this.secretAnswer1 = secretAnswer1;
	}

	public String getSecretQuestion2()
	{
		return secretQuestion2;
	}

	public void setSecretQuestion2(String secretQuestion2)
	{
		this.secretQuestion2 = secretQuestion2;
	}

	public String getSecretAnswer2()
	{
		return secretAnswer2;
	}

	public void setSecretAnswer2(String secretAnswer2)
	{
		this.secretAnswer2 = secretAnswer2;
	}

	public Date getExpires()
	{
		return expires;
	}

	public void setExpires(Date expires)
	{
		this.expires = expires;
	}

	public boolean isGeneric()
	{
		boolean rtnValue = false;
		//rtnValue = GenericUser.isGeneric(this);
		return rtnValue;
	}

}
