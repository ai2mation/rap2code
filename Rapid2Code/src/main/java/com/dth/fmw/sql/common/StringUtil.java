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

public class StringUtil
{

	public StringUtil()
	{
		super();
	}

	public static int compareDate(Date compareDate, Date baseDate)
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
				mRtnValue = 1;
			} else if (compareDate == null && baseDate != null)
			{
				mRtnValue = -1;
			}
		}
		return mRtnValue;
	}

	public static int compareNumber(double compareNumber, double baseNumber)
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

	public static int compareObject(Object compareObject, Object baseObject)
	{
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
				mRtnValue = 1;
			} else if (compareObject == null && baseObject != null)
			{
				mRtnValue = -1;
			}
		}
		return mRtnValue;
	}

	public static int compareObject(Object compareObject, Object baseObject, boolean checkType)
	{
		int mRtnValue = 0;
		if (checkType)
		{
			if (baseObject != null)
			{
				if (baseObject instanceof String)
					mRtnValue = compareString((String) compareObject, (String) baseObject);
				else if (baseObject instanceof Number)
					mRtnValue = compareNumber(((Integer) compareObject).doubleValue(),
							((Integer) baseObject).doubleValue());
				else if (baseObject instanceof Date)
					mRtnValue = compareDate((Date) compareObject, (Date) baseObject);
				else if (baseObject instanceof java.sql.Timestamp)
					mRtnValue = compareTimestamp((java.sql.Timestamp) compareObject, (java.sql.Timestamp) baseObject);
				else if (baseObject instanceof StringBuffer)
					mRtnValue = compareString(compareObject.toString(), baseObject.toString());
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

	public static int compareString(String compareString, String baseString)
	{
		int mRtnValue = 0;
		if (compareString != null && baseString != null)
		{
			mRtnValue = compareString.compareTo(baseString);
		} else
		{
			if (compareString != null && baseString == null)
			{
				mRtnValue = 1;
			} else if (compareString == null && baseString != null)
			{
				mRtnValue = -1;
			}
		}
		return mRtnValue;
	}

	public static int compareTimestamp(java.sql.Timestamp compareTimestamp, java.sql.Timestamp baseTimestamp)
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
				mRtnValue = 1;
			} else if (compareTimestamp == null && baseTimestamp != null)
			{
				mRtnValue = -1;
			}
		}
		return mRtnValue;
	}

	public static String[] parseLineToStringArray(String inputLine, String delimiter)
	{
		return parseListToStringArray(parseLineToList(inputLine, delimiter));

	}

	public static List<String> parseLineToList(String aInputLine, String aDelimiter)
	{
		List<String> mList = new ArrayList<>();
		int mIndex = 0;
		if (aInputLine != null && aInputLine.length() > 0 && aDelimiter != null && aDelimiter.length() > 0)
		{
			int mInputLength = aInputLine.length();
			int mDelLen = aDelimiter.length();
			int mStopLen = aInputLine.indexOf(aDelimiter);
			while (mStopLen > -1)
			{
				mList.add(aInputLine.substring(mIndex, mStopLen).trim());
				mIndex = mStopLen + mDelLen;
				String mSubString = aInputLine.substring(mIndex);
				aInputLine = mSubString;
				mInputLength = aInputLine.length();
				mIndex = 0;
				mStopLen = aInputLine.indexOf(aDelimiter);
			}
			if (mStopLen == -1 && mInputLength > 0)
			{
				mList.add(aInputLine.trim());
			}

		}
		return mList;
	}

	public static String[] parseListToStringArray(List<String> aList)
	{
		String[] mRtnValue = null;
		if (aList != null && aList.size() > 0)
		{
			int mListSize = aList.size();
			mRtnValue = new String[mListSize];
			for (int Lp0 = 0; Lp0 < mListSize; Lp0++)
			{
				mRtnValue[Lp0] = aList.get(Lp0);
			}
		}
		return mRtnValue;
	}

	public static List<String> sortStringList(List<String> aInputList)
	{
		List<String> mRtnValue = null;
		if (aInputList != null && aInputList.size() > 0)
		{
			mRtnValue = new ArrayList<>(aInputList.size());
			int mSize = aInputList.size();
			mRtnValue.add(aInputList.get(0));
			for (int Lp0 = 1; Lp0 < mSize; Lp0++)
			{
				int mSize2 = mRtnValue.size();
				String mInputKey = (String) aInputList.get(Lp0);
				boolean mInserted = false;
				for (int Lp1 = 0; Lp1 < mSize2; Lp1++)
				{
					String mKey = (String) mRtnValue.get(Lp1);
					int mResult = compareString(mInputKey, mKey);
					if (mResult <= 0)
					{
						mRtnValue.add(Lp1, mInputKey);
						Lp1 = mSize2;
						mInserted = true;
					}
				}
				if (!mInserted)
				{
					mRtnValue.add(mInputKey);
				}
			}
		}
		return mRtnValue;
	}

	public static String stringArrayToDelString(String[] aInputArray, String aDelimiter)
	{
		StringBuffer returnString = new StringBuffer();
		if (aInputArray != null)
		{
			int mSize = aInputArray.length;
			// Loop through the inputArray and add each element as a member of the delimited
			// String.
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				returnString.append(aInputArray[Lp0]);
				if (!(Lp0 == (mSize - 1)))
				{
					returnString.append(aDelimiter);
				}
			}
		}
		return returnString.toString();
	}

	public static List<String> stringArrayToList(String[] aInputArray)
	{
		List<String> mRtnValue = null;
		if (aInputArray != null)
		{
			int mSize = aInputArray.length;
			mRtnValue = new ArrayList<String>(mSize);
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				mRtnValue.add(aInputArray[Lp0]);
			}
		} else
		{
			mRtnValue = new ArrayList<>(1);
		}
		return mRtnValue;
	}

	public static String swapDelimiter(String aInputLine, String aOldDelimiter, String aNewDelimiter)
	{
		boolean delimiterFound = false;
		StringBuilder mRtnValue = new StringBuilder();
		List<String> mList = new ArrayList<>();
		int index = 0;
		// parse the delimited file, using 'the value of delimiter' as the delimiter.
		if (aInputLine != null && aInputLine.length() > 0 && aOldDelimiter != null && aOldDelimiter.length() > 0)
		{
			int mLen = aInputLine.length();
			int mOldDelLen = aOldDelimiter.length();
			int mStopLength = aInputLine.indexOf(aOldDelimiter);
			while (mStopLength > -1)
			{
				mList.add(aInputLine.substring(index, mStopLength));
				delimiterFound = true;
				index = mStopLength + mOldDelLen;
				String mRemainder = aInputLine.substring(index);
				aInputLine = mRemainder;
				mLen = aInputLine.length();
				index = 0;
				mStopLength = aInputLine.indexOf(aOldDelimiter);
			}
			if (mStopLength == -1 && mLen > 0)
			{
				mList.add(aInputLine);
			}
			int mListSize = mList.size();
			for (int Lp0 = 0; Lp0 < mListSize; Lp0++)
			{
				mRtnValue.append((String) mList.get(Lp0));
				if (!(Lp0 == (mListSize - 1)) || (mListSize == 1 && delimiterFound))
				{
					mRtnValue.append(aNewDelimiter);
				}
			}
		}
		return mRtnValue.toString();
	}

	public static String listToDelString(List<String> inputList, String delimiter)
	{
		return listToDelString(inputList, delimiter, null, null);
	}

	public static String listObjectToDelString(List<Object> inputList, String delimiter)
	{
		List<String> mListOfStrings = new ArrayList<>();
		for (Object mObject : inputList)
		{
			mListOfStrings.add(mObject.toString());
		}
		return listToDelString(mListOfStrings, delimiter, null, null);
	}

	public static String listToDelString(List<String> aInputList, String aDelimiter, String aPrefix, String aSuffix)
	{
		String mPrefix = "";
		String mSuffix = "";
		if (aPrefix != null)
			mPrefix = aPrefix;
		if (aSuffix != null)
			mSuffix = aSuffix;
		StringBuilder mBuilder = new StringBuilder();
		if (aInputList != null)
		{
			int mSize = aInputList.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				mBuilder.append(mPrefix);
				mBuilder.append(aInputList.get(Lp0));
				mBuilder.append(mSuffix);
				if (!(Lp0 == (mSize - 1)))
				{
					mBuilder.append(aDelimiter);
				}
			}
		}
		return mBuilder.toString();
	}
}
