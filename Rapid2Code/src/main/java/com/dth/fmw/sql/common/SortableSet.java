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

package com.dth.fmw.sql.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortableSet extends SortableList
{
	private Map<String, List<Object>> keys = new HashMap<>();
	public SortableSet()
	{
		super();
	}

	public SortableSet(List<Object> aList, boolean sortOn)
	{
		super(aList, sortOn);
	}

	public SortableSet(List<Object> aList, boolean sortOn, boolean ascendingOn)
	{
		super(aList, sortOn, ascendingOn);
	}

	public SortableSet(boolean sortOn)
	{
		super(sortOn);
	}

	public SortableSet(boolean sortOn, boolean ascendingOn)
	{
		super(sortOn, ascendingOn);
	}

	public void addElement(Object anObject)
	{
		put((Sortable) anObject);
	}

	private void cleanUpKeys()
	{
		keys = new HashMap<String, List<Object>>();
		for (int Lp0 = 0; Lp0 < size(); Lp0++)
		{
			Sortable mSortableObject = (Sortable) elementAt(Lp0);
			String mKey = mSortableObject.getSortKey().toString();
			List<Object> mResult = keys.get(mKey);
			if (mResult == null)
			{
				mResult = new ArrayList<Object>();
			}
			mResult.add(mSortableObject);
			keys.put(mKey, mResult);
		}
	}

	public boolean contains(Sortable aSortableObject)
	{
		return contains(aSortableObject.getSortKey());
	}

	public boolean contains(Object key)
	{
		return keys.containsKey(key);
	}

	public List<Object> get(Sortable aSortKey)
	{
		List<Object> mRtnVal = null;
		if (isSortOn())
		{
			mRtnVal = keys.get(aSortKey.getSortKey().toString());
		} else
		{
			if (contains(aSortKey))
			{
				mRtnVal = new ArrayList<Object>(1);
				mRtnVal.add(aSortKey);
			}
		}
		return mRtnVal;
	}

	public List<Object> get(Object key)
	{
		List<Object> mRtnVal = null;
		if (isSortOn())
		{
			mRtnVal = keys.get(key);
		} else
		{
			mRtnVal = slowSearch(key);
		}
		return mRtnVal;
	}

	public Object put(Sortable aSortableObject)
	{
		Object mRtnVal = null;
		if (isSortOn())
		{
			String mKey = aSortableObject.getSortKey().toString();
			List<Object> mResult = keys.get(mKey);
			if (mResult == null)
			{
				mResult = new ArrayList<Object>();
			}
			mResult.add(aSortableObject);
			mRtnVal = keys.put(mKey, mResult);
		}
		super.addElement(aSortableObject);
		return mRtnVal;
	}

	public void remove(Sortable aSortableObject)
	{
		List<Object> mList = keys.get(aSortableObject.getSortKey());
		if (mList != null && mList.size() > 1)
		{
			mList.remove(aSortableObject);
		} else
		{
			keys.remove(aSortableObject.getSortKey());
		}
		removeElement(aSortableObject);
	}

	public void remove(Object aKey)
	{
		List<Object> mList = keys.get(aKey);
		if (mList != null && mList.size() > 0)
		{
			for (int Lp0 = 0; Lp0 < mList.size(); Lp0++)
			{
				Sortable mObjectKey = (Sortable) mList.get(Lp0);
				removeElement(mObjectKey);
			}
			keys.remove(aKey);
		}
	}

	public void resort()
	{
		if (isSortOn())
		{
			super.resort();
			cleanUpKeys();
		}
	}

	private List<Object> slowSearch(Object aKey)
	{
		List<Object> mRtnVal = null;
		for (int Lp0 = 0; Lp0 < size(); Lp0++)
		{
			Sortable mSortableObject = (Sortable) elementAt(Lp0);
			Object mKey = mSortableObject.getSortKey();
			if (compareObject(aKey, mKey, true) == 0)
			{
				if (mRtnVal == null)
					mRtnVal = new ArrayList<Object>();
				mRtnVal.add(mSortableObject);
			}
		}
		return mRtnVal;
	}

	public String toString()
	{
		return super.toString();
	}
}
