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

public class SqlWhereDateTimeRange2 extends SqlWhere
{
	private SqlFieldTag fromDate;
	private SqlFieldTag fromTime;
	private SqlField date = null;
	private SqlField time = null;
	private SqlFieldTag toTime;
	private SqlFieldTag toDate;


	public SqlWhereDateTimeRange2(SqlFieldTag aFromDate, SqlFieldTag aToDate, SqlFieldTag aFromTime,
			SqlFieldTag aToTime, SqlField aDate1, SqlField aTime1, SqlField aDate2, SqlField aTime2, SqlFrom aFrom2,
			String aSuffix)
	{
		super(null, null, null, aSuffix);
		fromDate = aFromDate;
		toDate = aToDate;
		fromTime = aFromTime;
		toTime = aToTime;
		date = aDate1;
		time = aTime1;
		date = aDate2;
		time = aTime2;
	}

	public SqlWhereDateTimeRange2(SqlFieldTag aFromDate, SqlFieldTag aToDate, SqlFieldTag aFromTime,
			SqlFieldTag aToTime, SqlField aDate1, SqlField aTime1, String aSuffix)
	{
		super(null, null, null, aSuffix);
		fromDate = aFromDate;
		toDate = aToDate;
		fromTime = aFromTime;
		toTime = aToTime;
		date = aDate1;
		time = aTime1;
	}

	public String queryFullType()
	{
		return SqlWhereDateTimeRange.class.toString();
	}

	public int toIntTime(String aTime)
	{
		int mRtnValue = 0;
		int mSize = aTime.length();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			char mValue = aTime.charAt(Lp0);
			if (mValue >= '0' && mValue <= '9')
			{
				int mResult = Integer.parseInt("" + mValue);
				mRtnValue *= 10;
				mRtnValue += mResult;
			}
		}
		return mRtnValue;
	}

	public boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria)
	{
		boolean mRtnValue = true;
		if (mRtnValue)
			mRtnValue = fromTime.isValid(aCriteria);
		if (mRtnValue)
			mRtnValue = toTime.isValid(aCriteria);
		if (mRtnValue)
			mRtnValue = fromDate.isValid(aCriteria);
		if (mRtnValue)
			mRtnValue = toDate.isValid(aCriteria);
		if (mRtnValue)
		{
			String mSuffix = "";
			if (aLastField.isQueryType(SqlWhere.class.toString()))
			{
				if (aLastField.isOptional())
				{
					SqlOptionalWhere mWhere = (SqlOptionalWhere) aLastField;
					mSuffix = " " + mWhere.getSuffix(aLastField, aCriteria) + "\n  ";
				} else
				{
					SqlWhere mWhere = (SqlWhere) aLastField;
					mSuffix = " " + mWhere.getSuffix() + "\n  ";
				}
			} else
			{
				mSuffix = "\nWhere ";
			}
			aQueryString.append(mSuffix);
			aQueryString.append("(TIMESTAMP(");
			date.toQString(aQueryString, this, aCriteria);
			aQueryString.append(",");
			time.toQString(aQueryString, this, aCriteria);
			aQueryString.append(")>= TIMESTAMP(");
			aQueryString
					.append("'" + fromDate.getValue(aCriteria) + "','" + fromTime.getValue(aCriteria) + "') AND\n");
			aQueryString.append("  TIMESTAMP(");
			date.toQString(aQueryString, this, aCriteria);
			aQueryString.append(",");
			time.toQString(aQueryString, this, aCriteria);
			aQueryString.append(")<= TIMESTAMP(");
			aQueryString.append("'" + toDate.getValue(aCriteria) + "','" + toTime.getValue(aCriteria) + "'))");
		}
		return mRtnValue;
	}
}
