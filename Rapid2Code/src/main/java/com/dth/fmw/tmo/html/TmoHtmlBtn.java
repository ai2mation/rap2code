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

import com.dth.core.ctl.R2CSession;
import com.dth.fmw.component.CTO;
import com.dth.fmw.smo.Verdict;
import com.dth.fmw.smo.VerdictTrue;
import com.dth.fmw.util.DthBuilder;

public class TmoHtmlBtn extends TmoHtmlRoot
{
	private static VerdictTrue verdictTrue = new VerdictTrue();
	private String style = "";
	private String clasz = "";
	
	@Override
	public Verdict predicate(R2CSession aR2CSession,CTO aCTO)
	{
		return verdictTrue;
	}
	
	protected String getTextValue(R2CSession aR2CSession ,CTO aCto)
	{
		DthBuilder mContents = new DthBuilder();
		mContents.append(TmoHtmlTypes.SForm.tag()+nl);
		super.getContents(aR2CSession ,aCto, mContents);
		mContents.append(TmoHtmlTypes.EForm.tag()+nl);
		return mContents.toString();
	}
	
	public DthBuilder getContents(R2CSession aR2CSession ,CTO aCTO,DthBuilder aBuilder)
	{
		aBuilder.append(TmoHtmlTypes.SBtn.tag());
		if(style.length() > 0)
		{
			aBuilder.append(" style=\""+style+"\"");	
		}
		if(clasz.length() > 0)
		{
			aBuilder.append(" class=\""+clasz+"\"");
		}
		aBuilder.append(">");
		super.getContents(aR2CSession ,aCTO,aBuilder);
		aBuilder.append(TmoHtmlTypes.EBtn.tag()+nl);
		return aBuilder;
	}
	
	public void setStyle(String aStyle)
	{
		style = aStyle;
	}
	
	public void setClass(String aClass)
	{
		clasz = aClass;
	}
}
