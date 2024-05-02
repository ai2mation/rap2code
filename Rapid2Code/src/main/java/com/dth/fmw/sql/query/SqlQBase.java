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

public abstract class SqlQBase
{
	public final static int DATABASE_IS_MYSQL = 1;
	public final static int NO_REFORMAT = 0;
	public final static int DATE_SLASHES_TO_DASHES = 1000;
	public static int databaseType = DATABASE_IS_MYSQL;
	protected int reformatCode = NO_REFORMAT;

	public SqlQBase()
	{
		super();
	}

	public boolean isOptional()
	{
		return false;
	}

	protected boolean isQueryType(String aQueryType)
	{
		boolean mRtnValue = false;
		if (aQueryType.equals(queryType()))
		{
			mRtnValue = true;
		}
		return mRtnValue;
	}

	public boolean isSelectable()
	{
		return false;
	}

	public String queryFullType()
	{
		return queryType();
	}

	public abstract String queryType();

	protected String reformatData(String aValue)
	{
		String mRtnValue = aValue;
		switch (reformatCode)
		{
		case DATE_SLASHES_TO_DASHES:
			mRtnValue = reformatSlashesToDashes(aValue);
			break;
		}
		return mRtnValue;
	}

	protected String reformatSlashesToDashes(String aValue)
	{
		String mRtnValue = aValue;
		StringBuffer mMonth = new StringBuffer();
		StringBuffer mDay = new StringBuffer();
		StringBuffer mYear = new StringBuffer();
		int mFlag = 0;
		int mSize = aValue.length();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			char mChar = aValue.charAt(Lp0);
			if (mChar != '/')
			{
				switch (mFlag)
				{
				case 0:
					mMonth.append(mChar);
					break;
				case 1:
					mDay.append(mChar);
					break;
				case 2:
					mYear.append(mChar);
					break;
				}
			} else
			{
				mFlag++;
			}
		}
		mRtnValue = mYear + "-" + mMonth + "-" + mDay;
		return mRtnValue;
	}

	public void setColumnName(RepoCriteria aCriteria, SqlResults aSQLResults)
	{
	}

	protected void setReformatCode(int aCode)
	{
		reformatCode = aCode;
	}

	public abstract boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria);
}
