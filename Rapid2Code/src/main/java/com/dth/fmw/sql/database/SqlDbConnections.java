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

public class SqlDbConnections
{
	private SqlDbConnectionList oConnections = new SqlDbConnectionList();

	public SqlDbConnections()
	{
		super();
	}

	public SqlDbConnection buildConnection(int aConnectionId, String aConnectionName, String aUserID, String aPassword,
			String aDatabaseURL, boolean aReadOnly) throws SQLException
	{
		SqlDbConnection mRtnValue = null;
		try
		{
			SqlDbConnection mSqlConnection = oConnections.getConnection(aUserID, aDatabaseURL);
			if (mSqlConnection != null)
			{
				mRtnValue = mSqlConnection;
			} else
			{
				Connection mConnection = DriverManager.getConnection(aDatabaseURL, aUserID, aPassword);
				if (mConnection == null)
				{
					throw new RuntimeException("Unable to establish connection for " + aUserID);
				} else
				{
					mConnection.setReadOnly(aReadOnly);
					mRtnValue = new SqlDbConnection(aConnectionId, aConnectionName, aUserID, aPassword, aDatabaseURL,
							mConnection, aReadOnly);
					mRtnValue.setPrimary();
					oConnections.append(mRtnValue);
					int mMinConnections = SqlDbConnection.minUserConnections;
					int mMaxConnections = SqlDbConnection.maxTotalConnections;
					int mCurConnections = oConnections.connectionCount();
					int mCount = mMinConnections - 1;
					if (mMinConnections + mCurConnections >= mMaxConnections)
					{
						mCount = mMaxConnections - mCurConnections;
					}
					for (int Lp0 = 0; Lp0 < mCount; Lp0++)
					{
						mConnection = DriverManager.getConnection(aDatabaseURL, aUserID, aPassword);
						if (mConnection == null)
						{
							Lp0 = mCount;
						} else
						{
							mConnection.setReadOnly(aReadOnly);
							mSqlConnection = new SqlDbConnection(aConnectionId, aConnectionName, aUserID, aPassword,
									aDatabaseURL, mConnection, aReadOnly);
							oConnections.append(mSqlConnection);
							mCurConnections++;
						}
					}
				}
			}
		} catch (SQLException aException)
		{
			throw aException;
		}
		return mRtnValue;
	}

	public SqlDbConnection getConnection(String aUserID, String aDatabaseURL) throws SQLException
	{
		SqlDbConnection mRtnValue = oConnections.getAvailableUser(aUserID, aDatabaseURL);
		if (mRtnValue == null)
		{
			synchronized (this)
			{
				SqlDbConnection mPattern = oConnections.getConnection(aUserID, aDatabaseURL);
				int mCurrConnectionCount = oConnections.connectionCount();
				int mUserConnectionCount = oConnections.connectionCount(aUserID, aDatabaseURL);
				if (mCurrConnectionCount < SqlDbConnection.maxTotalConnections)
				{
					if (mUserConnectionCount < SqlDbConnection.maxUserConnections)
					{
						String mPassword = mPattern.getPassword();
						Connection mConnection = DriverManager.getConnection(aDatabaseURL, aUserID, mPassword);
						mConnection.setReadOnly(mPattern.isReadOnly());
						mRtnValue = new SqlDbConnection(1, "", aUserID, mPassword, aDatabaseURL, mConnection,
								mPattern.isReadOnly());

						oConnections.insert(mRtnValue);
					}
				} else
				{
					mRtnValue = oConnections.getNextExpired();
					synchronized (mRtnValue)
					{
						String mPassword = mPattern.getPassword();
						Connection mConnection = DriverManager.getConnection(aDatabaseURL, aUserID, mPassword);
						mConnection.setReadOnly(mPattern.isReadOnly());
						mRtnValue.setConnection(mConnection);
					}
				}
			}
		}
		while (mRtnValue == null)
		{
			try
			{
				wait(100);
			} catch (InterruptedException aException)
			{
			}
			mRtnValue = oConnections.getAvailableUser(aUserID, aDatabaseURL);
		}
		Connection mConnection = mRtnValue.getConnection();
		boolean mIsClosed = false;
		try
		{
			if (!mConnection.isClosed())
			{
				// Test to insure connection is really alive
				Statement mStatement = mConnection.createStatement();
				mStatement.close();
			} else
			{
				mIsClosed = true;
			}
		} catch (SQLException aException)
		{
			mIsClosed = true;
		}
		if (mIsClosed)
		{
			mConnection = DriverManager.getConnection(mRtnValue.getDatabase(), mRtnValue.getUserID(),
					mRtnValue.getPassword());
			mConnection.setReadOnly(mRtnValue.isReadOnly());
			mRtnValue.setConnection(mConnection);
		}
		return mRtnValue;
	}

	public SqlDbConnection getConnection(Connection aConnection) throws SQLException
	{
		SqlDbConnection mRtnValue = oConnections.getConnection(aConnection);
		return mRtnValue;
	}

	public SqlDbConnection getFirstConnection(String aUserID, String aDatabaseURL) throws SQLException
	{
		SqlDbConnection mRtnValue = oConnections.getFirstUser(aUserID, aDatabaseURL);
		return mRtnValue;
	}

	public SqlDbConnection getSqlDbConnection(String aUserID, String aDatabaseURL)
	{
		SqlDbConnection mRtnValue = oConnections.getSqlDbConnection(aUserID, aDatabaseURL);
		return mRtnValue;
	}

	public boolean removeConnection(SqlDbConnection aConnection)
	{
		boolean mRtnValue = false;
		if (aConnection != null)
		{
			oConnections.remove(aConnection);
		}
		return mRtnValue;
	}

	public boolean removeConnection(String aUserID, String aDatabaseURL)
	{
		return removeConnection(oConnections.getConnection(aUserID, aDatabaseURL));
	}
}
