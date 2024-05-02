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

public class SqlDbConnectionList
{
	private SqlDbConnectionNode oHead;
	private SqlDbConnectionNode oCurr;
	private SqlDbConnectionNode oTail;

	public SqlDbConnectionList()
	{
		super();
	}

	public void append(SqlDbConnection aConnection)
	{
		SqlDbConnectionNode mNode = new SqlDbConnectionNode(aConnection);
		mNode.setPrev(oTail);
		if (oHead == null)
		{
			oHead = mNode;
		}
		if (oTail != null)
		{
			oTail.setNext(mNode);
		}
		oTail = oCurr = mNode;
	}

	public int connectionCount()
	{
		int mRtnValue = 0;
		SqlDbConnectionNode mCurr = oHead;
		while (mCurr != null)
		{
			mRtnValue++;
			mCurr = mCurr.getNext();
		}
		return mRtnValue;
	}

	public int connectionCount(String aDatabaseURL)
	{
		int mRtnValue = 0;
		SqlDbConnectionNode mCurr = oHead;
		while (mCurr != null)
		{
			if (mCurr.getSqlDbConnection().getDatabase().equals(aDatabaseURL))
				mRtnValue++;
			mCurr = mCurr.getNext();
		}
		return mRtnValue;
	}

	public int connectionCount(String aUserID, String aDatabaseURL)
	{
		int mRtnValue = 0;
		SqlDbConnectionNode mCurr = oHead;
		while (mCurr != null)
		{
			if (mCurr.getSqlDbConnection().getUserID().equals(aUserID)
					&& mCurr.getSqlDbConnection().getDatabase().equals(aDatabaseURL))
				mRtnValue++;
			mCurr = mCurr.getNext();
		}
		return mRtnValue;
	}

	public SqlDbConnection getAvailableUser(String aUser, String aDatabaseURL)
	{
		SqlDbConnection mRtnValue = null;
		SqlDbConnectionNode mNode = oHead;
		while (mNode != null)
		{
			SqlDbConnection mConnection = mNode.getSqlDbConnection();
			if (mConnection.getUserID().equals(aUser) && mConnection.getDatabase().equals(aDatabaseURL))
			{
				if (!mConnection.isInUse())
				{
					mRtnValue = mConnection;
					mNode = null;
				}
			}
			if (mNode != null)
			{
				mNode = mNode.getNext();
			}
		}
		return mRtnValue;
	}

	public SqlDbConnection getConnection(String aUserID, String aDatabaseURL)
	{
		SqlDbConnection mRtnValue = null;
		if (oCurr == null)
		{
			oCurr = oHead;
		}
		if (oHead != null)
		{
			SqlDbConnectionNode mStartingNode = oCurr;
			synchronized (this)
			{
				boolean mContinue = true;
				while (mContinue)
				{
					if (oCurr.getSqlDbConnection().getUserID().equals(aUserID)
							&& oCurr.getSqlDbConnection().getDatabase().equals(aDatabaseURL))
					{
						mRtnValue = oCurr.getSqlDbConnection();
						mContinue = false;
					}
					oCurr = oCurr.getNext();
					if (oCurr == null)
						oCurr = oHead;
					if (oCurr == mStartingNode)
						mContinue = false;
				}
			}
		}
		return mRtnValue;
	}

	public SqlDbConnection getConnection(Connection aConnection)
	{
		SqlDbConnection mRtnValue = null;
		SqlDbConnectionNode mNode = oHead;
		while (mNode != null)
		{
			SqlDbConnection mConnection = mNode.getSqlDbConnection();
			if (mConnection.getConnection() == aConnection)
			{
				mRtnValue = mConnection;
				mNode = null;
			}
			if (mNode != null)
			{
				mNode = mNode.getNext();
			}
		}
		return mRtnValue;
	}

