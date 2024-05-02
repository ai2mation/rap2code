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

public class SqlOptionalWhere extends SqlOptional
{

	public String getSuffix(RepoCriteria aCriteria)
	{
		String mRtnValue = "";
		boolean mResult2 = false;
		boolean mResult3 = false;
		boolean mResults = true;
		SqlQBase mLastField = null;
		int mSize = options.size();
		StringBuffer mQueryString = new StringBuffer();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlQBase mOption = (SqlQBase) options.get(Lp0);
			mResults = mOption.toQString(mQueryString, mLastField, aCriteria);
			if (!mResults)
				Lp0 = mSize;
		}
		if (mResults)
		{
			mSize = elements.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				SqlQBase mQueryObj = (SqlQBase) elements.get(Lp0);
				mResult3 = mQueryObj.toQString(mQueryString, mLastField, aCriteria);
				if (mResult3)
				{
					mLastField = mQueryObj;
					mResult2 = true;
				}
			}
		} else
		{
			mSize = alternate.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				SqlQBase mQueryObj = (SqlQBase) alternate.get(Lp0);
				mResult3 = mQueryObj.toQString(mQueryString, mLastField, aCriteria);
				if (mResult3)
				{
					mLastField = mQueryObj;
					mResult2 = true;
				}
			}
		}
		if (mResult2)
		{
			if (mLastField.isQueryType(SqlWhere.class.toString()))
			{
				if (mLastField.queryFullType().equals(SqlOptionalWhere.class.toString()))
				{
					mRtnValue = ((SqlOptionalWhere) mLastField).getSuffix(aCriteria);
				} else if (mLastField.queryType().equals(SqlWhere.class.toString()))
				{
					mRtnValue = ((SqlWhere) mLastField).getSuffix();
				}
			}
		}
		return mRtnValue;
	}

	public String getSuffix(SqlQBase aLastField, RepoCriteria aCriteria)
	{
		String mRtnValue = "";
		boolean mResult2 = false;
		boolean mResult3 = false;
		boolean mResults = true;
		int mSize = options.size();
		StringBuffer mQueryString = new StringBuffer();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlQBase mOption = (SqlQBase) options.get(Lp0);
			mResults = mOption.toQString(mQueryString, aLastField, aCriteria);
			if (!mResults)
				Lp0 = mSize;
		}
		if (mResults)
		{
			mSize = elements.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				SqlQBase mQueryObj = (SqlQBase) elements.get(Lp0);
				mResult3 = mQueryObj.toQString(mQueryString, aLastField, aCriteria);
				if (mResult3)
				{
					aLastField = mQueryObj;
					mResult2 = true;
				}
			}
		} else
		{
			mSize = alternate.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				SqlQBase mQueryObj = (SqlQBase) alternate.get(Lp0);
				mResult3 = mQueryObj.toQString(mQueryString, aLastField, aCriteria);
				if (mResult3)
				{
					aLastField = mQueryObj;
					mResult2 = true;
				}
			}
		}
		if (mResult2)
		{
			if (aLastField.isQueryType(SqlWhere.class.toString()))
			{
				if (aLastField.queryFullType().equals(SqlOptionalWhere.class.toString()))
				{
					mRtnValue = ((SqlOptionalWhere) aLastField).getSuffix(aCriteria);
				} else if (aLastField.queryType().equals(SqlWhere.class.toString()))
				{
					mRtnValue = ((SqlWhere) aLastField).getSuffix();
				}
			}
		}
		return mRtnValue;
	}

	public String queryFullType()
	{
		return SqlOptionalWhere.class.toString();
	}

	public String queryType()
	{
		return SqlWhere.class.toString();
	}
}
