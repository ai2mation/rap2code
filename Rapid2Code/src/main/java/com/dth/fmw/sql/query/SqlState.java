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

public class SqlState
{
	protected String status = null;
	protected int state;
	protected SqlState next = null;
	protected SqlState prev = null;
	protected long startTime = System.currentTimeMillis();
	protected long endTime = System.currentTimeMillis();
	protected Exception exception = null;

	public SqlState()
	{
		super();
		setStatus(SqlStatus.UNKNOWN_STATE);
	}

	public SqlState(int aState)
	{
		super();
		setStatus(aState);
	}

	public SqlState(String aStatus, int aState)
	{
		super();
		status = aStatus;
		state = aState;
	}

	public void cancelReport() throws SQLException
	{
		if (state != SqlStatus.COMPLETED)
			setStatus(SqlStatus.TERMINATING_QUERY);
	}

	public long getElapsedQueryTime()
	{
		return System.currentTimeMillis() - startTime;
	}

	public SqlState getNext()
	{
		return next;
	}

	public SqlState getPrev()
	{
		return prev;
	}

	public long getQueryRunTime()
	{
		return endTime - startTime;
	}

	public int getState()
	{
		return state;
	}

	public String getStatus()
	{
		return status;
	}

	public boolean isCompleted()
	{
		boolean mRtnValue = false;
		if (state == SqlStatus.COMPLETED)
			mRtnValue = true;
		return mRtnValue;
	}

	public boolean isTerminating()
	{
		boolean mRtnValue = false;
		if (state == SqlStatus.TERMINATING_QUERY)
			mRtnValue = true;
		return mRtnValue;
	}

	public void setNext(SqlState aNext)
	{
		next = aNext;
	}

	public void setPrev(SqlState aPrev)
	{
		prev = aPrev;
	}

	public boolean setStatus(int aState)
	{
		boolean mRtnValue = true;
		synchronized (this)
		{
			if (!isTerminating() || aState == SqlStatus.USER_TERMINATED || aState == SqlStatus.SYS_TERM_DATA_NOT_FOUND)
			{
				state = aState;
				status = "Unknown status!";
				switch (aState)
				{
				case SqlStatus.STARTING_QUERY:
					status = SqlStatus.STARTING_QUERY_STRING;
					break;
				case SqlStatus.GENERATING_QUERY:
					status = SqlStatus.GENERATING_QUERY_STRING;
					break;
				case SqlStatus.EXECUTING_QUERY:
					status = SqlStatus.EXECUTING_QUERY_STRING;
					break;
				case SqlStatus.MERGE_RESULTS:
					status = SqlStatus.MERGING_RESULTS_STRING;
					break;
				case SqlStatus.TERMINATING_QUERY:
					status = SqlStatus.TERMINATING_QUERY_STRING;
					break;
				case SqlStatus.TERMINATING_DATA_NOT_FOUND:
					status = SqlStatus.TERMINATING_DATA_NOT_FOUND_STRING;
					break;
				case SqlStatus.PROCESSING_RESULTS:
					status = SqlStatus.PROCESSING_RESULTS_STRING;
					break;
				case SqlStatus.COMPLETED:
					status = SqlStatus.COMPLETED_STRING;
					break;
				case SqlStatus.ENDED_IN_ERROR:
					status = SqlStatus.ENDED_IN_ERROR_STRING;
					break;
				case SqlStatus.USER_TERMINATED:
					status = SqlStatus.USER_TERMINATED_STRING;
					break;
				case SqlStatus.SYS_TERM_DATA_NOT_FOUND:
					status = SqlStatus.SYS_TERM_DATA_NOT_FOUND_STRING;
					break;
				case SqlStatus.UNKNOWN_STATE:
					status = SqlStatus.UNKNOWN_STATUS_STRING;
					break;
				default:
					status = SqlStatus.UNKNOWN_STATUS_STRING;
					break;
				}
				switch (aState)
				{
				case SqlStatus.GENERATING_QUERY:
					startTime = System.currentTimeMillis();
					break;
				case SqlStatus.COMPLETED:
					endTime = System.currentTimeMillis();
					break;
				case SqlStatus.ENDED_IN_ERROR:
					endTime = System.currentTimeMillis();
					break;
				case SqlStatus.USER_TERMINATED:
					endTime = System.currentTimeMillis();
					break;
				case SqlStatus.SYS_TERM_DATA_NOT_FOUND:
					endTime = System.currentTimeMillis();
					break;
				}
			} else
			{
				mRtnValue = false;
			}
		}
		return mRtnValue;
	}

	public boolean setStatus(int aState, String aComments)
	{
		if (setStatus(aState))
			status += ": " + aComments;
		return !isTerminating();
	}

	public void setStatus(Exception aException)
	{
		synchronized (this)
		{
			state = SqlStatus.ENDED_IN_ERROR;
			status = SqlStatus.ENDED_IN_ERROR_STRING + ": " + aException.getMessage();
			exception = aException;
		}
	}

	public void terminateQuery()
	{
		synchronized (this)
		{
			setStatus(SqlStatus.TERMINATING_QUERY);
		}
	}
}
