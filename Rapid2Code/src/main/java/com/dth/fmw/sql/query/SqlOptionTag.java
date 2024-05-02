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

public class SqlOptionTag extends SqlOption
{
	private int oOptionType = 0;
	private int oDataType = 0;
	private String oLabel = "";
	private String oValue = "";
	public final static int DATA_TYPE_STRING = 10;
	public final static int DATA_TYPE_COUNT = 30;
	public final static int DATA_TYPE_INT = 20;
	public final static int LESS_THAN = 10;
	public final static int GREATER_THAN = 20;
	public final static int EQUAL_TO = 30;
	public final static int EQUAL_TO_LESS_THAN = 40;
	public final static int EQUAL_TO_GREATER_THAN = 50;
	public final static int NOT_NULL = 60;
	public final static int NOT_EQUAL_TO = 70;

	public SqlOptionTag(String aLabel, int aOperation)
	{
		oLabel = aLabel;
		oValue = "";
		oDataType = DATA_TYPE_STRING;
		oOptionType = aOperation;
	}

	public SqlOptionTag(String aLabel, int aOperation, int aDataType)
	{
		oLabel = aLabel;
		oValue = "";
		oDataType = aDataType;
		oOptionType = aOperation;
	}

	public SqlOptionTag(String aLabel, String aValue, int aOperation)
	{
		oLabel = aLabel;
		oValue = aValue;
		oDataType = DATA_TYPE_STRING;
		oOptionType = aOperation;
	}

	public SqlOptionTag(String aLabel, String aValue, int aOperation, int aDataType)
	{
		oLabel = aLabel;
		oValue = aValue;
		oDataType = aDataType;
		oOptionType = aOperation;
	}

	public boolean compareCount(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria)
	{
		boolean mRtnValue = false;
		int mValue1 = aCriteria.getValue(oLabel).size();
		int mValue2 = Integer.parseInt(oValue);
		switch (oOptionType)
		{
		case EQUAL_TO:
			mRtnValue = (mValue1 == mValue2);
			break;
		case NOT_EQUAL_TO:
			mRtnValue = (mValue1 != mValue2);
			break;
		case EQUAL_TO_GREATER_THAN:
			mRtnValue = (mValue1 >= mValue2);
			break;
		case EQUAL_TO_LESS_THAN:
			mRtnValue = (mValue1 <= mValue2);
			break;
		case GREATER_THAN:
			mRtnValue = (mValue1 > mValue2);
			break;
		case LESS_THAN:
			mRtnValue = (mValue1 < mValue2);
			break;
		}
		return mRtnValue;
	}

	public boolean compareInteger(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria)
	{
		boolean mRtnValue = false;
		String mValue = aCriteria.getValueAsString(oLabel);
		int mValue1 = Integer.parseInt(oValue);
		int mValue2 = Integer.parseInt(mValue);
		switch (oOptionType)
		{
		case EQUAL_TO:
			mRtnValue = (mValue1 == mValue2);
			break;
		case NOT_EQUAL_TO:
			mRtnValue = (mValue1 != mValue2);
			break;
		case EQUAL_TO_GREATER_THAN:
			mRtnValue = (mValue1 >= mValue2);
			break;
		case EQUAL_TO_LESS_THAN:
			mRtnValue = (mValue1 <= mValue2);
			break;
		case GREATER_THAN:
			mRtnValue = (mValue1 > mValue2);
			break;
		case LESS_THAN:
			mRtnValue = (mValue1 < mValue2);
			break;
		}
		return mRtnValue;
	}

	public boolean compareString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria)
	{
		boolean mRtnValue = false;
		String mValue = aCriteria.getValueAsString(oLabel);
		switch (oOptionType)
		{
		case EQUAL_TO:
			mRtnValue = mValue.equals(oValue);
			break;
		case NOT_EQUAL_TO:
			mRtnValue = !mValue.equals(oValue);
			break;
		case NOT_NULL:
			mRtnValue = mValue != null;
			break;
		}
		return mRtnValue;
	}

	public String getLabel()
	{
		return oLabel;
	}

	public String getValue()
	{
		return oValue;
	}

	public boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria)
	{
		boolean mRtnValue = false;
		switch (oDataType)
		{
		case DATA_TYPE_STRING:
			mRtnValue = compareString(aQueryString, aLastField, aCriteria);
			break;
		case DATA_TYPE_INT:
			mRtnValue = compareInteger(aQueryString, aLastField, aCriteria);
			break;
		case DATA_TYPE_COUNT:
			mRtnValue = compareCount(aQueryString, aLastField, aCriteria);
			break;
		}
		return mRtnValue;
	}
}
