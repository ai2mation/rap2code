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

import java.util.ArrayList;
import java.util.List;

import com.dth.fmw.sql.database.SqlDBManager;
import com.dth.fmw.sql.database.SqlDataBase;
import com.dth.fmw.sql.database.SqlDbTableView;
import com.dth.fmw.sql.repo.RepoCriteria;
import com.dth.fmw.sql.repo.Repository;

import java.lang.String;
import java.sql.SQLException;

public abstract class SqlQuery
{
	private String logicalName;
	private SqlDataBase sqlDataBase;
	private boolean useOrigCriteria = false;
	protected List<Object> oElements = new ArrayList<>();
	protected boolean standardQuery = true;
	private boolean useResultSetOnly = true;

	public SqlQuery(String aLogicalName)
	{
		super();
		standardQuery = true;
		logicalName = aLogicalName;
		buildQuery();
	}

	public SqlQuery(String aLogicalName, boolean useResultSetOnly)
	{
		super();
		standardQuery = true;
		logicalName = aLogicalName;
		useResultSetOnly(useResultSetOnly);
		buildQuery();
	}

	protected abstract void buildQuery();

	public SqlResults getColumnNames(RepoCriteria aCriteria)
	{
		SqlResults mRtnValue = new SqlResults();
		getColumnNames(mRtnValue, aCriteria);
		return mRtnValue;
	}

	public void getColumnNames(SqlResults aResults, RepoCriteria aCriteria)
	{
		int mSize = oElements.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			try
			{
				SqlQBase mElement = (SqlQBase) oElements.get(Lp0);
				mElement.setColumnName(aCriteria, aResults);
			} catch (ClassCastException aException) // ignore
			{
			}
		}
	}

	protected SqlDataBase getDataBase()
	{
		return sqlDataBase;
	}

	public String getLogicalName()
	{
		return logicalName;
	}

	public String getQuery(RepoCriteria aCriteria)
	{
		StringBuffer mRtnValue = new StringBuffer();
		SqlQBase mLastField = null;
		int mSize = oElements.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlQBase mQueryObj = (SqlQBase) oElements.get(Lp0);
			boolean mResults = mQueryObj.toQString(mRtnValue, mLastField, aCriteria);
			if (mResults)
				mLastField = mQueryObj;
		}
		return mRtnValue.toString();
	}

	public String getQuery(RepoCriteria aCriteria, SqlResults aResults)
	{
		return getQuery(aCriteria);
	}

	protected String getSelectSpecial()
	{
		return "";
	}

	public abstract String getUserID(RepoCriteria aCriteria);

	protected void insertElement(SqlQBase aElement)
	{
		oElements.add(aElement);
	}

	protected void insertField(SqlFromGroup aFromGroup, SqlDbTableView aTable, SqlQBase aObject, String aQualifier)
	{
		if (aObject.isSelectable())
		{
			if (aObject.queryFullType().equals(SqlOptionalField.class.toString()))
			{
				SqlOptionalField mField = (SqlOptionalField) aObject;
				insertElement(mField.cloneSelectAll(aFromGroup, aTable, aQualifier));
			} else if (aObject.queryType().equals(SqlField.class.toString()))
			{
				SqlField mField = (SqlField) aObject;
				String mName = mField.getName();
				String mAsColumn = mField.getAsColumn();
				insertElement(new SqlField(aFromGroup, aTable.getColumn(mName), mAsColumn, mName));
			}
		}
	}

	public boolean isStandardQuery()
	{
		return standardQuery;
	}

	public boolean isUseResultSetOnly()
	{
		return useResultSetOnly;
	}

	public SqlResults runQuery(RepoCriteria aCriteria, SqlStatus aStatus) throws SQLException
	{
		SqlResults mRtnValue = null;
		String mQuery = null;
		if (aStatus.setStatus(SqlStatus.GENERATING_QUERY))
			mQuery = getQuery(aCriteria);
		if (aStatus.setStatus(SqlStatus.EXECUTING_QUERY))
		{
			mRtnValue = runQuery(aStatus, aCriteria, mQuery);
			aStatus.setResults(mRtnValue);
		}
		return mRtnValue;
	}

	public SqlResults runQuery(SqlStatus aStatus, RepoCriteria aCriteria, String aQuery) throws SQLException
	{
		return runQuery(aStatus, aCriteria, aQuery, sqlDataBase, this);
	}

	public SqlResults runQuery(SqlStatus aStatus, RepoCriteria aCriteria, String aQuery, SqlDataBase aSqlDataBase,
			SqlQuery aSqlQuery) throws SQLException
	{
		SqlResults mRtnValue = new SqlResults(false, aSqlQuery.isUseResultSetOnly());
		aSqlQuery.getColumnNames(mRtnValue, aCriteria);
		SqlDBManager mSqlDBManager = Repository.getDBManager(aSqlDataBase.getLogicalName());
		mSqlDBManager.runQuery(aStatus, mRtnValue, aQuery, getUserID(aCriteria));
		return mRtnValue;
	}

	protected void selectAll(SqlFromGroup aFromGroup, String aQualifier)
	{
		SqlDbTableView mTable = new SqlDbTableView(aFromGroup, "", "", aQualifier);
		SqlGroup mGroup = aFromGroup.getGroup();
		List<SqlQBase> mGroups = mGroup.getElements();
		int mSize = mGroups.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlQBase mObject = (SqlQBase) mGroups.get(Lp0);
			if (mObject.queryFullType().equals(SqlUnionAllMultiQualifier.class.toString()))
			{
				SqlUnionAllMultiQualifier mUnion = (SqlUnionAllMultiQualifier) mObject;
				List<SqlQBase> mUnions = mUnion.getElements();
				int mUnionSize = mUnions.size();
				for (int Lp1 = 0; Lp1 < mUnionSize; Lp1++)
				{
					mObject = (SqlQBase) mUnions.get(Lp1);
					insertField(aFromGroup, mTable, mObject, aQualifier);
				}
			} else
			{
				insertField(aFromGroup, mTable, mObject, aQualifier);
			}
		}
	}

	protected void setDataBase(SqlDataBase aDataBase)
	{
		sqlDataBase = aDataBase;
	}

	public boolean useOrigCriteria()
	{
		return useOrigCriteria;
	}

	public SqlQuery useOrigCriteria(boolean aUseOrigCriteria)
	{
		useOrigCriteria = aUseOrigCriteria;
		return this;
	}

	public void useResultSetOnly(boolean aUseResultSetOnly)
	{
		useResultSetOnly = aUseResultSetOnly;
	}
}
