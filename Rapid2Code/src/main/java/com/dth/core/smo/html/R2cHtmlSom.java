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

package com.dth.core.smo.html;

import java.util.List;

import com.dth.core.ctl.R2CSession;
import com.dth.fmw.component.CTO;
import com.dth.fmw.logger.SmartLogger;
import com.dth.fmw.smo.SomBase;
import com.dth.fmw.smo.Verdict;
import com.dth.fmw.smo.VerdictTrue;
import com.dth.fmw.tmo.html.TmoHtmlRoot;


public class R2cHtmlSom extends SomBase
{

	private static VerdictTrue verdictTrue = new VerdictTrue();
	
	public String getDoc(CTO aCTO,R2CSession aR2CSession)
	{
		SmartLogger.Trace(1,"");
		StringBuilder mBuilder = new StringBuilder();
		String mKey = aR2CSession.getHtmlDoc();
		HtmlPageTypes mType = HtmlPageTypes.getType(mKey);
		if(mType != null)
		{
			getHtlmFrame(mType,mBuilder,aR2CSession,aCTO);	
		}
		SmartLogger.Trace(2,"");
		return mBuilder.toString();
	}

	private void getHtlmFrame(HtmlPageTypes aKey,StringBuilder aBuilder,R2CSession aR2CSession,CTO aCto)
	{
		SmartLogger.Trace(1,"");
		R2cHtmlBuilder mBuilder = getBuilder(aKey);
		List<TmoHtmlRoot> mHtmlList = mBuilder.build(aCto);
		for(TmoHtmlRoot mRoot: mHtmlList)
		{
			aBuilder.append(mRoot.getText(aR2CSession,aCto));
		}
		SmartLogger.Trace(2,"");
	}
	

	@Override
	public Verdict predicate(CTO aCTO)
	{
		SmartLogger.Trace(1,"");
		return verdictTrue;
	}
	
	private R2cHtmlBuilder getBuilder(HtmlPageTypes aKey)
	{
		SmartLogger.Trace(1,"");
		R2cHtmlBuilder mRtnValue = null;
		HtmlPageTypes mHtmlPageTypes = aKey;
		switch(aKey)
		{
		case SignOn:
			SmartLogger.Trace(3,"SignOn");
			mRtnValue = new R2cHtmlBuildSignOn();
			break;
		case HtmlDesign:
			SmartLogger.Trace(3,"HtmlDesign");
			mRtnValue = new R2cHtmlBuilDesign();
			break;
		case buildTemplate:
			SmartLogger.Trace(3,"buildTemplate");
			mRtnValue = new R2cHtmlBuildTemplates();
			break;
		case buildSingleWithMenu:
			SmartLogger.Trace(3,"buildSingleWithMenu");
			mRtnValue = new R2cHtmlBuildSingleWithMenu();
			break;
		case buildSingleNoMenu:
			SmartLogger.Trace(3,"buildSingleNoMenu");
			mRtnValue = new R2cHtmlBuildSingleNoMenu();
			break;
		case buildVerticalWithMenu:
			SmartLogger.Trace(3,"buildVerticalWithMenu");
			mRtnValue = new R2cHtmlBuildVertcalWithMenu();
			break;
		case buildVerticalNoMenu:
			SmartLogger.Trace(3,"buildVerticalNoMenu");
			mRtnValue = new R2cHtmlBuilVerticalNoMnu();
			break;
		case buildHorizontalWithMenu:
			SmartLogger.Trace(3,"buildHorizontalWithMenu");
			mRtnValue = new R2cHtmlBuildHorizontalWithMenu();
			break;
		case buildHorizontalNoMenu:
			SmartLogger.Trace(3,"buildHorizontalNoMenu");
			mRtnValue = new R2cHtmlBuildHorizontalNoMenu();
			break;
		case WindowsOverview:
			SmartLogger.Trace(3,"WindowsOverview");
			mRtnValue = new R2cHtmlBuildWindowsOverview();
			break;
		case RoadMapDashBoard:
			SmartLogger.Trace(3,"RoadMapDashBoard");
			mRtnValue = new R2cHtmlBuildRoadMapDashboard();
			break;
		default:
			SmartLogger.Trace(3,"default");
			mRtnValue = new R2cHtmlBuildSignOn();
			break;
		}
		SmartLogger.Trace(2,"");
		return mRtnValue;
	}
}
