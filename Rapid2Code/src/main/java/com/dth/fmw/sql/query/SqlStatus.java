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

package com.dth.fmw.sql.query;

import java.sql.SQLException;

import com.dth.fmw.sql.database.SqlDbConnection;

public class SqlStatus implements Runnable
{
	protected String status = null;
	protected SqlDbConnection sqlDbConnection = null;
	protected int state;
	protected Exception exception = null;
	protected SqlResults sqlResults = null;
	public static final int STARTING_QUERY = 0000;
	public final static int GENERATING_QUERY = 1000;
	public final static int EXECUTING_QUERY = 2000;
	public final static int MERGE_RESULTS = 2500;
	public final static int UNKNOWN_STATE = 9999;
	public final static int TERMINATING_QUERY = 3000;
	public final static int TERMINATING_DATA_NOT_FOUND = 3500;
	public final static int PROCESSING_RESULTS = 4000;
	public final static int COMPLETED = 5000;
	public final static int ENDED_IN_ERROR = 6000;
	public final static int USER_TERMINATED = 7000;
	public final static int SYS_TERM_DATA_NOT_FOUND = 7500;

	static final String GENERATING_QUERY_STRING = "Generating Query";
	static final String EXECUTING_QUERY_STRING = "Executing Query";
	static final String MERGING_RESULTS_STRING = "Merging Query Results";
	static final String TERMINATING_QUERY_STRING = "Terminated per User Request";
	static final String TERMINATING_DATA_NOT_FOUND_STRING = "Required data not found in query, Terminating";
	static final String STARTING_QUERY_STRING = "STARTING QUERY";
	static final String PROCESSING_RESULTS_STRING = "Processing Query Results";
	static final String COMPLETED_STRING = "Completed";
	static final String ENDED_IN_ERROR_STRING = "Error Found";
	static final String USER_TERMINATED_STRING = "Terminated per User Request";
	static final String SYS_TERM_DATA_NOT_FOUND_STRING = "System terminated, required data not found in query.";
	static final String UNKNOWN_STATUS_STRING = "Current Status is undefined...";

	protected long startTime = System.currentTimeMillis();
	protected long endTime = System.currentTimeMillis();
	private SqlState top = null;
	private SqlState cur = null;
	private String lastMessage;

	public SqlStatus()
	{
		super();
		setStatus(UNKNOWN_STATE);
	}

	public SqlStatus(int aState)
	{
		super();
		setStatus(aState);
	}

	public SqlStatus(String aStatus, int aState)
	{
		super();
		status = aStatus;
		state = aState;
	}

	public void cancelReport() throws SQLException
	{
		synchronized (this)
		{
			if (state != TERMINATING_QUERY && state != USER_TERMINATED && state != COMPLETED
					&& state != ENDED_IN_ERROR && state != SYS_TERM_DATA_NOT_FOUND)
			{
				setStatus(TERMINATING_QUERY);
				Thread aThread = new Thread(this);
				aThread.start();
			}
		}
	}

	public void closeConnection()
	{
		try
		{
			if (sqlDbConnection != null)
			{
				sqlDbConnection.close();
				sqlDbConnection.setInUse(false);
			}
		} catch (SQLException aException)
		{
		}
	}

	public long getElapsedQueryTime()
	{
		return System.currentTimeMillis() - startTime;
	}

	public String getLastMessage()
	{
		return lastMessage;
	}

	public SqlDbConnection getSqlDbConnection()
	{
		return sqlDbConnection;
	}

	public long getQueryRunTime()
	{
		return endTime - startTime;
	}

	public SqlResults getSqlResults()
	{
		return sqlResults;
	}

	public int getState()
	{
		return state;
	}

	public String getStatus()
	{
		return status;
	}

	public SqlState getTop()
	{
		return top;
	}

	public boolean isCompleted()
	{
		boolean mRtnValue = false;
		if (state == COMPLETED && top == cur)
			mRtnValue = true;
		return mRtnValue;
	}

	public boolean isTerminating()
	{
		boolean mRtnValue = false;
		if (state == TERMINATING_QUERY || state == TERMINATING_DATA_NOT_FOUND)
			mRtnValue = true;
		return mRtnValue;
	}

	public void pop()
	{
		if (top != null && cur != top)
		{
			cur = cur.getPrev();
			cur.setNext(null);
		}
	}

