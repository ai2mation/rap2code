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

import com.dth.fmw.sql.repo.RepoCriteria;

public class SqlWhereGroup extends SqlWhere
{

	public SqlWhereGroup(SqlField aLValue, String aOperator, SqlField aRValue)
	{
		super(aLValue, aOperator, aRValue);
	}

	public SqlWhereGroup(SqlField aLValue, String aOperator, SqlField aRValue, String aSuffix)
	{
		super(aLValue, aOperator, aRValue, aSuffix);
	}

	public SqlWhereGroup(SqlQBase aLValue, String aOperator, SqlQBase aRValue)
	{
		super(aLValue, aOperator, aRValue);
	}

	public SqlWhereGroup(SqlQBase aLValue, String aOperator, SqlQBase aRValue, String aSuffix)
	{
		super(aLValue, aOperator, aRValue, aSuffix);
	}

	public String queryFullType()
	{
		return SqlWhereGroup.class.toString();
	}

	public boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria)
	{
		boolean mRtnValue = true;
		StringBuffer mQueryString = new StringBuffer("");
		boolean mSkip = !rValue.toQString(mQueryString, lValue, aCriteria);
		if (!mSkip)
		{
			if (aLastField == null || aLastField.isQueryType(SqlWhere.class.toString()))
			{
				if (!noSuffix && !(aLastField == null))
				{
					if (aLastField.queryFullType().equals(SqlOptionalWhere.class.toString()))
					{
						SqlOptionalWhere mWhere = (SqlOptionalWhere) aLastField;
						aQueryString.append(" " + mWhere.getSuffix(this, aCriteria) + "\n   ");
					} else
					{
						SqlWhere mWhere = (SqlWhere) aLastField;
						aQueryString.append(" " + mWhere.getSuffix() + "\n   ");
					}
				}
			} else
			{
				aQueryString.append("\nWHERE ");
			}
			aQueryString.append("(");
			lValue.toQString(aQueryString, null, aCriteria);
			aQueryString.append(" " + operator + " " + mQueryString + ")");
		}
		return mRtnValue;
	}
}
