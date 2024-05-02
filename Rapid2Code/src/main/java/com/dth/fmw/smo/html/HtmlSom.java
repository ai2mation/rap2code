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

package com.dth.fmw.smo.html;

import java.util.ArrayList;
import java.util.List;

import com.dth.core.ctl.R2CSession;
import com.dth.fmw.component.CTO;
import com.dth.fmw.logger.SmartLogger;
import com.dth.fmw.smo.SomBase;
import com.dth.fmw.smo.Verdict;
import com.dth.fmw.smo.VerdictTrue;
import com.dth.fmw.tmo.html.TmoHtmlDoc;
import com.dth.fmw.tmo.html.TmoHtmlRoot;
import com.dth.fmw.tmo.html.TmoHtmlString;

public class HtmlSom extends SomBase
{
	private static List<TmoHtmlRoot> htmlList = new ArrayList<>();
	
	static
	{
	  htmlList.add(new TmoHtmlDoc());
	  htmlList.add(new TmoHtmlString("<b>HtmlSom</b>ss<h1>html</h1>xxx<b>welcome.</b>ss<h1>html</h1>xxx"));
	}
	
	private static VerdictTrue verdictTrue = new VerdictTrue();

	private void getHtlmFrame(StringBuilder aBuilder,R2CSession aR2CSession,CTO aCto)
	{
		SmartLogger.Trace(1,"");
		for(TmoHtmlRoot mRoot: htmlList)
		{
			aBuilder.append(mRoot.getText(aR2CSession,aCto));
		}
		//saBuilder.append("<b>Signin screen</b>ss<h1>html</h1>xxx<b>welcome.</b>ss<h1>html</h1>xxx");
		SmartLogger.Trace(2,"");
	}
	

	@Override
	public Verdict predicate(CTO aCTO)
	{
		SmartLogger.Trace(1,"");
		return verdictTrue;
	}

	@Override
	public String getDoc(CTO aCTO, R2CSession aR2CSession)
	{
		SmartLogger.Trace(1,"");
		StringBuilder mBuilder = new StringBuilder();
		getHtlmFrame(mBuilder,aR2CSession,aCTO);
		SmartLogger.Trace(2,"");
		return mBuilder.toString();
	}
}
