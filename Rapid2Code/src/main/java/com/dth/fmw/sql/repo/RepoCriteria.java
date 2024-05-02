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

package com.dth.fmw.sql.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dth.fmw.sql.common.StringUtil;

public class RepoCriteria
{
	private Map<String, List<String>> criteria = new HashMap<>();

	public RepoCriteria()
	{
		super();
	}

	public RepoCriteria(RepoCriteria aCriteria)
	{
		super();
		Set<String> mCriteriaKeys = aCriteria.criteria.keySet();
		Iterator<String> mIterator = mCriteriaKeys.iterator();
		while (mIterator.hasNext())
		{
			String mKey = mIterator.next();
			criteria.put(mKey, aCriteria.criteria.get(mKey));
		}
	}

	public List<String> getValue(String aLabel)
	{
		return criteria.get(aLabel);
	}

	public String getValueAsString(String aLabel)
	{
		String mRtnVal = null;
		List<String> mList = getValue(aLabel);
		if (mList != null && mList.size() > 0)
			mRtnVal = StringUtil.listToDelString(mList, ",");
		return mRtnVal;
	}

	public int getValueCount(String aLabel)
	{
		int mRtnVal = 0;
		List<String> aValue = getValue(aLabel);
		if (aValue != null)
			mRtnVal = aValue.size();
		return mRtnVal;
	}

	public boolean hasMultipleElements(String aLabel)
	{
		boolean mRtnVal = false;
		List<String> mList = criteria.get(aLabel);
		if (mList != null && mList.size() > 1)
			mRtnVal = true;
		return mRtnVal;
	}

	public boolean hasValue(String aLabel)
	{
		return criteria.containsKey(aLabel);
	}

	public void insert(String aLabel, String aValue)
	{
		List<String> mList = criteria.get(aLabel);
		if (mList == null)
			mList = new ArrayList<String>();
		mList.add(aValue);
		criteria.put(aLabel, mList);
	}
}
