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

import java.util.List;

import com.dth.fmw.sql.database.SqlDbColumn;
import com.dth.fmw.sql.database.SqlDbTableView;
import com.dth.fmw.sql.repo.RepoCriteria;

public class SqlOptionalField extends SqlOptional
{

	public SqlOptionalField cloneSelectAll(SqlFromGroup aFromGroup, SqlDbTableView aTable, String aQualifier)
	{
		SqlOptionalField mRtnValue = new SqlOptionalField();
		mRtnValue.options = options;
		List<SqlQBase> mList = null;
		List<SqlQBase> mList2 = null;
		for (int Lp0 = 0; Lp0 < 2; Lp0++)
		{
			if (Lp0 == 0)
			{
				mList = elements;
				mList2 = mRtnValue.elements;
			}
			if (Lp0 == 1)
			{
				mList = alternate;
				mList2 = mRtnValue.alternate;
			}
			int mSize = mList.size();
			for (int Lp1 = 0; Lp1 < mSize; Lp1++)
			{
				SqlQBase mObject = (SqlQBase) mList.get(Lp1);
				if (mObject.isSelectable())
				{
					if (mObject.queryFullType().equals(SqlOptionalField.class.toString()))
					{
						SqlOptionalField mTheField = (SqlOptionalField) mObject;
						SqlOptionalField mTheClone = mTheField.cloneSelectAll(aFromGroup, aTable, aQualifier);
						mList2.add(mTheClone);
					} else if (mObject.queryType().equals(SqlField.class.toString()))
					{
						SqlField mField = (SqlField) mObject;
						String mName = mField.getName();
						String mAsColumn = mField.getAsColumn();
						mList2.add(new SqlField(aFromGroup, aTable.getColumn(mName), mAsColumn, mName));
					}
				}
			}
		}
		return mRtnValue;
	}

	public boolean isSelectable()
	{
		return true;
	}

	public String queryFullType()
	{
		return SqlOptionalField.class.toString();
	}

	public String queryType()
	{
		return SqlField.class.toString();
	}

	public void selectAll(SqlDbTableView aTable)
	{
		List<SqlQBase> mList;
		for (int Lp0 = 0; Lp0 < 2; Lp0++)
		{
			if (Lp0 == 0)
			{
				mList = elements;
			} else
			{
				mList = alternate;
			}
			int mSize = mList.size();
			for (int Lp1 = 0; Lp1 < mSize; Lp1++)
			{
				SqlQBase mObject = (SqlQBase) mList.get(Lp1);
				if (mObject.isSelectable())
				{
					if (mObject.queryFullType().equals(SqlOptionalField.class.toString()))
					{
						SqlOptionalField mOptionalField = (SqlOptionalField) mObject;
						mOptionalField.selectAll(aTable);
					} else if (mObject.queryType().equals(SqlField.class.toString()))
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
	}

	public void setColumnName(RepoCriteria aCriteria, SqlResults aSqlResults)
	{
		boolean mResults = true;
		int mSize = options.size();
		StringBuffer mQueryString = new StringBuffer();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlQBase mOption = (SqlQBase) options.get(Lp0);
			mResults = mOption.toQString(mQueryString, null, aCriteria);
			if (!mResults)
				Lp0 = mSize;
		}
		if (mResults)
		{
			mSize = elements.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				SqlQBase mQueryObj = (SqlQBase) elements.get(Lp0);
				mQueryObj.setColumnName(aCriteria, aSqlResults);
			}
		} else
		{
			mSize = alternate.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				SqlQBase mQueryObj = (SqlQBase) alternate.get(Lp0);
				mQueryObj.setColumnName(aCriteria, aSqlResults);
			}
		}
	}
}
