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
package com.dth.core.ctl;

import org.springframework.web.bind.annotation.RequestMapping;

import com.dth.core.cto.CtoDefault;
import com.dth.core.cto.CtoSignIn;
import com.dth.core.smo.html.HtmlPageTypes;
import com.dth.core.smo.html.R2cHtmlSom;
import com.dth.fmw.component.CTO;
import com.dth.fmw.logger.SmartLogger;
import com.dth.fmw.smo.SomBase;

import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.context.annotation.ComponentScan;


@ComponentScan({"com.dth.core.ctl"})
@RestController
public class R2cController
{

	
	@RequestMapping("/")
	public String root(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.ROOT);
	}
	
	@RequestMapping("/home")
	public String home(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.HOME);
	}

	@RequestMapping("/Welcome")
	public String welcome(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.WELCOME);
	}
	
	@RequestMapping("/helpContents")
	public String helpContents(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.HELP_CONTENTS);
	}
	
	@RequestMapping("/Search")
	public String search(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.SEARCH);
	}
	
	@RequestMapping("/showContextHelp")
	public String showContextHelp(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"smartlogger ");
		return execute(aRequest,aResponse,CtlAction.HELP_CONTEXT);
	}
	
	@RequestMapping("/tipOftheDay")
	public String typeOfTheDay(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.TIP_OF_DAY);
	}
	

	@RequestMapping("/trickTips")
	public String trickTips(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.TRICK_TIPS);
	}
	
	@RequestMapping("/about")
	public String about(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.ABOUT);
	}
	
	@RequestMapping("/buildHtmlPage")
	public String buildHtmlPage(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.BUILD_HTML_PAGE);
	}
	
	@RequestMapping("/buildTemplate")
	public String buildTemplate(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.SELECT_HTML_TEMPLATE);
	}
	
	@RequestMapping("/singleMenu")
	public String buildSingleMenu(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.SINGLE_W_MENU);
	}
	
	@RequestMapping("/singlelNoMenu")
	public String buildSingleNoMenu(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.SINGLE_NO_MENU);
	}
	
	@RequestMapping("/verticalDualMenu")
	public String buildVerticalDualMenu(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.VERTICAL_DUAL_W_MENU);
	}
	
	@RequestMapping("/verticalDualNoMenu")
	public String buildVerticalDualNoMenu(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.VERTICAL_DUAL_NO_MENU);
	}
	
	@RequestMapping("/horizontalDualMenu")
	public String buildHorizontalMenu(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.HORIZONTAL_DUAL_W_MENU);
	}
	
	@RequestMapping("/horizontalDualNoMenu")
	public String buildHorizontalNoMenu(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.HORIZONTAL_DUAL_NO_MENU);
	}
	

	@RequestMapping("/insideOverview")
	public String insideOverview(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.INSIDE_OVERVIEEW);
	}
	/////////////////////////////////////
	/// road map
	/////////////////////////////////////
	@RequestMapping("/dashboard")
	public String dashboard(HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		SmartLogger.Trace(1,"");
		return execute(aRequest,aResponse,CtlAction.DASH_BOARD);
	}
	
	private String execute(HttpServletRequest aRequest,HttpServletResponse aResponse,CtlAction aAction)
	{
		SmartLogger.Trace(1,"");
		HttpSession mHttpSession = aRequest.getSession(true);
		Object mR2CObject = mHttpSession.getAttribute("R2CSESSION");
		R2CSession mR2CSession = null;
		CTO mCTO = null;
		if(mR2CObject == null)
		{
			mR2CSession = new R2CSession();
			mR2CSession.setHtmlDoc(HtmlPageTypes.SignOn.getName());
			mHttpSession.setAttribute("R2CSession", mR2CSession);
		}
		else
		{
			mR2CSession = (R2CSession)mR2CObject;
		}
        SomBase mHtmlSom = null;
        switch (aAction) {
		case ABOUT:
			SmartLogger.Trace(3,"About");
			break;
		case BUILD_HTML_PAGE:
			SmartLogger.Trace(3,"BUILD_HTML_PAGE");
        	String mKey = HtmlPageTypes.HtmlDesign.getName();
        	mR2CSession.setHtmlDoc(mKey);
        	mHtmlSom = new R2cHtmlSom();
			break;
		case HELP_CONTENTS:
			SmartLogger.Trace(3,"HELP_CONTENTS");
			break;
		case HELP_CONTEXT:
			SmartLogger.Trace(3,"HELP_CONTEXT");
			break;
		
		case SEARCH:
			SmartLogger.Trace(3,"SEARCH");
			break;
		case TIP_OF_DAY:
			SmartLogger.Trace(3,"TIP_OF_DAY");
			break;
		case TRICK_TIPS:
			SmartLogger.Trace(3,"TRICK_TIPS");
			break;
		case WELCOME:
			SmartLogger.Trace(3,"WELCOME");
			break;
		case SELECT_HTML_TEMPLATE:
			SmartLogger.Trace(3,"SELECT_HTML_TEMPLATE");
        	mKey = HtmlPageTypes.buildTemplate.getName();
        	mR2CSession.setHtmlDoc(mKey);
        	mHtmlSom = new R2cHtmlSom();
			break;
		case INSIDE_OVERVIEEW:
			SmartLogger.Trace(3,"INSIDE_OVERVIEW");
        	mKey = HtmlPageTypes.WindowsOverview.getName();
        	mR2CSession.setHtmlDoc(mKey);
        	mHtmlSom = new R2cHtmlSom();
			break;
		case DASH_BOARD:
			SmartLogger.Trace(3,"DASH_BOARD");
        	mKey = HtmlPageTypes.RoadMapDashBoard.getName();
        	mR2CSession.setHtmlDoc(mKey);
        	mHtmlSom = new R2cHtmlSom();
			break;
		case ROOT:
			SmartLogger.Trace(3,"ROOT");
		case HOME:
			SmartLogger.Trace(3,"HOME");

		default:   
			SmartLogger.Trace(3,"default");
			mCTO = new CtoSignIn();
			mKey = HtmlPageTypes.SignOn.getName();
    	    mR2CSession.setHtmlDoc(mKey);
    	    mHtmlSom = new R2cHtmlSom();
			break;

        }
        if(mCTO == null) mCTO = new CtoDefault();
        String mHtmlStr = mHtmlSom.getDoc(mCTO,mR2CSession);
        SmartLogger.Trace(2,"");
		return mHtmlStr;
	}
	
}