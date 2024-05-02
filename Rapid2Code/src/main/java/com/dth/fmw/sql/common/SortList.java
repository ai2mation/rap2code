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
import java.util.Date;
import java.util.List;

public class SortList
{
	private List<Object> list = new ArrayList<Object>(20000);
	private boolean sort = true;
	private boolean ascending = true;

	public SortList()
	{
		this(null, true, true);
	}

	public SortList(List<Object> aList, boolean aSortOn)
	{
		this(aList, aSortOn, true);
	}

	public SortList(List<Object> aList, boolean aSortOn, boolean aAscendingOn)
	{
		super();
		if (aList != null)
			list = aList;
		sort = aSortOn;
		ascending = aAscendingOn;
		resort();
	}

	public SortList(boolean aSortOn)
	{
		this(null, aSortOn, true);
	}

	public SortList(boolean aSortOn, boolean aAscendingOn)
	{
		this(null, aSortOn, aAscendingOn);
	}

	public void addElement(Object aObject)
	{
		if (list.size() == 0 || !sort)
		{
			list.add(aObject);
		} else
		{
			int mCount = list.size();
			boolean mInserted = false;
			for (int Lp0 = 0; Lp0 < mCount; Lp0++)
			{
				Object mBase = list.get(Lp0);
				int mResult = compareObject(aObject, mBase, true);
				if (ascending)
				{
					if (mResult <= 0)
					{
						list.add(Lp0, aObject);
						Lp0 = mCount;
						mInserted = true;
					}
				} else
				{
					if (mResult > 0)
					{
						list.add(Lp0, aObject);
						Lp0 = mCount;
						mInserted = true;
					}
				}
			}
			if (!mInserted)
			{
				list.add(aObject);
			}
		}
	}

	private static int compareByteArray(byte[] compareObject, byte[] baseObject)
	{
		int mRtnValue = 0;
		int mCompLength = compareObject.length;
		int mBaseLength = baseObject.length;
		int mLength = mCompLength > mBaseLength ? mCompLength : mCompLength == mBaseLength ? mCompLength : mBaseLength;
		int Lp0 = 0;
		while (mRtnValue == 0 && Lp0 < mLength)
		{
			byte compObj = compareObject[Lp0];
			byte baseObj = baseObject[Lp0];
			mRtnValue = compareNumber(compObj, baseObj);
		}
		if (mRtnValue == 0 && mCompLength > mBaseLength)
		{
			mRtnValue = 1;
		} else if (mRtnValue == 0 && mCompLength < mBaseLength)
		{
			mRtnValue = -1;
		}
		return mRtnValue;
	}

	private static int compareCharArray(char[] compareObject, char[] baseObject)
	{
		int mRtnValue = 0;
		int mCompLength = compareObject.length;
		int mBaseLength = baseObject.length;
		int mLength = mCompLength > mBaseLength ? mCompLength : mCompLength == mBaseLength ? mCompLength : mBaseLength;
		int Lp0 = 0;
		while (mRtnValue == 0 && Lp0 < mLength)
		{
			String mCompObj = "" + compareObject[Lp0];
			String mBaseObj = "" + baseObject[Lp0];
			mRtnValue = compareString(mCompObj, mBaseObj);
		}
		if (mRtnValue == 0 && mCompLength > mBaseLength)
		{
			mRtnValue = 1;
		} else if (mRtnValue == 0 && mCompLength < mBaseLength)
		{
			mRtnValue = -1;
		}
		return mRtnValue;
	}

	private static int compareDate(Date compareDate, Date baseDate)
	{
		int mRtnValue = 0;
		if (compareDate != null && baseDate != null)
		{
			if (compareDate.after(baseDate))
			{
				mRtnValue = 1;
			} else if (compareDate.before(baseDate))
			{
				mRtnValue = -1;
			}
		} else
		{
			if (compareDate != null && baseDate == null)
			{
				mRtnValue = -1;
			} else if (compareDate == null && baseDate != null)
			{
				mRtnValue = 1;
			}
		}
		return mRtnValue;
	}

