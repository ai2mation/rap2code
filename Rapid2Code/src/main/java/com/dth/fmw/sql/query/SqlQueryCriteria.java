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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dth.fmw.sql.common.DateUtility;
import com.dth.fmw.sql.common.StringUtil;
import com.dth.fmw.sql.repo.RepoCriteria;

public class SqlQueryCriteria extends SqlQuery
{
	private Map<String, List<Object>> oFilters = new HashMap<>();
	private Map<String, List<Object>> oReformatCodes = new HashMap<>();
	public static final int FIRST_DAY_OF_MONTH = 1001;
	public static final int FIRST_DAY_OF_NEXT_MONTH = 1002;
	public static final int LAST_DAY_OF_MONTH = 1003;
	private boolean oStopIfNotFound = false;

	public SqlQueryCriteria(String aLogicalName)
	{
		super(aLogicalName);
	}

	public SqlQueryCriteria(String aLogicalName, String aLabel, String aFilter)
	{
		super(aLogicalName);
		insertFilter(aLabel, aFilter);
	}

	public SqlQueryCriteria(String aLogicalName, String aLabel, String aFilter, int aReformatCode)
	{
		super(aLogicalName);
		insertFilter(aLabel, aFilter, aReformatCode);
	}

	protected void buildQuery()
	{
	}

	public String getQuery(RepoCriteria aCriteria, SqlResults aResults)
	{
		int mOrder = -1;
		boolean mFoundElement = false;
		Set<String> eFilters = oFilters.keySet();
		try
		{
			Iterator<String> mIterator = eFilters.iterator();
			while (mIterator.hasNext())
			{
				mFoundElement = false;
				String mLabel = (String) mIterator.next();
				String mFilter = StringUtil.listObjectToDelString(oFilters.get(mLabel), "");
				mOrder = aResults.getColNameIndex(mFilter);
				SqlResultRow mRow = aResults.getFirstRow();
				while (mRow != null)
				{
					SqlResultColumn mColumn = mRow.getColumnAt(mOrder);
					String mData = mColumn.getData();
					if (mData != null)
					{
						List<Object> mReformatCodes = oReformatCodes.get(mLabel);
						if (mReformatCodes != null)
						{
							int mCode = Integer.parseInt(StringUtil.listObjectToDelString(mReformatCodes, "+"));
							mData = reformatData(mData, mCode);
						}
						aCriteria.insert(mLabel, mData);
						mFoundElement = true;
					}
					mRow = aResults.getNextRow();
				}

			}
		} catch (Exception aException)
		{
			//Exception mException = aException;
		}
		String mRtnValue = "setting criteria";
		if (!mFoundElement && oStopIfNotFound)
			mRtnValue = "stop processing";
		return mRtnValue;
	}

	public String getUserID(RepoCriteria aCriteria)
	{
		return null;
	}

	public void insertFilter(String aLabel, String aFilter)
	{
		List<Object> mFilters = oFilters.get(aLabel);
		if (mFilters == null)
			mFilters = new ArrayList<>();
		mFilters.add(aFilter);
		oFilters.put(aLabel, mFilters);
	}

	public void insertFilter(String aLabel, String aFilter, int aReformatCode)
	{
		insertFilter(aLabel, aFilter);
		if (aReformatCode != 0)
		{
			List<Object> mReformatCodes = oReformatCodes.get(aLabel);
			if (mReformatCodes == null)
				mReformatCodes = new ArrayList<>();
			String mReformatCode = Integer.toString(aReformatCode);
			mReformatCodes.add(mReformatCode);
			oReformatCodes.put(aLabel, mReformatCodes);
		}
	}

	protected String reformatData(String aValue, int aCode)
	{
		String mRtnValue = aValue;
		switch (aCode)
		{
		case FIRST_DAY_OF_MONTH:
			mRtnValue = DateUtility.getFirstDayMonth(aValue);
			break;
		case FIRST_DAY_OF_NEXT_MONTH:
			mRtnValue = DateUtility.getFirstDayNextMonth(aValue);
			break;
		case LAST_DAY_OF_MONTH:
			mRtnValue = DateUtility.getLastDayMonth(aValue);
			break;
		}
		return mRtnValue;
	}

	public SqlQueryCriteria setStopIfNotFound(boolean aValue)
	{
		oStopIfNotFound = aValue;
		return this;
	}

	public boolean stopIfNotFound()
	{
		return oStopIfNotFound;
	}
}
