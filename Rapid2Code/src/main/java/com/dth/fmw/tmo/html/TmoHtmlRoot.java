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

package com.dth.fmw.tmo.html;

import java.util.ArrayList;
import java.util.List;

import com.dth.core.ctl.R2CSession;
import com.dth.fmw.component.CTO;
import com.dth.fmw.smo.Verdict;
import com.dth.fmw.util.DthBuilder;

public abstract class TmoHtmlRoot
{
	public abstract Verdict predicate(R2CSession aR2CSesssion,CTO aCTO);
	private List<TmoHtmlRoot> contents = new ArrayList<>();
	public static String nl = System.lineSeparator();
	private String style = null;
	
	public String getText(R2CSession aR2CSession ,CTO aCto)
	{
		String mRtnValue = ""; 
		Verdict mVerdict = predicate(aR2CSession,aCto);
		if(mVerdict.isTrue())
		{
			mRtnValue = getTextValue(aR2CSession ,aCto);
		}
		return mRtnValue;
	}
	
	protected abstract String getTextValue(R2CSession aR2CSession ,CTO aCto);
	
	public TmoHtmlRoot add(TmoHtmlRoot aHtmlElement,CTO aCto)
	{
		contents.add(aHtmlElement);
		return aHtmlElement;
	}
	
	public String getContents(R2CSession aR2CSesssion,CTO aCto)
	{
		DthBuilder mRtnValue = new DthBuilder();
		getContents(aR2CSesssion,aCto,mRtnValue);
		Verdict mVerdict = predicate(aR2CSesssion,aCto);
		if(mVerdict.isTrue())
		{
			mRtnValue.append(nl);
		}
		return mRtnValue.toString();
	}
	
	public DthBuilder getContents(R2CSession aSession,CTO aCTO,DthBuilder aBuilder)
	{
		for(TmoHtmlRoot mRoot: contents)
		{
			mRoot.getContents(aSession,aCTO, aBuilder);
		}
		return aBuilder;
	}
	
	public String getStyle()
	{
		return style;
	}
	
	public void setStyle(String aStyle)
	{
		style = aStyle;
	}
	
}
