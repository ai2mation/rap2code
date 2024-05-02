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

import com.dth.fmw.sql.repo.RepoCriteria;

public class SqlOptional extends SqlQBase
{
	protected List<SqlQBase> options = new ArrayList<>();
	protected List<SqlQBase> elements = new ArrayList<>();
	protected List<SqlQBase> alternate = new ArrayList<>();

	public SqlOptional()
	{
		super();
	}

	public void insertAlternate(SqlQBase aElement)
	{
		alternate.add(aElement);
	}

	public void insertElement(SqlQBase aElement)
	{
		elements.add(aElement);
	}

	public void insertOption(SqlQBase aOption)
	{
		options.add(aOption);
	}

	public boolean isOptional()
	{
		return true;
	}

	public String queryType()
	{
		return SqlOptional.class.toString();
	}

	public boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria)
	{
		boolean mRtnValue = false;
		boolean mResults = true;
		int mSize = options.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlQBase mOption = (SqlQBase) options.get(Lp0);
			mResults = mOption.toQString(aQueryString, aLastField, aCriteria);
			if (!mResults)
				Lp0 = mSize;
		}
		if (mResults)
		{
			mSize = elements.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				SqlQBase mQueryObj = (SqlQBase) elements.get(Lp0);
				mResults = mQueryObj.toQString(aQueryString, aLastField, aCriteria);
				if (mResults)
				{
					aLastField = mQueryObj;
					mRtnValue = true;
				}
			}
		} else
		{
			mSize = alternate.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				SqlQBase mQueryObj = (SqlQBase) alternate.get(Lp0);
				mResults = mQueryObj.toQString(aQueryString, aLastField, aCriteria);
				if (mResults)
				{
					aLastField = mQueryObj;
					mRtnValue = true;
				}
			}
		}
		return mRtnValue;
	}

	public boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria, String aQualifier)
	{
		boolean mRtnValue = false;
		boolean mResults = true;
		int mSize = options.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlQBase mOption = (SqlQBase) options.get(Lp0);
			mResults = mOption.toQString(aQueryString, aLastField, aCriteria);
			if (!mResults)
				Lp0 = mSize;
		}
		if (mResults)
		{
			mSize = elements.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				SqlQBase mQueryObj = (SqlQBase) elements.get(Lp0);
				mResults = toQStringWithQual(aQueryString, mQueryObj, aLastField, aCriteria, aQualifier);
				if (mResults)
				{
					aLastField = mQueryObj;
					mRtnValue = true;
				}
			}
		} else
		{
			mSize = alternate.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				SqlQBase mQueryObj = (SqlQBase) alternate.get(Lp0);
				mResults = toQStringWithQual(aQueryString, mQueryObj, aLastField, aCriteria, aQualifier);
				if (mResults)
				{
					aLastField = mQueryObj;
					mRtnValue = true;
				}
			}
		}
		return mRtnValue;
	}

	public boolean toQStringWithQual(StringBuffer aQueryString, SqlQBase aQueryObj, SqlQBase aLastField,
			RepoCriteria aCriteria, String aQualifier)
	{
		boolean mRtnValue = false;
		if (aQueryObj.queryFullType().equals(SqlFromSelectQualifier.class.toString()))
		{
			SqlFromSelectQualifier mFrom = (SqlFromSelectQualifier) aQueryObj;
			mRtnValue = mFrom.toQString(aQueryString, aLastField, aCriteria, aQualifier);
		} else if (aQueryObj.queryFullType().equals(SqlFieldQualifier.class.toString()))
		{
			SqlFieldQualifier mFieldQual = (SqlFieldQualifier) aQueryObj;
			mRtnValue = mFieldQual.toQString(aQueryString, aLastField, aCriteria, aQualifier);
		} else if (aQueryObj.queryFullType().equals(SqlFromGroupQual.class.toString()))
		{
			SqlFromGroupQual mFromGroupQual = (SqlFromGroupQual) aQueryObj;
			mRtnValue = mFromGroupQual.toQString(aQueryString, aLastField, aCriteria, aQualifier);
		} else if (aQueryObj.queryFullType().equals(SqlGroupQual.class.toString()))
		{
			SqlGroupQual mGroupQual = (SqlGroupQual) aQueryObj;
			mRtnValue = mGroupQual.toQString(aQueryString, aLastField, aCriteria, aQualifier);
		} else if (aQueryObj.isOptional())
		{
			SqlOptional mOptional = (SqlOptional) aQueryObj;
			mRtnValue = mOptional.toQString(aQueryString, aLastField, aCriteria, aQualifier);
		} else if (aQueryObj.queryType().equals(SqlWhere.class.toString()))
		{
			SqlWhere mWhere = (SqlWhere) aQueryObj;
			mRtnValue = mWhere.toQString(aQueryString, aLastField, aCriteria, aQualifier);
		} else
		{
			mRtnValue = aQueryObj.toQString(aQueryString, aLastField, aCriteria);
		}
		return mRtnValue;
	}
}
