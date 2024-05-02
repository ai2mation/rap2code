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

import java.util.ArrayList;
import java.util.List;

import com.dth.core.mdl.qry.SqlFieldNode;

public class SqlDbTable
{
	private String oLogicalName;
	private String oPhysicalName;
	private Object oReference;
	private List<SqlDbColumn> oColumns = new ArrayList<>();

	private String oQualifier = null;

	public SqlDbTable(String aLogicalName, String aPhysicalName)
	{
		oLogicalName = aLogicalName;
		oPhysicalName = aPhysicalName;
	}

	public SqlDbTable(String aLogicalName, String aPhysicalName, Object aReference)
	{
		oLogicalName = aLogicalName;
		oPhysicalName = aPhysicalName;
		oReference = aReference;
	}

	public SqlDbTable(String aLogicalName, String aPhysicalName, String aQualifier)
	{
		oLogicalName = aLogicalName;
		oPhysicalName = aPhysicalName;
		oQualifier = aQualifier;
	}

	public SqlDbTable(String aLogicalName, String aPhysicalName, String aQualifier, Object aReference)
	{
		oLogicalName = aLogicalName;
		oPhysicalName = aPhysicalName;
		oReference = aReference;
		oQualifier = aQualifier;
	}

	public static SqlDbTable clone(SqlDbTable aTable)
	{
		SqlDbTable mRtnValue = new SqlDbTable(aTable.oLogicalName, aTable.oPhysicalName, aTable.oQualifier,
				aTable.oReference);
		return mRtnValue;
	}

	public static SqlDbTable clone(SqlDbTable aTable, String aQualifier)
	{
		SqlDbTable mRtnValue = new SqlDbTable(aTable.oLogicalName, aTable.oPhysicalName, aQualifier, aTable.oReference);
		return mRtnValue;
	}

	public SqlDbColumn getColumn(String aColumn)
	{
		SqlDbColumn mRtnValue = null;
		int mSize = oColumns.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlDbColumn mColumn = (SqlDbColumn) oColumns.get(Lp0);
			if (mColumn.getLogicalName().equalsIgnoreCase(aColumn))
			{
				mRtnValue = mColumn;
				Lp0 = mSize;
			}
		}
		return mRtnValue;
	}

	public SqlDbColumn getColumnAt(int aSlot)
	{
		SqlDbColumn mRtnValue = null;
		int mSize = oColumns.size();
		if (mSize <= aSlot)
		{
			mRtnValue = (SqlDbColumn) oColumns.get(aSlot);
		}
		return mRtnValue;
	}

	public String getLogicalName()
	{
		return oLogicalName;
	}

	public String getPhysicalName()
	{
		return oPhysicalName;
	}

	public String getTableName()
	{
		String mRtnValue = "";
		if (oQualifier.length() > 0)
		{
			mRtnValue = oQualifier + "." + oPhysicalName;
		} else
		{
			mRtnValue = oPhysicalName;
		}
		return mRtnValue;
	}

	public String getTableName(String aQualifier)
	{
		String mRtnValue = "";
		if (aQualifier != null && aQualifier.length() > 0)
		{
			mRtnValue = aQualifier + "." + oPhysicalName;
		} else
		{
			mRtnValue = oPhysicalName;
		}
		return mRtnValue;
	}

	public SqlDbColumn InsertColumn(String aLogicalName, String aPhyscialName, SqlFieldNode aSqlFieldNode)
	{
		SqlDbColumn mColumn = new SqlDbColumn(aLogicalName, aPhyscialName, this, aSqlFieldNode);
		oColumns.add(mColumn);
		return mColumn;
	}

	public String toString()
	{
		return getTableName();
	}
}
