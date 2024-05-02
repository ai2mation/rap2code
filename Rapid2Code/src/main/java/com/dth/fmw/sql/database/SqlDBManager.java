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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import com.dth.fmw.sql.query.SqlResults;
import com.dth.fmw.sql.query.SqlStatus;

public abstract class SqlDBManager
{
	private static SqlDbConnections sqlDbConnections = new SqlDbConnections();

	private static final AtomicInteger nextId = new AtomicInteger(0);

	private static final ThreadLocal<Integer> connectionId = new ThreadLocal<Integer>()
	{
		@Override
		protected Integer initialValue()
		{
			return nextId.getAndIncrement();
		}
	};

	public static int getConnectionId()
	{
		return connectionId.get();
	}

	public SqlDBManager()
	{
		super();
	}

	public SqlDbConnection buildConnection(String aConnectionName, String aUserID, String aPassword, boolean aReadOnly)
			throws SQLException
	{
		return sqlDbConnections.buildConnection(getConnectionId(), aConnectionName, aUserID, aPassword,
				getDatabaseURL(), aReadOnly);
	}

	public SqlDbConnection getConnection(String aUserID) throws SQLException
	{
		return sqlDbConnections.getConnection(aUserID, getDatabaseURL());
	}

	public static Connection getConnection(String aUserID, String aDatabaseURL) throws SQLException
	{
		Connection mRtnValue = null;
		SqlDbConnection mSqlDbConnection = sqlDbConnections.getConnection(aUserID, aDatabaseURL);
		if (mSqlDbConnection != null)
			mRtnValue = mSqlDbConnection.getConnection();
		return mRtnValue;
	}

	public abstract String getDatabaseURL();

	public static long getLastAccess(Connection aConnection) throws SQLException
	{
		long mRtnValue = 0;
		SqlDbConnection mConnection = sqlDbConnections.getConnection(aConnection);
		if (mConnection != null)
			mRtnValue = mConnection.getLastAccess();
		return mRtnValue;
	}

	public SqlDbConnection getSqlConnection(String aUserID) throws SQLException
	{
		return sqlDbConnections.getSqlDbConnection(aUserID, getDatabaseURL());
	}

	public static SqlDbConnection getSqlConnection(String aUserID, String aDatabaseURL)
	{
		return sqlDbConnections.getSqlDbConnection(aUserID, aDatabaseURL);
	}

	public static int getQueryCount(Connection aConnection) throws SQLException
	{
		int mRtnValue = 0;
		SqlDbConnection mSqlDbConnection = sqlDbConnections.getConnection(aConnection);
		if (mSqlDbConnection != null)
			mRtnValue = mSqlDbConnection.getQueryCount();
		return mRtnValue;
	}

	public static Connection getTheConnection(String aUserID, String aDatabaseURL) throws SQLException
	{
		Connection mRtnValue = null;
		SqlDbConnection mSqlDbConnection = sqlDbConnections.getSqlDbConnection(aUserID, aDatabaseURL);
		if (mSqlDbConnection != null)
			mRtnValue = mSqlDbConnection.getConnection();
		return mRtnValue;
	}

	public static void increaseQueryCount(Connection aConnection) throws SQLException
	{
		SqlDbConnection mSqlDbConnection = sqlDbConnections.getConnection(aConnection);
		if (mSqlDbConnection != null)
			mSqlDbConnection.increaseQueryCount();
	}

	public static boolean isInUse(Connection aConnection) throws SQLException
	{
		boolean mRtnValue = true;
		SqlDbConnection mSqlDbConneciton = sqlDbConnections.getConnection(aConnection);
		if (mSqlDbConneciton != null)
			mRtnValue = mSqlDbConneciton.isInUse();
		return mRtnValue;
	}

	public static boolean isTimedOut(Connection aConnection) throws SQLException
	{
		boolean mRtnValue = true;
		SqlDbConnection mSqlDbConneciton = sqlDbConnections.getConnection(aConnection);
		if (mSqlDbConneciton != null)
			mRtnValue = mSqlDbConneciton.isTimedOut();
		return mRtnValue;
	}

	public static boolean removeConnection(SqlDbConnection aConnection)
	{
		return sqlDbConnections.removeConnection(aConnection);
	}

	public static boolean removeConnection(String aUserID, String aDatabaseURL) throws SQLException
	{
		return sqlDbConnections.removeConnection(aUserID, aDatabaseURL);
	}

	public SqlResults runQuery(SqlStatus aStatus, SqlResults aResults, String aQuery, String aUserID)
			throws java.sql.SQLException
	{
		SqlDbConnection mConnection = getConnection(aUserID);
		// boolean mTerminating = false;
		ResultSet mSQLResultSet = null;
		if (!aStatus.isTerminating())
		{
			try
			{
				aStatus.setSqlDbConnection(mConnection);
				if (!aStatus.isTerminating())
				{
					mConnection.setInUse(true);
					aResults.setStatement(mConnection.getStatement());
					mSQLResultSet = aResults.getStatement().executeQuery(aQuery);
					mConnection.increaseQueryCount();
					mConnection.setInUse(false);
					aResults.insertResults(mSQLResultSet);
				}
			} catch (SQLException aSQLException)
			{
				if (!aSQLException.getSQLState().equals("08S01"))
				{
					throw aSQLException;
				}
			}
			aStatus.setSqlDbConnection(null);
		}
		return aResults;
	}

	public static void setInUse(boolean aValue, Connection aConnection) throws SQLException
	{
		SqlDbConnection mConnection = sqlDbConnections.getConnection(aConnection);
		if (mConnection != null)
			mConnection.setInUse(aValue);
	}

	public static void setLastAccess(Connection aConnection) throws SQLException
	{
		SqlDbConnection mSqlDbConnection = sqlDbConnections.getConnection(aConnection);
		if (mSqlDbConnection != null)
			mSqlDbConnection.setLastAccess();
	}

	public static void setTimedOut(boolean aValue, Connection aConnection) throws SQLException
	{
		SqlDbConnection mSqlDbConnection = sqlDbConnections.getConnection(aConnection);
		if (mSqlDbConnection != null)
			mSqlDbConnection.setTimedOut(aValue);
	}
}
