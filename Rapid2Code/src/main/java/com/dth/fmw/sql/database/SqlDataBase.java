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

public abstract class SqlDataBase
{
	private List<SqlDbTable> oTables = new ArrayList<>();

	private java.lang.String oQualifier = null;
	private boolean oUseQualifier = false;
	private java.lang.String oLogicalName;
	private java.lang.String oPhysicalName;

	public SqlDataBase(String aLogicalName, String aPhysicalName)
	{
		oLogicalName = aLogicalName;
		oPhysicalName = aPhysicalName;
		buildTables();
	}

	public SqlDataBase(String aLogicalName, String aPhysicalName, String aQualifier)
	{
		oLogicalName = aLogicalName;
		oPhysicalName = aPhysicalName;
		oQualifier = aQualifier;
		oUseQualifier = true;
	}

	protected abstract void buildTables();

	public abstract String getDatabaseURL();

	public String getLogicalName()
	{
		return oLogicalName;
	}

	public SqlDbTable getTable(String aTable)
	{
		SqlDbTable mRtnValue = null;
		int mSize = oTables.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlDbTable mTable = (SqlDbTable) oTables.get(Lp0);
			if (mTable.getLogicalName().equalsIgnoreCase(aTable))
			{
				mRtnValue = mTable;
				Lp0 = mSize;
			}
		}
		return mRtnValue;
	}

	public SqlDbTable insertTable(String aLogicalName, String aPhysicalName)
	{
		SqlDbTable mTable = new SqlDbTable(aLogicalName, aPhysicalName, this);
		oTables.add(mTable);
		return mTable;
	}

	public SqlDbTable insertTable(String aLogicalName, String aPhysicalName, String aQualifier)
	{
		SqlDbTable mTable = new SqlDbTable(aLogicalName, aPhysicalName, aQualifier, this);
		oTables.add(mTable);
		return mTable;
	}

	public List<SqlDbTable> getoTables()
	{
		return oTables;
	}

	public void setoTables(List<SqlDbTable> oTables)
	{
		this.oTables = oTables;
	}

	public java.lang.String getoQualifier()
	{
		return oQualifier;
	}

	public void setoQualifier(java.lang.String oQualifier)
	{
		this.oQualifier = oQualifier;
	}

	public boolean isoUseQualifier()
	{
		return oUseQualifier;
	}

	public void setoUseQualifier(boolean oUseQualifier)
	{
		this.oUseQualifier = oUseQualifier;
	}

	public java.lang.String getoLogicalName()
	{
		return oLogicalName;
	}

	public void setoLogicalName(java.lang.String oLogicalName)
	{
		this.oLogicalName = oLogicalName;
	}

	public java.lang.String getoPhysicalName()
	{
		return oPhysicalName;
	}

	public void setoPhysicalName(java.lang.String oPhysicalName)
	{
		this.oPhysicalName = oPhysicalName;
	}
}
