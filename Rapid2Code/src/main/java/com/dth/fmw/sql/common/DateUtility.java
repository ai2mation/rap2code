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

import java.text.FieldPosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtility
{
	public DateUtility()
	{
		super();
	}

	public static java.sql.Date add(java.sql.Date aDate, int aField, int aAmount)
	{

		java.util.Date tempDate = (java.util.Date) aDate;
		java.util.Date uDate = add(tempDate, aField, aAmount);
		return new java.sql.Date(uDate.getTime());
	}

	public static java.util.Date add(java.util.Date date, int field, int amount)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();

	}

	public static int calculateNumberOfDays(java.sql.Date fromDate, java.sql.Date toDate)
	{
		int mRtnValue = 0;
		if (fromDate == null || toDate == null)
		{
		} else
		{
			if (fromDate.after(toDate))
			{
				mRtnValue = 0;
			} else if (fromDate.equals(toDate))
			{
				// return 0 if dates are equal coming in
			} else
			{
				java.util.GregorianCalendar fromGc = new java.util.GregorianCalendar();
				java.util.GregorianCalendar toGc = new java.util.GregorianCalendar();
				fromGc.setTime(fromDate);
				toGc.setTime(toDate);
				while (fromGc.getTime().before(toGc.getTime()))
				{
					++mRtnValue;
					fromGc.add(Calendar.DATE, 1);
				}
				++mRtnValue;
			}
		}
		return mRtnValue;
	}

	public static String formatSimpleDateString(String dateStr, String aFormat)
	{
		String mRtnVal = "";
		java.util.Date tempDate = validUtilDate(dateStr);
		if (tempDate != null)
			mRtnVal = formatSimpleDateString(tempDate, aFormat);
		return mRtnVal;
	}

	public static String formatSimpleDateString(java.sql.Date aDate, String aFormat)
	{
		java.util.Date mDate = new java.util.Date(aDate.getTime());
		return formatSimpleDateString(mDate, aFormat);
	}

	public static String formatSimpleDateString(java.util.Date aDate, String aFormat)
	{
		String mRtnVal = "";
		StringBuffer mFormatedDate = new StringBuffer();
		if (aDate != null)
		{
			try
			{
				java.text.DateFormat df = new java.text.SimpleDateFormat(aFormat);
				df.setLenient(false);
				df.format(aDate, mFormatedDate, new FieldPosition(0));
			} catch (Exception iae)
			{
				iae.printStackTrace();
				mFormatedDate = new StringBuffer();
			}
		}
		if (mFormatedDate.length() > 0)
		{
			mRtnVal = mFormatedDate.toString();
		}
		return mRtnVal;
	}

	public static String formatTimeStampString(String timeStampStr, String aFormat)
	{
		String mRtnVal = "";
		java.sql.Timestamp tempDate = validTimeStamp(timeStampStr);
		if (tempDate != null)
			mRtnVal = formatTimeStampString(tempDate, aFormat);
		return mRtnVal;
	}

	public static String formatTimeStampString(java.sql.Timestamp aTimeStamp, String aFormat)
	{
		java.util.Date mDate = new java.util.Date(aTimeStamp.getTime());
		return formatSimpleDateString(mDate, aFormat);
	}

	public static String getFirstDayMonth(String aValue)
	{
		String mRtnValue = aValue;
		String tempDate = formatSimpleDateString(aValue, "MM/dd/yyyy");
		if (tempDate != null && tempDate.length() > 0)
		{
			List<String> dateList = StringUtil.parseLineToList(tempDate, "/");
			String mMonth = (String) dateList.get(0);
			String mDay = "01";
			String mYear = (String) dateList.get(2);
			mRtnValue = mMonth + "/" + mDay + "/" + mYear;
		}
		return mRtnValue;
	}

	public static String getFirstDayNextMonth(String aValue)
	{
		String mRtnValue = aValue;
		String tempDate = formatSimpleDateString(aValue, "MM/dd/yyyy");
		if (tempDate != null && tempDate.length() > 0)
		{
			List<String> dateList = StringUtil.parseLineToList(tempDate, "/");
			String mMonth = (String) dateList.get(0);
			String mDay = "01";
			String mYear = (String) dateList.get(2);
			int mMonthValue = Integer.parseInt(mMonth) + 1;
			mMonth = Integer.toString(mMonthValue);
			if (mMonthValue > 12)
			{
				int mYearValue = Integer.parseInt(mYear) + 1;
				mYear = Integer.toString(mYearValue);
				mMonth = "01";
			}
			mRtnValue = mMonth + "/" + mDay + "/" + mYear;
		}
		return mRtnValue;
	}

	public static String getLastDayMonth(String aValue)
	{
		String mRtnValue = aValue;
		String tempDate = getFirstDayNextMonth(aValue);
		if (tempDate != null && tempDate.length() > 0)
		{
			// take the first day of next month and turn into a Date then subtract 1 and
			// then turn the date back into
			// a formated String.
			tempDate = formatSimpleDateString(add(validUtilDate(tempDate), java.util.Calendar.DATE, -1), "MM/dd/yyyy");
			List<String> dateList = StringUtil.parseLineToList(tempDate, "/");
			String mMonth = (String) dateList.get(0);
			String mDay = (String) dateList.get(1);
			;
			String mYear = (String) dateList.get(2);
			mRtnValue = mMonth + "/" + mDay + "/" + mYear;
		}
		return mRtnValue;
	}

	public static boolean isEqualByDate(java.sql.Date date1, java.sql.Date date2)
	{

		return date1.toString().equals(date2.toString());
	}

	public static boolean isEqualByDate(java.util.Date date1, java.util.Date date2)
	{
		boolean datesEqual = false;
		GregorianCalendar calendar1 = new GregorianCalendar();
		calendar1.setTime(date1);
		GregorianCalendar calendar2 = new GregorianCalendar();
		calendar2.setTime(date2);
		if (calendar1.get(GregorianCalendar.YEAR) == calendar2.get(GregorianCalendar.YEAR)
				&& calendar1.get(GregorianCalendar.MONTH) == calendar2.get(GregorianCalendar.MONTH)
				&& calendar1.get(GregorianCalendar.DATE) == calendar2.get(GregorianCalendar.DATE))
		{
			datesEqual = true;
		}
		return datesEqual;
	}

	public static void main(String[] args)
	{
		java.sql.Date aFromDate = java.sql.Date.valueOf("1958-10-31");
		java.sql.Date aToDate = java.sql.Date.valueOf("2001-02-26");
		DateUtility.calculateNumberOfDays(aFromDate, aToDate);
	}

	private static String parseDate(String dt)
	{
		String formattedDate = null;
		final int DATE_ITEMS = 3;
		int tokenCount = 0;
		String T1 = new String();
		String T2 = new String();
		String T3 = new String();
		String t1 = new String();
		String t2 = new String();
		String t3 = new String();
		if (dt != null)
		{
			if (dt.length() >= 6 && dt.length() <= 10)
			{
				if (dt.indexOf("/") > -1)
				{
					String[] mDate = StringUtil.parseLineToStringArray(dt, "/");
					tokenCount = mDate.length;
					if (tokenCount != DATE_ITEMS)
					{
						System.err.println("ERROR!!  Invalid input date: " + dt);
						return null;
					}
					T1 = mDate[0].trim();
					T2 = mDate[1].trim();
					T3 = mDate[2].trim();
				}
				if (dt.indexOf("-") > -1)
				{
					String[] mDate = StringUtil.parseLineToStringArray(dt, "-");
					tokenCount = mDate.length;

					if (tokenCount != DATE_ITEMS)
					{
						System.err.println("ERROR!!  Invalid input date: " + dt);
						return null;
					}
					T1 = mDate[0].trim();
					T2 = mDate[1].trim();
					T3 = mDate[2].trim();
				}
				if (T1.length() == 4 && T3.length() >= 1 && T3.length() <= 2)
				{
					t1 = new String(T1);
					t2 = new String(T2);
					t3 = new String(T3);
					T1 = t2;
					T2 = t3;
					T3 = t1;
				}
				if (T3.length() == 2)
				{

					if (Integer.valueOf(T3).intValue() >= 20)
					{
						T3 = "19" + T3;
					} else
					{
						T3 = "20" + T3;
					}
				}
				if (T1.length() == 1)
				{
					T1 = "0" + T1;
				}
				if (T2.length() == 1)
				{
					T2 = "0" + T2;
				}
			}
			if (T3.length() == 4 && T2.length() == 2 && T1.length() == 2)
			{
				if (validateInteger(T3).intValue() <= 2099 && validateInteger(T3).intValue() >= 1910)
				{
					formattedDate = T3 + "-" + T1 + "-" + T2;
				}
			}
		}
		return formattedDate;
	}

	private static String[] parseTimeStamp(String dt)
	{
		String[] dateValues = new String[7];
		boolean goodTimeStamp = false;
		if (dt != null)
		{
			if (dt.length() > 11)
			{
				String[] tsSegments = StringUtil.parseLineToStringArray(dt, " ");
				if (tsSegments.length == 2)
				{
					String date = formatSimpleDateString(tsSegments[0], "yyyy-MM-dd");
					String[] dSegments = StringUtil.parseLineToStringArray(date, "-");
					if (dSegments.length == 3)
					{
						dateValues[0] = dSegments[0];
						dateValues[1] = dSegments[1];
						dateValues[2] = dSegments[2];
						goodTimeStamp = true;
					}
					String[] tSegments = StringUtil.parseLineToStringArray(tsSegments[1], ":");
					if (dSegments.length == 3 && goodTimeStamp)
					{
						dateValues[3] = tSegments[0];
						dateValues[4] = tSegments[1];
						String[] secondSegments = StringUtil.parseLineToStringArray(tSegments[2], ".");
						if (secondSegments.length == 2)
						{
							dateValues[5] = secondSegments[0];
							dateValues[6] = secondSegments[1];
						}
					}
				}
			}
			if (!goodTimeStamp)
			{
				dateValues = null;
			}
		} else
		{
			dateValues = null;
		}
		return dateValues;
	}

	private static Integer validateInteger(String s)
	{
		Integer aInteger = null;
		try
		{
			if (s != null)
			{
				aInteger = Integer.valueOf(s);
			}
		} catch (NumberFormatException nfe)
		{
			aInteger = null;
		}
		return aInteger;
	}

	public static java.sql.Date validSQLDate(String dateStr)
	{
		return new java.sql.Date(validUtilDate(dateStr).getTime());
	}

	public static java.sql.Timestamp validTimeStamp(String dateStr)
	{
		java.sql.Timestamp date = null;
		try
		{
			String[] datevalues = parseTimeStamp(dateStr);
			int year = Integer.parseInt(datevalues[0]);
			int month = Integer.parseInt(datevalues[1]) - 1;
			int day = Integer.parseInt(datevalues[2]);
			int hour = Integer.parseInt(datevalues[3]);
			int min = Integer.parseInt(datevalues[4]);
			int sec = Integer.parseInt(datevalues[5]);
			GregorianCalendar calendar = new GregorianCalendar(year, month, day, hour, min, sec);
			calendar.set(Calendar.MILLISECOND, 0);
			long tsLong = (calendar.getTime().getTime());
			date = new java.sql.Timestamp(tsLong);
			StringBuffer nanoMultiplier = new StringBuffer("1");
			int nanoLen = 9 - datevalues[6].length();
			for (int Lp0 = 0; Lp0 < nanoLen; Lp0++)
			{
				nanoMultiplier.append("0");
			}
			date.setNanos(Integer.parseInt(datevalues[6]) * Integer.parseInt(nanoMultiplier.toString()));
		} catch (Exception iae)
		{
			date = null;
		}
		return date;
	}

	public static Date validUtilDate(String dateStr)
	{
		Date date = null;
		try
		{
			String datevalue = parseDate(dateStr);
			java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
			df.setLenient(false);
			date = df.parse(datevalue);
		} catch (Exception iae)
		{
			date = null;
		}
		return date;
	}
}
