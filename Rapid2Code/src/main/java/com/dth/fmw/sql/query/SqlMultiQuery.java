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

import com.dth.fmw.sql.repo.RepoCriteria;

public abstract class SqlMultiQuery extends SqlQuery
{

	public SqlMultiQuery(String aLogicalName)
	{
		super(aLogicalName);
		standardQuery = false;
	}

	public SqlMultiQuery(String aLogicalName, boolean processResultSetOn)
	{
		super(aLogicalName, processResultSetOn);
		standardQuery = false;
	}

	protected abstract void buildQueries();

	protected void buildQuery()
	{
		buildQueries();
	}

	public abstract String getUserID(RepoCriteria aCriteria);

	public SqlResults runQuery(RepoCriteria aCriteria, SqlStatus aStatus) throws SQLException
	{
		SqlResults mRtnValue = null;
		RepoCriteria mCriteria = new RepoCriteria(aCriteria);
		int mSize = oElements.size();
		aStatus.push();
		boolean mTerminating = false;
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlQuery mSqlQuery = (SqlQuery) oElements.get(Lp0);
			String mQueryName = mSqlQuery.getLogicalName();
			String mPass = "Pass " + (Lp0 + 1) + " of " + mSize + " <" + mQueryName + ">";
			aStatus.setStatus(SqlStatus.GENERATING_QUERY, mPass);
			mTerminating = aStatus.isTerminating();
			if (!mTerminating)
			{
				String mQuery = "";
				if (mSqlQuery.standardQuery)
				{
					if (mSqlQuery.useOrigCriteria())
					{
						mQuery = mSqlQuery.getQuery(aCriteria, mRtnValue);
					} else
					{
						mQuery = mSqlQuery.getQuery(mCriteria, mRtnValue);
					}
					if (mQuery.equals("stop processing"))
					{
						aStatus.sysTerminateQuery(mSqlQuery.getLogicalName()
								+ " Query did not return any data.  Please try different Criteria selection.");
					} else if (!mQuery.equals("setting criteria"))
					{
						mTerminating = !aStatus.setStatus(SqlStatus.EXECUTING_QUERY, mPass);
						if (!mTerminating)
							mRtnValue = runQuery(aStatus, mCriteria, mQuery, mSqlQuery.getDataBase(), mSqlQuery);
					}
				} else
				{
					mTerminating = !aStatus.setStatus(SqlStatus.EXECUTING_QUERY, mPass);
					if (!mTerminating)
						mRtnValue = mSqlQuery.runQuery(mCriteria, aStatus);
				}
			} else
			{
				Lp0 = mSize;
			}
		}
		aStatus.setResults(mRtnValue);
		aStatus.pop();
		if (mTerminating)
		{
			aStatus.setStatus(SqlStatus.TERMINATING_QUERY);
		}
		return mRtnValue;
	}
}
