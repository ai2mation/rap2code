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

import com.dth.fmw.sql.common.SortableObject;
import com.dth.fmw.sql.common.SqlColumn;
import com.dth.fmw.sql.common.SqlRow;

public class SqlResultRow extends SortableObject implements SqlRow
{
	private List<SqlColumn> columns = new ArrayList<>();
	private int slot = 0;

	public SqlResultRow()
	{
		super();
	}

	public SqlResultColumn getColumnAt(int aIndex)
	{
		SqlResultColumn mRtnValue = null;
		slot = aIndex;
		if (slot > -1 && slot < columns.size())
			;
		{
			try
			{
				mRtnValue = (SqlResultColumn) columns.get(slot++);
			} catch (ClassCastException aException)
			{
			}
		}
		return mRtnValue;
	}

	public List<String> getColumnNames()
	{
		SqlResultColumn mColumn = null;
		List<String> mRtnValue = new ArrayList<>();
		for (int Lp0 = 0; Lp0 < columns.size(); Lp0++)
		{
			mColumn = (SqlResultColumn) columns.get(Lp0);
			mRtnValue.add(mColumn.getName());

		}
		return mRtnValue;
	}

	public SqlResultColumn getFirstColumn()
	{
		reset();
		return (SqlResultColumn) nextColumn();
	}

	public SqlResultColumn getNextColumn()
	{
		return (SqlResultColumn) nextColumn();
	}

	public int getSize()
	{
		return columns.size();
	}

	public boolean hasMoreColumns()
	{
		boolean mRtnValue = false;
		if (getSize() > 0 && slot < getSize())
		{
			mRtnValue = true;
		} else
		{
			reset();
		}
		return mRtnValue;
	}

	public void insertColumn(SqlResultColumn aColumn)
	{
		columns.add(aColumn);
	}

	public SqlColumn nextColumn()
	{
		SqlColumn mRtnValue = null;
		if (hasMoreColumns())
		{
			mRtnValue = getColumnAt(slot++);
		}
		return mRtnValue;
	}

	public boolean replaceColumnAt(int aIndex, SqlResultColumn aColumn)
	{
		boolean mRtnValue = false;
		int mSize = columns.size();
		if (aIndex < mSize)
		{
			columns.set(aIndex, aColumn);
			mRtnValue = true;
		}
		return mRtnValue;
	}

	public void reset()
	{
		slot = 0;
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		for (int Lp0 = 0; Lp0 < columns.size(); Lp0++)
		{
			sb.append(((SqlResultColumn) columns.get(Lp0)).toString());
		}
		return sb.toString();
	}
}
