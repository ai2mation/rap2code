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

public class SqlGroupQual extends SqlGroup
{

	public SqlGroupQual()
	{
		super();
	}

	public String queryFullType()
	{
		return SqlGroupQual.class.toString();
	}

	public boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria, String aQualifier)
	{
		boolean mRtnValue = true;
		boolean mResult = false;
		aQueryString.append("(");
		int mSize = oElements.size();
		SqlQBase mLastField = null;
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlQBase mQueryObj = (SqlQBase) oElements.get(Lp0);

			if (mQueryObj.queryFullType().equals(SqlFromSelectQualifier.class.toString()))
			{
				SqlFromSelectQualifier mFrom = (SqlFromSelectQualifier) mQueryObj;
				mResult = mFrom.toQString(aQueryString, mLastField, aCriteria, aQualifier);
			} else if (mQueryObj.queryFullType().equals(SqlFieldQualifier.class.toString()))
			{
				SqlFieldQualifier mFieldQual = (SqlFieldQualifier) mQueryObj;
				mResult = mFieldQual.toQString(aQueryString, mLastField, aCriteria, aQualifier);
			} else if (mQueryObj.queryFullType().equals(SqlFromGroupQual.class.toString()))
			{
				SqlFromGroupQual mFromGroupQual = (SqlFromGroupQual) mQueryObj;
				mResult = mFromGroupQual.toQString(aQueryString, mLastField, aCriteria, aQualifier);
			} else if (mQueryObj.isOptional())
			{
				SqlOptional mOptional = (SqlOptional) mQueryObj;
				mResult = mOptional.toQString(aQueryString, mLastField, aCriteria, aQualifier);
			} else
			{
				mResult = mQueryObj.toQString(aQueryString, mLastField, aCriteria);
			}
			if (mResult)
				mLastField = mQueryObj;
		}
		aQueryString.append(")");
		return mRtnValue;
	}
}