	public void push()
	{
		if (top != null && cur != null)
		{
			if (cur.getState() != SqlStatus.GENERATING_QUERY)
			{
				SqlState mSqlState = new SqlState();
				cur.setNext(mSqlState);
				mSqlState.setPrev(cur);
				cur = mSqlState;
			}
		}
	}

	public void run()
	{
		closeConnection();
	}

	public void setSqlDbConnection(SqlDbConnection aConnection)
	{
		sqlDbConnection = aConnection;
	}

	public void setResults(SqlResults aResults)
	{
		sqlResults = aResults;
	}

	public boolean setStatus(int aState)
	{
		boolean mRtnValue = true;
		synchronized (this)
		{
			if (!isTerminating() || aState == USER_TERMINATED || aState == SYS_TERM_DATA_NOT_FOUND)
			{
				state = aState;
				status = "Unknown status!";
				switch (aState)
				{
				case STARTING_QUERY:
					status = STARTING_QUERY_STRING;
					break;
				case GENERATING_QUERY:
					status = GENERATING_QUERY_STRING;
					break;
				case EXECUTING_QUERY:
					status = EXECUTING_QUERY_STRING;
					break;
				case MERGE_RESULTS:
					status = MERGING_RESULTS_STRING;
					break;
				case TERMINATING_QUERY:
					status = TERMINATING_QUERY_STRING;
					break;
				case TERMINATING_DATA_NOT_FOUND:
					status = TERMINATING_DATA_NOT_FOUND_STRING;
					break;
				case PROCESSING_RESULTS:
					status = PROCESSING_RESULTS_STRING;
					break;
				case COMPLETED:
					status = COMPLETED_STRING;
					break;
				case ENDED_IN_ERROR:
					status = ENDED_IN_ERROR_STRING;
					break;
				case USER_TERMINATED:
					status = USER_TERMINATED_STRING;
					break;
				case SYS_TERM_DATA_NOT_FOUND:
					status = SYS_TERM_DATA_NOT_FOUND_STRING;
					break;
				case UNKNOWN_STATE:
					status = UNKNOWN_STATUS_STRING;
					break;
				default:
					status = UNKNOWN_STATUS_STRING;
					break;
				}
				if (top == null && aState != UNKNOWN_STATE)
				{
					top = new SqlState();
					cur = top;
				}
				if (top == cur && aState != UNKNOWN_STATE)
				{
					switch (aState)
					{
					case STARTING_QUERY:
						startTime = System.currentTimeMillis();
						break;
					case COMPLETED:
						endTime = System.currentTimeMillis();
						break;
					case ENDED_IN_ERROR:
						endTime = System.currentTimeMillis();
						break;
					case USER_TERMINATED:
						endTime = System.currentTimeMillis();
						break;
					case SYS_TERM_DATA_NOT_FOUND:
						endTime = System.currentTimeMillis();
						break;
					}
				}
				if (cur != null)
					cur.setStatus(aState);
			} else
			{
				mRtnValue = false;
			}
		}
		return mRtnValue;
	}

	public boolean setStatus(int aState, String aComments)
	{
		return setStatus(aState, aComments, false);
	}

	public boolean setStatus(int aState, String aComments, boolean aOverWrite)
	{
		if (setStatus(aState))
		{
			lastMessage = aComments;
			if (aOverWrite)
			{
				status = aComments;
			} else
			{
				status += ": " + aComments;
			}
			if (cur != null)
			{
				cur.setStatus(aState, aComments);
			}
		}
		return !isTerminating();
	}

	public void setStatus(Exception aException)
	{
		synchronized (this)
		{
			state = ENDED_IN_ERROR;
			status = ENDED_IN_ERROR_STRING + ": " + aException.getMessage();
			exception = aException;
			if (cur != null)
			{
				cur.setStatus(aException);
			}
		}
	}

	public void sysTerminateQuery()
	{
		synchronized (this)
		{
			setStatus(TERMINATING_DATA_NOT_FOUND);
		}
	}

	public void sysTerminateQuery(String aMessage)
	{
		synchronized (this)
		{
			setStatus(TERMINATING_DATA_NOT_FOUND, aMessage);
		}
	}

	public void terminateQuery()
	{
		synchronized (this)
		{
			setStatus(TERMINATING_QUERY);
		}
	}
}
