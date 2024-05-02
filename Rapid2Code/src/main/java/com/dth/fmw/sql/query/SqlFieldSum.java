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

import com.dth.fmw.sql.database.SqlDbColumn;
import com.dth.fmw.sql.repo.RepoCriteria;

public class SqlFieldSum extends SqlField
{

	public SqlFieldSum(SqlFrom aSqlFrom, SqlDbColumn aColumn, String aAsColumn, String aName)
	{
		super(aSqlFrom, aColumn, aAsColumn, aName);
	}

	public boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria)
	{
		if (aLastField == null)
		{
			aQueryString.append("SELECT ");
		} else if (aLastField.isQueryType(SqlField.class.toString()))
		{
			aQueryString.append(",\n   ");
		}
		SqlFrom mFrom = getFrom();
		SqlDbColumn mColumn = getColumn();
		String mAsColumn = getAsColumn();
		String mAlias = "";
		if (!mFrom.getAlias().equals(""))
		{
			mAlias = mFrom.getAlias() + ".";
		}
		String mAsString = "";
		if (mAsColumn != null && !mAsColumn.equals(""))
		{
			mAsString = " as " + mAsColumn;
		}
		aQueryString.append("SUM(" + mAlias + mColumn.getPhysicalName() + ")" + mAsString);
		return true;
	}
}