	private static int compareDoubleArray(double[] compareObject, double[] baseObject)
	{
		int mRtnValue = 0;
		int compLength = compareObject.length;
		int baseLength = baseObject.length;
		int mLength = compLength > baseLength ? compLength : compLength == baseLength ? compLength : baseLength;
		int Lp0 = 0;
		while (mRtnValue == 0 && Lp0 < mLength)
		{
			double compObj = compareObject[Lp0];
			double baseObj = baseObject[Lp0];
			mRtnValue = compareNumber(compObj, baseObj);
		}
		if (mRtnValue == 0 && compLength > baseLength)
		{
			mRtnValue = 1;
		} else if (mRtnValue == 0 && compLength < baseLength)
		{
			mRtnValue = -1;
		}
		return mRtnValue;
	}

	private static int compareFloatArray(float[] compareObject, float[] baseObject)
	{
		int mRtnValue = 0;
		int compLength = compareObject.length;
		int baseLength = baseObject.length;
		int mLength = compLength > baseLength ? compLength : compLength == baseLength ? compLength : baseLength;
		int Lp0 = 0;
		while (mRtnValue == 0 && Lp0 < mLength)
		{
			float compObj = compareObject[Lp0];
			float baseObj = baseObject[Lp0];
			mRtnValue = compareNumber(compObj, baseObj);
		}
		if (mRtnValue == 0 && compLength > baseLength)
		{
			mRtnValue = 1;
		} else if (mRtnValue == 0 && compLength < baseLength)
		{
			mRtnValue = -1;
		}
		return mRtnValue;
	}

	private static int compareIntArray(int[] compareObject, int[] baseObject)
	{
		int mRtnValue = 0;
		int compLength = compareObject.length;
		int baseLength = baseObject.length;
		int mLength = compLength > baseLength ? compLength : compLength == baseLength ? compLength : baseLength;
		int Lp0 = 0;
		while (mRtnValue == 0 && Lp0 < mLength)
		{
			int compObj = compareObject[Lp0];
			int baseObj = baseObject[Lp0];
			mRtnValue = compareNumber(compObj, baseObj);
			Lp0++;
		}
		if (mRtnValue == 0 && compLength > baseLength)
		{
			mRtnValue = 1;
		} else if (mRtnValue == 0 && compLength < baseLength)
		{
			mRtnValue = -1;
		}
		return mRtnValue;
	}

	private static int compareLongArray(long[] compareObject, long[] baseObject)
	{
		int mRtnValue = 0;
		int compLength = compareObject.length;
		int baseLength = baseObject.length;
		int mLength = compLength > baseLength ? compLength : compLength == baseLength ? compLength : baseLength;
		int Lp0 = 0;
		while (mRtnValue == 0 && Lp0 < mLength)
		{
			long compObj = compareObject[Lp0];
			long baseObj = baseObject[Lp0];
			mRtnValue = compareNumber(compObj, baseObj);
		}
		if (mRtnValue == 0 && compLength > baseLength)
		{
			mRtnValue = 1;
		} else if (mRtnValue == 0 && compLength < baseLength)
		{
			mRtnValue = -1;
		}
		return mRtnValue;
	}

	private static int compareNumber(double compareNumber, double baseNumber)
	{
		int mRtnValue = 0;
		if (compareNumber > baseNumber)
		{
			mRtnValue = 1;
		} else if (compareNumber < baseNumber)
		{
			mRtnValue = -1;
		}
		return mRtnValue;
	}

	private static int compareObject(Object compareObject, Object baseObject)
	{
		// comparison is solely done based on hashcode
		int mRtnValue = 0;
		if (compareObject != null && baseObject != null)
		{
			if (compareObject.hashCode() > baseObject.hashCode())
			{
				mRtnValue = 1;
			} else if (compareObject.hashCode() < baseObject.hashCode())
			{
				mRtnValue = -1;
			}
		} else
		{
			if (compareObject != null && baseObject == null)
			{
				mRtnValue = -1;
			} else if (compareObject == null && baseObject != null)
			{
				mRtnValue = 1;
			}
		}
		return mRtnValue;
	}

