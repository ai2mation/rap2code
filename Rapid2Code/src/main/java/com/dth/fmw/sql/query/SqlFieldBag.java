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

import com.dth.fmw.sql.common.StringUtil;
import com.dth.fmw.sql.database.SqlDbColumn;
import com.dth.fmw.sql.repo.RepoCriteria;

public class SqlFieldBag extends SqlField
{
	private List<String> elements = new ArrayList<>();
	private boolean useSingleQuotes = false;
	private List<String> tags = new ArrayList<>();
	private boolean processIfEmpty = false;

	public SqlFieldBag()
	{
		super(null, null, null, null);
	}

	public SqlFieldBag(SqlFrom aSqlFrom, SqlDbColumn aColumn, String aAsColumn, String aName)
	{
		super(aSqlFrom, aColumn, aAsColumn, aName);
	}

	public SqlFieldBag(SqlFrom aSqlFrom, SqlDbColumn aColumn, String aAsColumn, String aName, int aReformatCode)
	{
		super(aSqlFrom, aColumn, aAsColumn, aName);
		setReformatCode(aReformatCode);
	}

	public SqlFieldBag(SqlFrom aSqlFrom, SqlDbColumn aColumn, String aAsColumn, String aName, List<String> aTags)
	{
		super(aSqlFrom, aColumn, aAsColumn, aName);
		tags = aTags;
	}

	public SqlFieldBag(SqlFrom aSqlFrom, SqlDbColumn aColumn, String aAsColumn, String aName, List<String> aTags,
			int aReformatCode)
	{
		super(aSqlFrom, aColumn, aAsColumn, aName);
		tags = aTags;
		setReformatCode(aReformatCode);
	}

	public SqlFieldBag(String aTag)
	{
		super(null, null, null, null);
		tags.add(aTag);
	}

	public SqlFieldBag(List<String> aTags)
	{
		super(null, null, null, null);
		tags = aTags;
	}

	public SqlFieldBag(List<String> aTags, boolean aProcessIfEmpty)
	{
		super(null, null, null, null);
		processIfEmpty = aProcessIfEmpty;
		tags = aTags;
	}

	public void addValue(String aValue)
	{
		elements.add(aValue);
	}

	public boolean isSelectable()
	{
		return false;
	}

	public boolean toQString(StringBuffer aQueryString, SqlQBase aLastField, RepoCriteria aCriteria)
	{
		List<String> mElements = new ArrayList<>();
		int mSize = elements.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			mElements.add(elements.get(Lp0));
		}
		mSize = tags.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			String mLabel = (String) tags.get(Lp0);
			List<String> mCriteriaValue = aCriteria.getValue(mLabel);
			String mValue = null;
			if (useSingleQuotes)
			{
				mValue = StringUtil.listToDelString(mCriteriaValue, ",", "'", "'");
			} else
			{
				mValue = StringUtil.listToDelString(mCriteriaValue, ",");
			}
			if (mValue.length() > 0)
				mElements.add(mValue);
		}
		boolean mProcess = processIfEmpty;
		if (mElements.size() > 0)
			mProcess = true;
		if (mProcess)
		{
			aQueryString.append("(");
			aQueryString.append(StringUtil.listToDelString(mElements, ","));
			aQueryString.append(")");
		}
		return mProcess;
	}

	public SqlFieldBag useSingleQoutes()
	{
		useSingleQuotes = true;
		return this;
	}
}
