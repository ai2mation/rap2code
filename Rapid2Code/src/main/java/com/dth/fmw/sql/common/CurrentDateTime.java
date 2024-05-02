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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CurrentDateTime
{

	public CurrentDateTime()
	{
		super();
	}

	public static String getCurrentDateMMDDYYYY()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
		sdf.setTimeZone(TimeZone.getDefault());
		StringBuffer currTime = new StringBuffer();
		try
		{
			Date d = new Date();
			sdf.format(d, currTime, new FieldPosition(0));
		} catch (Exception e)
		{
			System.out.println("CurrentDataTime/getCurrentDateYYYYMMDD/Exception: " + e.getMessage());
		}
		return currTime.toString();
	}

	public static String getCurrentDateMMDDYYYYFormatted()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.setTimeZone(TimeZone.getDefault());
		StringBuffer currTime = new StringBuffer();
		try
		{
			Date d = new Date();
			sdf.format(d, currTime, new FieldPosition(0));
		} catch (Exception e)
		{
			System.out.println("CurrentDataTime/getCurrentDateYYYYMMDD/Exception: " + e.getMessage());
		}
		return currTime.toString();
	}

	public static String getCurrentDateTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d  HH:mm:ss:SSSS z yyyy");
		sdf.setTimeZone(TimeZone.getDefault());
		StringBuffer currTime = new StringBuffer();
		try
		{
			Date d = new Date();
			sdf.format(d, currTime, new FieldPosition(0));
		} catch (Exception e)
		{
			System.out.println("CurrentDataTime/getCurrentDateTime/Exception: " + e.getMessage());
		}
		return currTime.toString();
	}

	public static String getCurrentDateTime(String timeZone)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d  hh:mm:ss:SSSS z yyyy");
		TimeZone tz = TimeZone.getTimeZone(timeZone);
		sdf.setTimeZone(tz);
		StringBuffer currTime = new StringBuffer();
		try
		{
			Date d = new Date();
			sdf.format(d, currTime, new FieldPosition(0));
		} catch (Exception e)
		{
			System.out.println("Exception in CurrentDateTime: " + e.getMessage());
		}
		return currTime.toString();
	}

	public static String getCurrentDateTimeAMPM()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d  hh:mm:ss:SSSS a z yyyy");
		sdf.setTimeZone(TimeZone.getDefault());
		StringBuffer currTime = new StringBuffer();
		try
		{
			Date d = new Date();
			sdf.format(d, currTime, new FieldPosition(0));
		} catch (Exception e)
		{
			System.out.println("CurrentDataTime/getCurrentDateTimeAMPM/Exception: " + e.getMessage());
		}
		return currTime.toString();
	}

	public static String getCurrentDateTimeMMDDYYYYHHMMSS()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a");
		sdf.setTimeZone(TimeZone.getDefault());
		StringBuffer currTime = new StringBuffer();
		try
		{
			Date d = new Date();
			sdf.format(d, currTime, new FieldPosition(0));
		} catch (Exception e)
		{
			System.out.println("CurrentDateTime/getCurrentDateTimeMMDDYYYYHHMMSS/Exception: " + e.getMessage());
		}
		return currTime.toString();
	}

	public static String getCurrentDateTimeYYYYMMDDHHMMSS()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS a");
		sdf.setTimeZone(TimeZone.getDefault());
		StringBuffer currTime = new StringBuffer();
		try
		{
			Date d = new Date();
			sdf.format(d, currTime, new FieldPosition(0));
		} catch (Exception e)
		{
			System.out.println("CurrentDateTime/getCurrentDateTimeMMDDYYYYHHMMSS/Exception: " + e.getMessage());
		}
		return currTime.toString();
	}

	public static String getCurrentDateTimeYYYYMMDDHHMMSSUnFormatted()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		sdf.setTimeZone(TimeZone.getDefault());
		StringBuffer currTime = new StringBuffer();
		try
		{
			Date d = new Date();
			sdf.format(d, currTime, new FieldPosition(0));
		} catch (Exception e)
		{
			System.out.println(
					"CurrentDateTime/getCurrentDateTimeMMDDYYYYHHMMSSUnFormatted/Exception: " + e.getMessage());
		}
		return currTime.toString();
	}

	public static String getCurrentDateYYYYMMDD()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setTimeZone(TimeZone.getDefault());
		StringBuffer currTime = new StringBuffer();
		try
		{
			Date d = new Date();
			sdf.format(d, currTime, new FieldPosition(0));
		} catch (Exception e)
		{
			System.out.println("CurrentDataTime/getCurrentDateYYYYMMDD/Exception: " + e.getMessage());
		}
		return currTime.toString();
	}

	public static String getCurrentTimeHHMMSS()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		sdf.setTimeZone(TimeZone.getDefault());
		StringBuffer currTime = new StringBuffer();
		try
		{
			Date d = new Date();
			sdf.format(d, currTime, new FieldPosition(0));
		} catch (Exception e)
		{
			System.out.println("CurrentDataTime/getCurrentTimeHHMMSS/Exception: " + e.getMessage());
		}
		return currTime.toString();
	}

	public static String getCurrentTimeHHMMSSFormatted()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(TimeZone.getDefault());
		StringBuffer currTime = new StringBuffer();
		try
		{
			Date d = new Date();
			sdf.format(d, currTime, new FieldPosition(0));
		} catch (Exception e)
		{
			System.out.println("CurrentDataTime/getCurrentTimeHHMMSS/Exception: " + e.getMessage());
		}
		return currTime.toString();
	}
}
