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

import java.util.List;

import com.dth.core.mdl.qry.SqlFieldNode;
import com.dth.fmw.sql.query.SqlField;
import com.dth.fmw.sql.query.SqlFromGroup;
import com.dth.fmw.sql.query.SqlGroup;
import com.dth.fmw.sql.query.SqlOptionalField;
import com.dth.fmw.sql.query.SqlQBase;
import com.dth.fmw.sql.query.SqlUnionAll;

public class SqlDbTableView extends SqlDbTable
{

	public SqlDbTableView(SqlFromGroup aFromGroup, String aLogicalName, String aPhysicalName)
	{
		super(aLogicalName, aPhysicalName);
		selectAll(aFromGroup);
	}

	public SqlDbTableView(SqlFromGroup aFromGroup, String aLogicalName, String aPhysicalName, String aQualifier)
	{
		super(aLogicalName, aPhysicalName, aQualifier);
		selectAll(aFromGroup);
	}

	public SqlDbTableView(String aLogicalName, String aPhysicalName)
	{
		super(aLogicalName, aPhysicalName);
	}

	public SqlDbTableView(String aLogicalName, String aPhysicalName, Object aReference)
	{
		super(aLogicalName, aPhysicalName, aReference);
	}

	public SqlDbTableView(String aLogicalName, String aPhysicalName, String aQualifier)
	{
		super(aLogicalName, aPhysicalName, aQualifier);
	}

	public SqlDbTableView(String aLogicalName, String aPhysicalName, String aQualifier, Object aReference)
	{
		super(aLogicalName, aPhysicalName, aQualifier, aReference);
	}

	public void selectAll(SqlFromGroup aFromGroup)
	{
		SqlGroup mGroup = aFromGroup.getGroup();
		List<SqlQBase> mList = mGroup.getElements();
		int mSize = mList.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlQBase mObject = (SqlQBase) mList.get(Lp0);
			if (mObject.isSelectable())
			{
				if (mObject.queryFullType().equals(SqlOptionalField.class.toString()))
				{
					SqlOptionalField mField = (SqlOptionalField) mObject;
					mField.selectAll(this);
				} else if (mObject.queryType().equals(SqlField.class.toString()))
				{
					SqlField mField = (SqlField) mObject;
					String mName = mField.getName();
					SqlDbColumn mColumn = mField.getColumn();
					String mAsColumn = mField.getAsColumn();
					SqlFieldNode mSqlFieldNode = mColumn.getSqlFieldNode();
					if (mAsColumn != null && !mAsColumn.equals(""))
					{
						InsertColumn(mName, mAsColumn,mSqlFieldNode);
					} else
					{
						InsertColumn(mName, mColumn.getPhysicalName(),mSqlFieldNode);
					}
				}
			} else
			{
				if (mObject.queryType().equals(SqlUnionAll.class.toString()))
				{
					SqlUnionAll mUnionAll = (SqlUnionAll) mObject;
					mUnionAll.selectAll(this);
				}
			}
		}
	}
}