	@SuppressWarnings("unchecked")
	protected static int compareObject(Object compareObject, Object baseObject, boolean checkType)
	{
		int mRtnValue = 0;
		if (checkType)
		{
			if (baseObject != null)
			{
				if (baseObject instanceof List && compareObject instanceof List)
					mRtnValue = compareList((List<Object>) compareObject, (List<Object>) baseObject);
				else if (baseObject instanceof String && compareObject instanceof String)
					mRtnValue = compareString((String) compareObject, (String) baseObject);
				else if (baseObject instanceof Number && compareObject instanceof Number)
					mRtnValue = compareNumber(((Integer) compareObject).doubleValue(),
							((Integer) baseObject).doubleValue());
				else if (baseObject instanceof Date && compareObject instanceof Date)
					mRtnValue = compareDate((Date) compareObject, (Date) baseObject);
				else if (baseObject instanceof java.sql.Timestamp && compareObject instanceof java.sql.Timestamp)
					mRtnValue = compareTimestamp((java.sql.Timestamp) compareObject, (java.sql.Timestamp) baseObject);
				else if (baseObject instanceof StringBuffer && compareObject instanceof StringBuffer)
					mRtnValue = compareString(compareObject.toString(), baseObject.toString());
				else if (baseObject instanceof int[] && compareObject instanceof int[])
					mRtnValue = compareIntArray((int[]) compareObject, (int[]) baseObject);
				else if (baseObject instanceof long[] && compareObject instanceof long[])
					mRtnValue = compareLongArray((long[]) compareObject, (long[]) baseObject);
				else if (baseObject instanceof float[] && compareObject instanceof float[])
					mRtnValue = compareFloatArray((float[]) compareObject, (float[]) baseObject);
				else if (baseObject instanceof double[] && compareObject instanceof double[])
					mRtnValue = compareDoubleArray((double[]) compareObject, (double[]) baseObject);
				else if (baseObject instanceof byte[] && compareObject instanceof byte[])
					mRtnValue = compareByteArray((byte[]) compareObject, (byte[]) baseObject);
				else if (baseObject instanceof char[] && compareObject instanceof char[])
					mRtnValue = compareCharArray((char[]) compareObject, (char[]) baseObject);
				else if (baseObject instanceof Object[] && compareObject instanceof Object[])
					mRtnValue = compareObjectArray((Object[]) compareObject, (Object[]) baseObject);
				else
				{
					mRtnValue = compareObject(compareObject, baseObject);
				}
			} else
			{
				mRtnValue = compareObject(compareObject, baseObject);
			}
		} else
		{
			mRtnValue = compareObject(compareObject, baseObject);
		}

		return mRtnValue;
	}

	private static int compareObjectArray(Object[] compareObject, Object[] baseObject)
	{
		int mRtnValue = 0;
		if (compareObject != null && baseObject != null)
		{
			int compLength = compareObject.length;
			int baseLength = baseObject.length;
			int mLength = compLength > baseLength ? compLength : compLength == baseLength ? compLength : baseLength;
			int Lp0 = 0;
			while (mRtnValue == 0 && Lp0 < mLength)
			{
				Object compObj = compareObject[Lp0];
				Object baseObj = baseObject[Lp0];
				mRtnValue = compareObject(compObj, baseObj, true);
			}
			if (mRtnValue == 0 && compLength > baseLength)
			{
				mRtnValue = 1;
			} else if (mRtnValue == 0 && compLength < baseLength)
			{
				mRtnValue = -1;
			}
		} else
		{
			if (compareObject != null && baseObject == null)
			{
				mRtnValue = -1;
			} else if (compareObject == null && baseObject != null)
			{
				mRtnValue = 1;
			}
		}
		return mRtnValue;
	}

