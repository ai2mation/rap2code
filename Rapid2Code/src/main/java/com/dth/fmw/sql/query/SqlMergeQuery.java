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
import java.util.ArrayList;
import java.util.List;

import com.dth.fmw.sql.repo.RepoCriteria;

public abstract class SqlMergeQuery extends SqlQuery
{
	protected boolean oResultSetsReqData = false;

	public SqlMergeQuery(String aLogicalName)
	{
		super(aLogicalName);
		standardQuery = false;
	}

	public SqlMergeQuery(String aLogicalName, boolean processResultSetOn)
	{
		super(aLogicalName, processResultSetOn);
		standardQuery = false;
	}

	protected abstract void buildQueries();

	protected void buildQuery()
	{
		buildQueries();
	}

	public SqlResults findResultSet(String aName, List<SqlResults> aResultSets)
	{
		SqlResults mRtnValue = null;
		int mSize = aResultSets.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlResults mResults = (SqlResults) aResultSets.get(Lp0);
			if (mResults.getResultName().equals(aName))
			{
				mRtnValue = mResults;
				Lp0 = mSize;
			}
		}
		return mRtnValue;
	}

	public abstract String getUserID(RepoCriteria aCriteria);

	public boolean isDataRequired()
	{
		return oResultSetsReqData;
	}

	protected abstract SqlResults mergeResults(RepoCriteria aCriteria, List<SqlResults> aResultSets) throws SQLException;

	public SqlResults runQuery(RepoCriteria aCriteria, SqlStatus aStatus) throws SQLException
	{
		SqlResults mRtnValue = null;
		RepoCriteria mCriteria = aCriteria;
		List<SqlResults> mResultSets = new ArrayList<>();
		int mSize = oElements.size();
		boolean mTerminating = false;
		aStatus.push();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			mCriteria = setNewCriteria(Lp0, aCriteria, mCriteria, mRtnValue);
			SqlQuery mSqlQuery = (SqlQuery) oElements.get(Lp0);
			String mQueryName = mSqlQuery.getLogicalName();
			String mPass = "Pass " + (Lp0 + 1) + " of " + mSize + " <" + mQueryName + ">";
			mTerminating = !aStatus.setStatus(SqlStatus.GENERATING_QUERY, mPass);
			if (!mTerminating && mSqlQuery.isStandardQuery())
			{
				String mQuery = mSqlQuery.getQuery(mCriteria);
				if (mQuery.equals("stop processing"))
				{
					aStatus.sysTerminateQuery(mSqlQuery.getLogicalName()
							+ " Query did not return any data.  Please try different Criteria selection.");
				} else if (!mQuery.equals("setting criteria"))
				{
					mTerminating = !aStatus.setStatus(SqlStatus.EXECUTING_QUERY, mPass);
					if (!mTerminating)
					{
						mRtnValue = runQuery(aStatus, aCriteria, mQuery, mSqlQuery.getDataBase(), mSqlQuery);
						mResultSets.add(mRtnValue);
						mRtnValue.setResultName(mQueryName);
					}
				}
			} else
			{
				mTerminating = !aStatus.setStatus(SqlStatus.EXECUTING_QUERY, mPass);
				if (!mTerminating)
				{
					mRtnValue = mSqlQuery.runQuery(aCriteria, aStatus);
					mResultSets.add(mRtnValue);
					mRtnValue.setResultName(mQueryName);
				}
			}
			if (mTerminating)
			{
				Lp0 = mSize;
			}
		}
		if (!mTerminating)
		{
			boolean allHaveData = true;
			if (isDataRequired())
			{
				for (int Lp0 = 0; Lp0 < mResultSets.size(); Lp0++)
				{
					SqlResults aResultSet = (SqlResults) mResultSets.get(Lp0);
					if (!aResultSet.isUseResultSetOnly() && aResultSet.getNumberOfRows() == 0)
					{
						allHaveData = false;
						Lp0 = mResultSets.size();
					}
				}
			}
			if (allHaveData)
			{
				aStatus.setStatus(SqlStatus.MERGE_RESULTS);
				mRtnValue = mergeResults(aCriteria, mResultSets);
				aStatus.setResults(mRtnValue);
			} else
			{
				mTerminating = true;
				aStatus.sysTerminateQuery();
			}
		}
		aStatus.pop();
		if (mTerminating)
		{
			aStatus.setStatus(SqlStatus.TERMINATING_QUERY);
		}
		return mRtnValue;
	}

	public void setDataRequired(boolean isDataRequired)
	{
		oResultSetsReqData = isDataRequired;
	}

	public RepoCriteria setNewCriteria(int aIndex, RepoCriteria aOrigCriteria, RepoCriteria aLastCriteria,
			SqlResults aResults)
	{
		return aOrigCriteria;
	}
}