	public SqlDbConnection getFirstUser(String aUser, String aDatabaseURL)
	{
		SqlDbConnection mRtnValue = null;
		SqlDbConnectionNode mNode = oHead;
		while (mNode != null)
		{
			SqlDbConnection mConnection = mNode.getSqlDbConnection();
			if (mConnection.getUserID().equals(aUser) && mConnection.getDatabase().equals(aDatabaseURL))
			{
				mRtnValue = mConnection;
				mNode = null;
			}
			if (mNode != null)
			{
				mNode = mNode.getNext();
			}
		}
		return mRtnValue;
	}

	public SqlDbConnection getNextExpired()
	{
		SqlDbConnection mRtnValue = null;
		if (oCurr == null)
		{
			oCurr = oHead;
		}
		if (oHead != null)
		{
			SqlDbConnectionNode mStartingNode = oCurr;
			synchronized (this)
			{
				boolean mContinue = true;
				while (mContinue)
				{
					if (oCurr.hasExpired())
					{
						mRtnValue = oCurr.getSqlDbConnection();
						mContinue = false;
					}
					oCurr = oCurr.getNext();
					if (oCurr == null)
						oCurr = oHead;
					if (oCurr == mStartingNode)
						mContinue = false;
				}
			}
		}
		return mRtnValue;
	}

	public SqlDbConnection getSqlDbConnection(String aUser, String aDatabaseURL)
	{
		SqlDbConnection mRtnValue = null;
		SqlDbConnectionNode mNode = oHead;
		while (mNode != null)
		{
			SqlDbConnection mConnection = mNode.getSqlDbConnection();
			if (mConnection.getUserID().equals(aUser) && mConnection.getDatabase().equals(aDatabaseURL))
			{
				mRtnValue = mConnection;
				mNode = null;
			}
			if (mNode != null)
			{
				mNode = mNode.getNext();
			}
		}
		return mRtnValue;
	}

	public void insert(SqlDbConnection aConnection)
	{
		if (oCurr == null)
		{
			append(aConnection);
		} else
		{
			SqlDbConnectionNode mNode = new SqlDbConnectionNode(aConnection);
			SqlDbConnectionNode mNext = oCurr.getNext();
			mNode.setNext(mNext);
			mNode.setPrev(oCurr);
			if (mNext != null)
			{
				mNext.setPrev(mNode);
			}
			oCurr.setNext(mNode);
		}
	}

	public void push(SqlDbConnection aConnection)
	{
		SqlDbConnectionNode mNode = new SqlDbConnectionNode(aConnection);
		mNode.setNext(oHead);
		if (oTail == null)
		{
			oTail = mNode;
		}
		if (oHead != null)
		{
			oHead.setPrev(mNode);
		}
		oCurr = oHead = mNode;
	}

	public SqlDbConnectionNode remove(SqlDbConnection aConnection)
	{
		SqlDbConnectionNode mRtnValue = null;
		SqlDbConnectionNode mNode = oHead;
		while (mNode != null)
		{
			SqlDbConnection mConnection = mNode.getSqlDbConnection();
			if (mConnection == aConnection)
			{
				mRtnValue = mNode;
				SqlDbConnectionNode mPrev = mNode.getPrev();
				SqlDbConnectionNode mNext = mNode.getNext();
				if (mPrev != null)
				{
					mPrev.setNext(mNext);
				}
				if (mNext != null)
				{
					mNext.setPrev(mPrev);
				}
				if (mNode == oHead)
				{
					oHead = mNext;
				}
				if (mNode == oTail)
				{
					oTail = mPrev;
				}
				if (oCurr == mNode)
				{
					oCurr = mNext;
				}
				mNode = null;
			}
		}
		return mRtnValue;
	}

	public SqlDbConnectionNode remove(SqlDbConnectionNode aConnection)
	{
		SqlDbConnectionNode mRtnValue = null;
		SqlDbConnection mConnection = aConnection.getSqlDbConnection();
		if (mConnection != null)
		{
			mRtnValue = remove(mConnection);
		}
		return mRtnValue;
	}
}
