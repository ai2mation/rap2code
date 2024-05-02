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

package com.dth.fmw.sql.repo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dth.fmw.sql.database.SqlDBManager;
import com.dth.fmw.sql.database.SqlDataBase;
import com.dth.fmw.sql.query.SqlQuery;
import com.dth.fmw.sql.query.SqlResults;
import com.dth.fmw.sql.query.SqlStatus;

public abstract class Repository
{
	private List<SqlDataBase> databases = new ArrayList<>();
	private List<SqlQuery> queries = new ArrayList<>();
	private static Repository repositories = null;
	static public Map<String,SqlDBManager> sqlDbManagers = new HashMap<>();

	public Repository()
	{
		super();
		repositories = this;
		buildDBManagers();
		buildDatabases();
		buildQueries();
	}

	protected abstract void buildDatabases();

	protected abstract void buildDBManagers();

	protected abstract void buildQueries();

	static public SqlDataBase getDataBase(String aDataBaseName)
	{

		SqlDataBase mRtnValue = null;
		if (repositories != null)
		{
			int mSize = repositories.databases.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				try
				{
					SqlDataBase mDataBase = (SqlDataBase) repositories.databases.get(Lp0);
					String mDataBaseName = mDataBase.getLogicalName();
					if (aDataBaseName.equalsIgnoreCase(mDataBaseName))
					{
						mRtnValue = mDataBase;
						Lp0 = mSize;
					}
				} catch (ClassCastException aException) // ignore
				{
				}
			}
		}
		return mRtnValue;
	}

	public static SqlDBManager getDBManager(String aDBManager)
	{
		return (SqlDBManager) sqlDbManagers.get(aDBManager);
	}

	public static Repository getDomain()
	{
		return repositories;
	}

	public void insertDatabase(SqlDataBase aDatabase)
	{
		databases.add(aDatabase);
	}

	public void insertDBManager(String aName, SqlDBManager aManager)
	{
		sqlDbManagers.put(aName, aManager);
	}

	public void insertQuery(SqlQuery aQuery)
	{
		queries.add(aQuery);
	}

	public static SqlResults runQuery(String aNameOfQuery, RepoCriteria aCriteria, SqlStatus aStatus)
			throws SQLException
	{
		SqlResults mRtnValue = null;
		if (repositories != null)
		{
			int mSize = repositories.queries.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				try
				{
					SqlQuery mQuery = (SqlQuery) repositories.queries.get(Lp0);
					String mQueryName = mQuery.getLogicalName();
					if (aNameOfQuery.equalsIgnoreCase(mQueryName))
					{
						//mQueryToBeRun = mQuery;
						if (!aStatus.isTerminating())
						{
							mRtnValue = mQuery.runQuery(aCriteria, aStatus);
						}
						Lp0 = mSize;
					}
				} catch (ClassCastException aException) // ignore
				{
				}
			}
		}
		return mRtnValue;
	}

	public static void setDomain(Repository aDomain)
	{
		repositories = aDomain;
	}
}
