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

package com.dth.fmw.util;

import java.lang.StringBuilder;

public class DthBuilder
{
	private StringBuilder mBuilder = new StringBuilder();
	private static int indent =0;
	
    public DthBuilder append(String aValue)
    {
    	indentIt();
    	mBuilder.append(aValue);
    	return this;
    }
    
    public DthBuilder add(DthBuilder aValue)
    {
    	mBuilder.append(aValue);
    	return this;
    }
    
    public DthBuilder add(String aValue)
    {
    	mBuilder.append(aValue);
    	return this;
    }
    
    public DthBuilder append(DthBuilder aValue)
    {
    	indentIt();
    	mBuilder.append(aValue);
    	return this;
    }
    
    public DthBuilder appendWithStyle(String aValue,String aStyle)
    {
    	indentIt();
    	int mSlot = aValue.indexOf(">");
    	String mStart = aValue.substring(0, mSlot);
    	String mNewValue = mStart+" style=\""+aStyle+"\""+">";
    	mBuilder.append(mNewValue);
    	return this;
    }
    
    private void indentIt()
    {
    	for(int Lp0=0;Lp0<indent;Lp0++)
    	{
    		mBuilder.append("  ");
    	}
    }
    
    public StringBuilder getBuilder()
    {
    	return mBuilder;
    }
    
    public static void increment()
    {
    	indent++;
    }

    public static void decremint()
    {
    	indent--;
    }
    
    public String toString() 
    {
      return mBuilder.toString();	
    }
}
