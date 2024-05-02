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

public class SqlWhere extends SqlQBase
{
	protected SqlQBase lValue;
	protected SqlQBase rValue;
	protected String operator;
	protected String suffix;
	protected boolean noSuffix = false;

	public SqlWhere(SqlField aLValue, String aOperator, SqlField aRValue)
	{
		super();
		lValue = aLValue;
		operator = aOperator;
		rValue = aRValue;
		suffix = "";
	}

	public SqlWhere(SqlField aLValue, String aOperator, SqlField aRValue, String aSuffix)
	{
		super();
		lValue = aLValue;
		operator = aOperator;
		rValue = aRValue;
		suffix = aSuffix;
	}

	public SqlWhere(SqlQBase aLValue, String aOperator, SqlQBase aRValue)
	{
		super();
		lValue = aLValue;
		operator = aOperator;
		rValue = aRValue;
		suffix = "";
	}

	public SqlWhere(SqlQBase aLValue, String aOperator, SqlQBase aRValue, String aSuffix)
	{
		super();
		lValue = aLValue;
		operator = aOperator;
		rValue = aRValue;
		suffix = aSuffix;
	}

	public SqlQBase getLValue()
	{
		return lValue;
	}

	public String getOperator()
	{
		return operator;
	}

	public SqlQBase getRValue()
	{
		return rValue;
	}

	public String getSuffix()
	{
		String mRtnValue = suffix;
		if (noSuffix)
			mRtnValue = "";
		return mRtnValue;
	}

	public String queryType()
	{
		return SqlWhere.class.toString();
	}

	public SqlWhere setNoSuffix(boolean aValue)
	{
		noSuffix = aValue;
		return this;
	}

	public boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria)
	{
		boolean mRtnValue = false;
		StringBuffer mQueryString = new StringBuffer("");
		boolean mSkip = false;
		if (lValue.isQueryType(SqlField.class.toString()) && rValue.isQueryType(SqlField.class.toString()))
		{
			mSkip = !rValue.toQString(mQueryString, this, aCriteria);
		} else
		{
			mSkip = !rValue.toQString(mQueryString, lValue, aCriteria);
		}
		if (!mSkip)
		{
			if (aLastField == null || aLastField.isQueryType(SqlWhere.class.toString()))
			{
				if (aLastField != null)
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
			if (aLastField == null && lValue.isQueryType(SqlField.class.toString()))
			{
				aLastField = this;
			}
			lValue.toQString(aQueryString, aLastField, aCriteria);
			aQueryString.append(" " + operator + " " + mQueryString);
			mRtnValue = true;
		}
		return mRtnValue;
	}

	public boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria, String aQualifier)
	{
		return toQString(aQueryString, aLastField, aCriteria);
	}
}
