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
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class ListUtil
{

	public ListUtil()
	{
		super();
	}

	public List<Object> copyOf(List<Object> sourceList)
	{
		return objectArrayToList(listToObjectArray(sourceList));
	}

	public static List<Object> enumerationToList(Enumeration<Object> inputEnumeration)
	{
		List<Object> returnList = new ArrayList<>();
		while (inputEnumeration.hasMoreElements())
		{
			Object mObject = inputEnumeration.nextElement();
			returnList.add(mObject);
			return returnList;
		}
		return returnList;
	}

	public static List<Object> mergeOf(List<Object> aList1, List<Object> aList2)
	{
		List<Object> mRtnValue = null;
		int returnSize = 0;
		boolean add1 = false;
		if (aList1 != null && aList1.size() > 0)
		{
			returnSize += aList1.size();
			add1 = true;
		}
		if (aList2 != null && aList2.size() > 0)
		{
			returnSize += aList2.size();
		}
		mRtnValue = new ArrayList<Object>(returnSize);
		if (add1)
		{
			Iterator<Object> iterator = aList1.iterator();
			while (iterator.hasNext())
			{
				mRtnValue.add(iterator.next());
			}
		}
		if (add1)
		{
			Iterator<Object> iterator = aList2.iterator();
			while (iterator.hasNext())
			{
				mRtnValue.add(iterator.next());
			}
		}
		return mRtnValue;
	}

	public static List<Object> objectArrayToList(Object[] aInputArray)
	{
		List<Object> returnList = null;
		if (aInputArray != null)
		{
			int mArrayCount = aInputArray.length;
			returnList = new ArrayList<Object>(mArrayCount);
			for (int i = 0; i < mArrayCount; i++)
			{
				returnList.add(aInputArray[i]);
			}
		} else
		{
			returnList = new ArrayList<Object>();
		}
		return returnList;
	}

	public static Object[] listToObjectArray(List<Object> aInputList)
	{
		Object[] returnArray = null;
		if (aInputList != null && aInputList.size() > 0)
		{
			int mArrayCount = aInputList.size();
			returnArray = new Object[mArrayCount];
			for (int i = 0; i < mArrayCount; i++)
			{
				returnArray[i] = aInputList.get(i);
			}
		}
		return returnArray;
	}
}