	protected static int compareString(String compareString, String baseString)
	{
		int mRtnValue = 0;
		if (compareString != null && baseString != null)
		{
			mRtnValue = compareString.compareTo(baseString);
		} else
		{
			if (compareString != null && baseString == null)
			{
				mRtnValue = -1;
			} else if (compareString == null && baseString != null)
			{
				mRtnValue = 1;
			}
		}
		return mRtnValue;
	}

	private static int compareTimestamp(java.sql.Timestamp compareTimestamp, java.sql.Timestamp baseTimestamp)
	{
		int mRtnValue = 0;
		if (compareTimestamp != null && baseTimestamp != null)
		{
			if (compareTimestamp.after(baseTimestamp))
			{
				mRtnValue = 1;
			} else if (compareTimestamp.before(baseTimestamp))
			{
				mRtnValue = -1;
			}
		} else
		{
			if (compareTimestamp != null && baseTimestamp == null)
			{
				mRtnValue = -1;
			} else if (compareTimestamp == null && baseTimestamp != null)
			{
				mRtnValue = 1;
			}
		}
		return mRtnValue;
	}

	private static int compareList(List<Object> compareObject, List<Object> baseObject)
	{
		int mRtnValue = 0;
		if (baseObject != null && compareObject != null && baseObject.size() == compareObject.size())
		{
			int Lp0 = 0;
			while (mRtnValue == 0 && Lp0 < baseObject.size())
			{
				Object compObj = compareObject.get(Lp0);
				Object baseObj = baseObject.get(Lp0);
				mRtnValue = compareObject(compObj, baseObj, true);
				Lp0++;
			}
		} else
		{
			mRtnValue = compareObject(compareObject, baseObject);
		}
		return mRtnValue;
	}

	public boolean contains(Object elem)
	{
		return list.contains(elem);
	}

	public Object elementAt(int index)
	{
		return list.get(index);
	}

	public Object firstElement()
	{
		return list.get(0);
	}

	protected List<Object> getList()
	{
		return list;
	}

	public int indexOf(Object elem)
	{
		return list.indexOf(elem);
	}

	public int indexOf(Object elem, int index)
	{
		int mRtnValue = -1;
		for (int Lp0 = index; Lp0 < list.size(); Lp0++)
		{
			Object mObject = list.get(Lp0);
			if (mObject.equals(elem))
			{
				mRtnValue = Lp0;
				break;
			}
		}
		return mRtnValue;
	}

	public boolean isAscending()
	{
		return ascending;
	}

	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	public boolean isSortOn()
	{
		return sort;
	}

	public Object lastElement()
	{
		return list.get(list.size() - 1);
	}

	public int lastIndexOf(Object elem)
	{
		int mRtnValue = -1;
		for (int Lp0 = 0; Lp0 < list.size(); Lp0++)
		{
			Object mObject = list.get(Lp0);
			if (mObject.equals(elem))
			{
				mRtnValue = Lp0;
			}
		}
		return mRtnValue;
	}

	public int lastIndexOf(Object elem, int index)
	{
		int mRtnValue = -1;
		for (int Lp0 = index; Lp0 < list.size(); Lp0++)
		{
			Object mObject = list.get(Lp0);
			if (mObject.equals(elem))
			{
				mRtnValue = Lp0;
			}
		}
		return mRtnValue;
	}

	public void removeAllElements()
	{
		list = new ArrayList<Object>();
	}

	public boolean removeElement(Object obj)
	{
		return list.remove(obj);
	}

	public void removeElementAt(int index)
	{
		list.remove(index);
	}

	public void resort()
	{
		if (isSortOn())
		{
			List<Object> newList = list;
			int mSize = newList.size();
			list = new ArrayList<Object>(mSize);
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				addElement(newList.get(Lp0));
			}
		}
	}

	public void setAscending(boolean ascendingOn)
	{
		if (ascending != ascendingOn)
		{
			ascending = ascendingOn;
			resort();
		}
	}

	public void setSort(boolean sortOn)
	{
		if (sort != sortOn)
		{
			sort = sortOn;
			resort();
		}
	}

	public int size()
	{
		return list.size();
	}

	public String toString()
	{
		return list.toString();
	}
}
