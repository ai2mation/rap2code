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

public class SqlOrderBy extends SqlQBase
{
	public SqlFrom sqlFrom;
	public SqlDbColumn sqlDbColumn;

	public SqlOrderBy()
	{
		super();
	}

	public SqlOrderBy(SqlFrom aQOMFrom, SqlDbColumn aColumn)
	{
		super();
		sqlFrom = aQOMFrom;
		sqlDbColumn = aColumn;
	}

	public String queryType()
	{
		return SqlOrderBy.class.toString();
	}

	public boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria)
	{
		if (aLastField.queryType().equals(SqlOrderBy.class.toString()))
		{
			aQueryString.append(",\n   ");
		} else
		{
			aQueryString.append("\nORDER BY ");
		}

		String mAlias = sqlFrom.getAlias();
		if (mAlias != null && !mAlias.equals(""))
		{
			mAlias = mAlias + ".";
		}
		aQueryString.append(mAlias + sqlDbColumn.getPhysicalName());
		return true;
	}
}
