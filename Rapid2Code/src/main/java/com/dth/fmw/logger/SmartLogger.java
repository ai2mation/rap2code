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

package com.dth.fmw.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SmartLogger
{
	private static Logger log = LogManager.getLogger(""); 
	private static int TraceLevel = 0;
	private static int DebugLevel = 0;
	
	public static void setTraceLevel(int aTraceLevel)
	{
		TraceLevel = aTraceLevel;
		String[] mTag = getTag();
		String mMsg = "trace: "+mTag[0]+"::"+mTag[1]+" TraceLevel set to "+aTraceLevel;
		log.info("\u001B[34m" + mMsg + "\u001B[0m");
		//log.info(mMsg);
	}
	
	public static void setDebugLevel(int aDebugLevel)
	{
		DebugLevel = aDebugLevel;
		String[] mTag = getTag();
		String mMsg = mTag[0]+"::"+mTag[1]+" DebugLevel set to "+aDebugLevel;
		log.debug(mMsg);
	}
	
	public static void Trace(int aTraceLevel,String aMsg)
	{
		if(aTraceLevel != 0 && aTraceLevel <= TraceLevel)
		{
			String[] mTag = getTag();
			String mTraceMsg = "trace: ";
			switch(aTraceLevel)
			{
			case 1: mTraceMsg += "enter "; break;
			case 2: mTraceMsg += "exit "; break;
			case 3: mTraceMsg += "ctl "; break;
			}
			String mMsg = mTraceMsg+mTag[0]+"::"+mTag[1]+"  "+aMsg;
			log.info("\u001B[34m" + mMsg + "\u001B[0m");
			//log.info(mMsg);
		}
	}
	
	public static void debug(int aDebugLevel,String aMsg)
	{  
		if(aDebugLevel != 0 && aDebugLevel <= DebugLevel)
		{
			String[] mTag = getTag();
			String mMsg = mTag[0]+"::"+mTag[1]+"  "+aMsg;
			log.debug(mMsg);
		}

	}
	
	public static String[] getTag()
	{
		String[] mRtnValue = new String[2];
		StackTraceElement[] mStacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement mElement = mStacktrace[3];//maybe this number needs to be corrected
		String mClassName = mElement.getClassName();
		String mMethodName = mElement.getMethodName();
		mRtnValue[0] = mClassName;
		mRtnValue[1] = mMethodName;
		return mRtnValue;
	}
	
	/*
	
	  TRACE("Trace"),
	  DEBUG("Debug"),
	  INFO("Info"),
	  WARN("Warn"),
	  ERROR("Error"),
	  FATAL("Fatal"),
	  */
}
