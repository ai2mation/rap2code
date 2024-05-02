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

import com.dth.fmw.sql.database.SqlDbColumn;
import com.dth.fmw.sql.database.SqlDbTableView;
import com.dth.fmw.sql.repo.RepoCriteria;

public class SqlUnionAll extends SqlQBase
{
	protected List<SqlQBase> oElements = new ArrayList<>();

	public SqlUnionAll()
	{
		super();
	}

	public List<SqlQBase> getElements()
	{
		return oElements;
	}

	public void insertElement(SqlQBase aElement)
	{
		oElements.add(aElement);
	}

	public String queryType()
	{
		return SqlUnionAll.class.toString();
	}

	public void selectAll(SqlDbTableView aTable)
	{
		//SqlDbTableView mTable = aTable;
		List<SqlQBase> mElements = oElements;
		int mSize = mElements.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlQBase mObject = (SqlQBase) mElements.get(Lp0);
			if (mObject.isSelectable() && mObject.queryType().equals(SqlField.class.toString()))
			{
				if (mObject.queryFullType().equals(SqlOptionalField.class.toString()))
				{
					SqlOptionalField mField = (SqlOptionalField) mObject;
					mField.selectAll(aTable);
				} else
				{
					SqlField mField = (SqlField) mObject;
					String mName = mField.getName();
					SqlDbColumn mColumn = mField.getColumn();
					String mAsColumn = mField.getAsColumn();
					if (mAsColumn != null && !mAsColumn.equals(""))
					{
						aTable.InsertColumn(mName, mAsColumn,mColumn.getSqlFieldNode());
					} else
					{
						aTable.InsertColumn(mName, mColumn.getPhysicalName(),mColumn.getSqlFieldNode());
					}
				}
			}
		}
	}

	public SqlDbTableView selectAll(SqlDbTableView aTable, SqlFromGroup aFromGroup, String aQualifier)
	{
		SqlDbTableView mTable = aTable;
		SqlGroup mGroup = aFromGroup.getGroup();
		List<SqlQBase> mSqlObjects = mGroup.getElements();
		int mSize = mSqlObjects.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlQBase mObject = (SqlQBase) mSqlObjects.get(Lp0);
			if (mObject.isSelectable())
			{
				if (mObject.queryFullType().equals(SqlOptionalField.class.toString()))
				{
					SqlOptionalField mField = (SqlOptionalField) mObject;
					insertElement(mField.cloneSelectAll(aFromGroup, mTable, aQualifier));
				} else if (mObject.queryType().equals(SqlField.class.toString()))
				{
					SqlField mField = (SqlField) mObject;
					String mName = mField.getName();
					String mAsColumn = mField.getAsColumn();
					insertElement(new SqlField(aFromGroup, mTable.getColumn(mName), mAsColumn, mName));
				}
			}
		}
		return mTable;
	}

	public SqlDbTableView selectAll(SqlFromGroup aFromGroup, String aQualifier)
	{
		SqlDbTableView mTable = new SqlDbTableView(aFromGroup, "", "", aQualifier);
		selectAll(mTable, aFromGroup, aQualifier);
		return mTable;
	}

	public SqlDbTableView selectAllTable(SqlFromGroup aFromGroup, String aQualifier)
	{
		SqlDbTableView mTable = new SqlDbTableView(aFromGroup, "", "", aQualifier);
		return mTable;
	}

	public boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria)
	{

		if (aLastField.isQueryType(SqlUnionAll.class.toString()))
		{
			aQueryString.append("\n  UNION ALL \n");
		}
		int mSize = oElements.size();
		//SqlQBase mLastField = null;
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlQBase mQueryObj = (SqlQBase) oElements.get(Lp0);
			mQueryObj.toQString(aQueryString, aLastField, aCriteria);
			//mLastField = mQueryObj;
		}
		return true;
	}
}
