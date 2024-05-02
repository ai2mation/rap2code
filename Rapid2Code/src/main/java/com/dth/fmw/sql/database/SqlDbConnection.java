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

package com.dth.fmw.sql.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDbConnection
{
	private String userId;
	private int countOfQueries = 0;
	private String password;
	public static int maxUserConnections = 1;
	public static long maxTime = 6000000;
	public static int minUserConnections = 1;
	public static int maxTotalConnections = 25;
	private long time = System.currentTimeMillis();
	private String database;
	private Connection connection;
	private boolean primary = false;
	private boolean inUse = false;
	private boolean readOnly = true;
	private boolean timedOut = false;
	
	private int connectionId;
	private String connectionName;

	public SqlDbConnection(int aConnectionId,String aConnectionName,String aUserID, String aPassword, String aDatabase, 
			Connection aConnection,	boolean aReadOnly) throws SQLException
	{
		connectionId = aConnectionId;
		connectionName = aConnectionName;
		userId = aUserID;
		password = aPassword;
		database = aDatabase;
		connection = aConnection;
		readOnly = aReadOnly;
	}
	
	public int getConnectionId()
	{
		return connectionId;
	}
	
	public String getConnectionName()
	{
	  return connectionName;	
	}

	public void close() throws SQLException
	{
		Connection mOldConnection = connection;
		connection = DriverManager.getConnection(database, userId, password);
		mOldConnection.close();
		time = System.currentTimeMillis();
	}

	public Connection getConnection()
	{
		time = System.currentTimeMillis();
		return connection;
	}

	public String getDatabase()
	{
		return database;
	}

	public long getLastAccess()
	{
		return time;
	}

	public String getPassword()
	{
		return password;
	}

	public int getQueryCount()
	{
		return countOfQueries;
	}

	public Statement getStatement() throws SQLException
	{
		time = System.currentTimeMillis();
		return connection.createStatement();
	}

	public String getUserID()
	{
		return userId;
	}

	public boolean hasExpired()
	{
		boolean mRtnValue = false;
		long mTime = System.currentTimeMillis() - time;
		if (mTime > maxTime)
			mRtnValue = true;
		return mRtnValue;
	}

	public void increaseQueryCount()
	{
		countOfQueries++;
	}

	public boolean isInUse()
	{
		return inUse;
	}

	public boolean isPrimary()
	{
		return primary;
	}

	public boolean isReadOnly()
	{
		return readOnly;
	}

	public boolean isTimedOut()
	{
		return timedOut;
	}

	public void setConnection(Connection aConnection) throws SQLException
	{
		connection = aConnection;
		time = System.currentTimeMillis();
	}

	public void setInUse(boolean aValue)
	{
		inUse = aValue;
	}

	public void setLastAccess()
	{
		time = System.currentTimeMillis();
	}

	public void setPrimary()
	{
		primary = true;
	}

	public void setTimedOut(boolean aValue)
	{
		timedOut = aValue;
	}
	
	public boolean executeStmt(String aSql) throws SQLException
	{
		Statement mStatement = getStatement();
		boolean mRtnValue = true;
		try
		{
			mStatement.execute(aSql);
		}
		catch(SQLException aException)
		{
			mRtnValue = false;
		}
		return  mRtnValue;
	}
}
