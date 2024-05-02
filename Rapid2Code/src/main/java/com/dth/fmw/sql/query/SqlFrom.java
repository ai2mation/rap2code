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

import com.dth.fmw.sql.database.SqlDbTable;
import com.dth.fmw.sql.repo.RepoCriteria;

public class SqlFrom extends SqlQBase
{
	private SqlDbTable table;
	private String alias;

	public SqlFrom(SqlDbTable aTable, String aAlias)
	{
		table = aTable;
		alias = aAlias;
	}

	public String getAlias()
	{
		return alias;
	}

	public SqlDbTable getTable()
	{
		return table;
	}

	public String getTableName(SqlDbTable aTable, RepoCriteria aCriteria)
	{
		return aTable.getTableName();
	}

	public String queryType()
	{
		return SqlFrom.class.toString();
	}

	public boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria)
	{
		if (aLastField.isQueryType(SqlFrom.class.toString()))
		{
			aQueryString.append(",\n   ");
		} else if (aLastField.isQueryType(SqlWhere.class.toString()))
		{
			aQueryString.append("FROM ");
		} else
		{
			aQueryString.append("\nFROM ");
		}
		SqlDbTable mTable = getTable();
		String mAlias = getAlias();
		String mTableName = getTableName(mTable, aCriteria);
		aQueryString.append(mTableName + " " + mAlias);
		return true;
	}
}
