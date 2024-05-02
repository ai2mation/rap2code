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

import java.io.Serializable;
import java.util.List;

import com.dth.fmw.component.CTO;
import com.dth.fmw.tmo.html.TmoHtmlBreak;
import com.dth.fmw.tmo.html.TmoHtmlBtn;
import com.dth.fmw.tmo.html.TmoHtmlCssClass;
import com.dth.fmw.tmo.html.TmoHtmlDiv;
import com.dth.fmw.tmo.html.TmoHtmlH1;
import com.dth.fmw.tmo.html.TmoHtmlHead;
import com.dth.fmw.tmo.html.TmoHtmlIdo;
import com.dth.fmw.tmo.html.TmoHtmlLink;
import com.dth.fmw.tmo.html.TmoHtmlRoot;
import com.dth.fmw.tmo.html.TmoHtmlString;
import com.dth.fmw.tmo.html.TmoHtmlStyle;


public abstract class R2cHtmlBuilder implements Serializable
{
	
	public abstract List<TmoHtmlRoot> build(CTO aCto);
	
	protected void buildHeader(TmoHtmlRoot aBody,CTO aCto)
	{
		  TmoHtmlRoot mHeading = new TmoHtmlH1();
		  mHeading.setStyle("text-align: center; vertical-align: text-top;");
		  mHeading.add(new TmoHtmlString("R2C"),aCto);
		  mHeading.setStyle("color:white;");
		  TmoHtmlDiv mTmoHtmlDiv = new TmoHtmlDiv();
		  mTmoHtmlDiv.setStyle("width: 100%;");
		  aBody.add(mTmoHtmlDiv,aCto);
		  TmoHtmlDiv mTmoHtmlDivLeft = new TmoHtmlDiv();
		  mTmoHtmlDivLeft.setStyle("width: 10%; height: 50px; float: left; background: blue; border: 2px solid black");
		  mTmoHtmlDivLeft.add(mHeading,aCto);
		  mTmoHtmlDiv.add(mTmoHtmlDivLeft,aCto);
		  TmoHtmlDiv mTmoHtmlDivRight = new TmoHtmlDiv();
		  mTmoHtmlDivRight.setStyle("margin-left: 10%; height: 50px; background: white; border: 2px solid black");
		  mTmoHtmlDiv.add(mTmoHtmlDivRight,aCto);
		  
		  TmoHtmlDiv mTmoHtmlDivNavBar = new TmoHtmlDiv();
		  mTmoHtmlDivRight.add(mTmoHtmlDivNavBar,aCto);
		  mTmoHtmlDivNavBar.setClass("navbar");
		  buidRoadMapkMenu(mTmoHtmlDivNavBar,aCto);
		  buildProjectMenu(mTmoHtmlDivNavBar,aCto);
		  buidBoardsMenu(mTmoHtmlDivNavBar,aCto);
		  buildLookMenu(mTmoHtmlDivNavBar,aCto);
		  buildDiscoverMenu(mTmoHtmlDivNavBar,aCto);
		  buildTutorialMenu(mTmoHtmlDivNavBar,aCto);
		  buildDesignMenu(mTmoHtmlDivNavBar,aCto);
		  buildRenovateMenu(mTmoHtmlDivNavBar,aCto);
		  buildWindowMenu(mTmoHtmlDivNavBar,aCto);
		  buildProfileMenu(mTmoHtmlDivNavBar,aCto);
		  buildHelpMenu(mTmoHtmlDivNavBar,aCto);
	}
	
	protected void buidRoadMapkMenu(TmoHtmlDiv mTmoHtmlDivNavBar,CTO aCto)
	{
		  TmoHtmlDiv mTmoHtmlDivDropDown = new TmoHtmlDiv();
		  mTmoHtmlDivNavBar.add(mTmoHtmlDivDropDown,aCto);
		  mTmoHtmlDivDropDown.setClass("dropdown");
		  
		  TmoHtmlBtn mTmoHtmlBtn = new TmoHtmlBtn();
		  mTmoHtmlDivDropDown.add(mTmoHtmlBtn,aCto);
		  mTmoHtmlBtn.setClass("dropbtn");
		  TmoHtmlString mTmoHtmlString = new TmoHtmlString("Road Map");
		  mTmoHtmlBtn.add(mTmoHtmlString,aCto);
		  TmoHtmlIdo mIdo = new TmoHtmlIdo();
		  mTmoHtmlBtn.add(mIdo,aCto);
		  mIdo.setClass("fa fa-caret-down");
		  TmoHtmlDiv mTmoHtmlDivContent = new TmoHtmlDiv();
		  mTmoHtmlDivContent.setClass("dropdown-content");
		  mTmoHtmlDivDropDown.add(mTmoHtmlDivContent,aCto);
		  TmoHtmlLink mTmoHtmlLink = new TmoHtmlLink();
		  mTmoHtmlLink.setHRef("insideOverview");
		  mTmoHtmlDivContent.add(mTmoHtmlLink,aCto);
		  TmoHtmlString mLink1 = new TmoHtmlString("Overview");
		  mTmoHtmlLink.add(mLink1,aCto);    
		  
		  addLink(mTmoHtmlDivContent,"Home","home",aCto);
		  addLink(mTmoHtmlDivContent,"Dashboard","dashboard",aCto);
		  addLink(mTmoHtmlDivContent,"Sign In","signin",aCto);
		  addLink(mTmoHtmlDivContent,"Sign up","signup",aCto);
		  addLink(mTmoHtmlDivContent,"User Profile","userProfile",aCto);
		  addLink(mTmoHtmlDivContent,"Create Group","createGroup",aCto);
		  addLink(mTmoHtmlDivContent,"Manage Group","manageGroup",aCto);
	}
	

	protected void buildProjectMenu(TmoHtmlDiv mTmoHtmlDivNavBar,CTO aCto)
	{
		  TmoHtmlDiv mTmoHtmlDivDropDown = new TmoHtmlDiv();
		  mTmoHtmlDivNavBar.add(mTmoHtmlDivDropDown,aCto);
		  mTmoHtmlDivDropDown.setClass("dropdown");
		  
		  TmoHtmlBtn mTmoHtmlBtn = new TmoHtmlBtn();
		  mTmoHtmlDivDropDown.add(mTmoHtmlBtn,aCto);
		  mTmoHtmlBtn.setClass("dropbtn");
		  TmoHtmlString mTmoHtmlString = new TmoHtmlString("Projects");
		  mTmoHtmlBtn.add(mTmoHtmlString,aCto);
		  TmoHtmlIdo mIdo = new TmoHtmlIdo();
		  mTmoHtmlBtn.add(mIdo,aCto);
		  mIdo.setClass("fa fa-caret-down");
		  TmoHtmlDiv mTmoHtmlDivContent = new TmoHtmlDiv();
		  mTmoHtmlDivContent.setClass("dropdown-content");
		  mTmoHtmlDivDropDown.add(mTmoHtmlDivContent,aCto);
		  addLink(mTmoHtmlDivContent,"Overview","fileOverview",aCto);
        addLink(mTmoHtmlDivContent,"Home","fileHome",aCto);
	}
	
	protected void buidBoardsMenu(TmoHtmlDiv mTmoHtmlDivNavBar,CTO aCto)
	{
		  TmoHtmlDiv mTmoHtmlDivDropDown = new TmoHtmlDiv();
		  mTmoHtmlDivNavBar.add(mTmoHtmlDivDropDown,aCto);
		  mTmoHtmlDivDropDown.setClass("dropdown");
		  
		  TmoHtmlBtn mTmoHtmlBtn = new TmoHtmlBtn();
		  mTmoHtmlDivDropDown.add(mTmoHtmlBtn,aCto);
		  mTmoHtmlBtn.setClass("dropbtn");
		  TmoHtmlString mTmoHtmlString = new TmoHtmlString("Boards");
		  mTmoHtmlBtn.add(mTmoHtmlString,aCto);
		  TmoHtmlIdo mIdo = new TmoHtmlIdo();
		  mTmoHtmlBtn.add(mIdo,aCto);
		  mIdo.setClass("fa fa-caret-down");
		  TmoHtmlDiv mTmoHtmlDivContent = new TmoHtmlDiv();
		  mTmoHtmlDivContent.setClass("dropdown-content");
		  mTmoHtmlDivDropDown.add(mTmoHtmlDivContent,aCto);
		  TmoHtmlLink mTmoHtmlLink = new TmoHtmlLink();
		  mTmoHtmlLink.setHRef("insideOverview");
		  mTmoHtmlDivContent.add(mTmoHtmlLink,aCto);
		  TmoHtmlString mLink1 = new TmoHtmlString("Overview");
		  mTmoHtmlLink.add(mLink1,aCto);
		  
		  addLink(mTmoHtmlDivContent,"Home","home",aCto);
		  addLink(mTmoHtmlDivContent,"Dashboard","dashboard",aCto);
		  addLink(mTmoHtmlDivContent,"Sign In","signin",aCto);
		  addLink(mTmoHtmlDivContent,"Sign up","signup",aCto);
		  addLink(mTmoHtmlDivContent,"User Profile","userProfile",aCto);
		  addLink(mTmoHtmlDivContent,"Create Group","createGroup",aCto);
		  addLink(mTmoHtmlDivContent,"Manage Group","manageGroup",aCto);
	}
	
	protected void buildLookMenu(TmoHtmlDiv mTmoHtmlDivNavBar,CTO aCto)
	{
		  TmoHtmlDiv mTmoHtmlDivDropDown = new TmoHtmlDiv();
		  mTmoHtmlDivNavBar.add(mTmoHtmlDivDropDown,aCto);
		  mTmoHtmlDivDropDown.setClass("dropdown");
		  
		  TmoHtmlBtn mTmoHtmlBtn = new TmoHtmlBtn();
		  mTmoHtmlDivDropDown.add(mTmoHtmlBtn,aCto);
		  mTmoHtmlBtn.setClass("dropbtn");
		  TmoHtmlString mTmoHtmlString = new TmoHtmlString("Look Inside");
		  mTmoHtmlBtn.add(mTmoHtmlString,aCto);
		  TmoHtmlIdo mIdo = new TmoHtmlIdo();
		  mTmoHtmlBtn.add(mIdo,aCto);
		  mIdo.setClass("fa fa-caret-down");
		  TmoHtmlDiv mTmoHtmlDivContent = new TmoHtmlDiv();
		  mTmoHtmlDivContent.setClass("dropdown-content");
		  mTmoHtmlDivDropDown.add(mTmoHtmlDivContent,aCto);
		  TmoHtmlLink mTmoHtmlLink = new TmoHtmlLink();
		  mTmoHtmlLink.setHRef("insideOverview");
		  mTmoHtmlDivContent.add(mTmoHtmlLink,aCto);
		  TmoHtmlString mLink1 = new TmoHtmlString("Overview");
		  mTmoHtmlLink.add(mLink1,aCto);
	}
	
	protected void buildDiscoverMenu(TmoHtmlDiv mTmoHtmlDivNavBar,CTO aCto)
	{
		  TmoHtmlDiv mTmoHtmlDivDropDown1 = new TmoHtmlDiv();
		  mTmoHtmlDivNavBar.add(mTmoHtmlDivDropDown1,aCto);
		  mTmoHtmlDivDropDown1.setClass("dropdown");

		  TmoHtmlBtn mTmoHtmlBtn1 = new TmoHtmlBtn();
		  mTmoHtmlDivDropDown1.add(mTmoHtmlBtn1,aCto);
		  mTmoHtmlBtn1.setClass("dropbtn");
		  TmoHtmlString mTmoHtmlString1 = new TmoHtmlString("Discover");
		  mTmoHtmlBtn1.add(mTmoHtmlString1,aCto);
		  TmoHtmlIdo mIdo1 = new TmoHtmlIdo();
		  mTmoHtmlBtn1.add(mIdo1,aCto);
		  mIdo1.setClass("fa fa-caret-down");
		  TmoHtmlDiv mTmoHtmlDivContent1 = new TmoHtmlDiv();
		  mTmoHtmlDivContent1.setClass("dropdown-content");
		  mTmoHtmlDivDropDown1.add(mTmoHtmlDivContent1,aCto);
		  TmoHtmlLink mTmoHtmlLink1 = new TmoHtmlLink();
		  mTmoHtmlLink1.setHRef("discoverOverview");
		  mTmoHtmlDivContent1.add(mTmoHtmlLink1,aCto);
		  TmoHtmlString mLink11 = new TmoHtmlString("Over discover");
		  mTmoHtmlLink1.add(mLink11,aCto);
	}
	
	protected void buildTutorialMenu(TmoHtmlDiv mTmoHtmlDivNavBar,CTO aCto)
	{
		  TmoHtmlDiv mTmoHtmlDivDropDown = new TmoHtmlDiv();
		  mTmoHtmlDivNavBar.add(mTmoHtmlDivDropDown,aCto);
		  mTmoHtmlDivDropDown.setClass("dropdown");
		  
		  TmoHtmlBtn mTmoHtmlBtn = new TmoHtmlBtn();
		  mTmoHtmlDivDropDown.add(mTmoHtmlBtn,aCto);
		  mTmoHtmlBtn.setClass("dropbtn");
		  TmoHtmlString mTmoHtmlString = new TmoHtmlString("Tutorial");
		  mTmoHtmlBtn.add(mTmoHtmlString,aCto);
		  TmoHtmlIdo mIdo = new TmoHtmlIdo();
		  mTmoHtmlBtn.add(mIdo,aCto);
		  mIdo.setClass("fa fa-caret-down");
		  TmoHtmlDiv mTmoHtmlDivContent = new TmoHtmlDiv();
		  mTmoHtmlDivContent.setClass("dropdown-content");
		  mTmoHtmlDivDropDown.add(mTmoHtmlDivContent,aCto);
		  TmoHtmlLink mTmoHtmlLink = new TmoHtmlLink();
		  mTmoHtmlLink.setHRef("insideOverview");
		  mTmoHtmlDivContent.add(mTmoHtmlLink,aCto);
		  TmoHtmlString mLink1 = new TmoHtmlString("Overview");
		  mTmoHtmlLink.add(mLink1,aCto);
	}
	
	protected void buildDesignMenu(TmoHtmlDiv mTmoHtmlDivNavBar,CTO aCto)
	{
		  TmoHtmlDiv mTmoHtmlDivDropDown = new TmoHtmlDiv();
		  mTmoHtmlDivNavBar.add(mTmoHtmlDivDropDown,aCto);
		  mTmoHtmlDivDropDown.setClass("dropdown");
		  
		  TmoHtmlBtn mTmoHtmlBtn = new TmoHtmlBtn();
		  mTmoHtmlDivDropDown.add(mTmoHtmlBtn,aCto);
		  mTmoHtmlBtn.setClass("dropbtn");
		  TmoHtmlString mTmoHtmlString = new TmoHtmlString("Design");
		  mTmoHtmlBtn.add(mTmoHtmlString,aCto);
		  TmoHtmlIdo mIdo = new TmoHtmlIdo();
		  mTmoHtmlBtn.add(mIdo,aCto);
		  mIdo.setClass("fa fa-caret-down");
		  TmoHtmlDiv mTmoHtmlDivContent = new TmoHtmlDiv();
		  mTmoHtmlDivContent.setClass("dropdown-content");
		  mTmoHtmlDivDropDown.add(mTmoHtmlDivContent,aCto);
          addLink(mTmoHtmlDivContent,"Overview","designOverview",aCto);
          addLink(mTmoHtmlDivContent,"Drag and Drop","dragAndDrop",aCto);
          addLink(mTmoHtmlDivContent,"Build Html Page","buildHtmlPage",aCto);
          addLink(mTmoHtmlDivContent,"Html Template","buildTemplate",aCto);
	}
	
	protected void buildAddHtmlMenu(TmoHtmlDiv mTmoHtmlDivNavBar,CTO aCto)
	{
		  TmoHtmlDiv mTmoHtmlDivDropDown = new TmoHtmlDiv();
		  mTmoHtmlDivNavBar.add(mTmoHtmlDivDropDown,aCto);
		  mTmoHtmlDivDropDown.setClass("dropdown");
		  
		  TmoHtmlBtn mTmoHtmlBtn = new TmoHtmlBtn();
		  mTmoHtmlDivDropDown.add(mTmoHtmlBtn,aCto);
		  mTmoHtmlBtn.setClass("dropbtn");
		  TmoHtmlString mTmoHtmlString = new TmoHtmlString("Html Elem");
		  mTmoHtmlBtn.add(mTmoHtmlString,aCto);
		  TmoHtmlIdo mIdo = new TmoHtmlIdo();
		  mTmoHtmlBtn.add(mIdo,aCto);
		  mIdo.setClass("fa fa-caret-down");
		  TmoHtmlDiv mTmoHtmlDivContent = new TmoHtmlDiv();
		  mTmoHtmlDivContent.setClass("dropdown-content");
		  mTmoHtmlDivDropDown.add(mTmoHtmlDivContent,aCto);
          addLink(mTmoHtmlDivContent,"Input","addInput",aCto);
          addLink(mTmoHtmlDivContent,"Link","addLink",aCto);
          addLink(mTmoHtmlDivContent,"Heading","addHeading",aCto);
          addLink(mTmoHtmlDivContent,"HR","addHr",aCto);
          addLink(mTmoHtmlDivContent,"Br","addBr",aCto);
          addLink(mTmoHtmlDivContent,"Add Image","addImage",aCto);
          addLink(mTmoHtmlDivContent,"Add Form","addForm",aCto);
	}
	
	protected void buildRenovateMenu(TmoHtmlDiv mTmoHtmlDivNavBar,CTO aCto)
	{
		  TmoHtmlDiv mTmoHtmlDivDropDown = new TmoHtmlDiv();
		  mTmoHtmlDivNavBar.add(mTmoHtmlDivDropDown,aCto);
		  mTmoHtmlDivDropDown.setClass("dropdown");
		  
		  TmoHtmlBtn mTmoHtmlBtn = new TmoHtmlBtn();
		  mTmoHtmlDivDropDown.add(mTmoHtmlBtn,aCto);
		  mTmoHtmlBtn.setClass("dropbtn");
		  TmoHtmlString mTmoHtmlString = new TmoHtmlString("Renovate");
		  mTmoHtmlBtn.add(mTmoHtmlString,aCto);
		  TmoHtmlIdo mIdo = new TmoHtmlIdo();
		  mTmoHtmlBtn.add(mIdo,aCto);
		  mIdo.setClass("fa fa-caret-down");
		  TmoHtmlDiv mTmoHtmlDivContent = new TmoHtmlDiv();
		  mTmoHtmlDivContent.setClass("dropdown-content");
		  mTmoHtmlDivDropDown.add(mTmoHtmlDivContent,aCto);
		  addLink(mTmoHtmlDivContent,"Overview","renovateOverview",aCto);
          addLink(mTmoHtmlDivContent,"Code Mining","codeMining",aCto);
          addLink(mTmoHtmlDivContent,"Class Digram","classDiagram",aCto);
	}
	
	protected void buildWindowMenu(TmoHtmlDiv mTmoHtmlDivNavBar,CTO aCto)
	{
		  TmoHtmlDiv mTmoHtmlDivDropDown = new TmoHtmlDiv();
		  mTmoHtmlDivNavBar.add(mTmoHtmlDivDropDown,aCto);
		  mTmoHtmlDivDropDown.setClass("dropdown");
		  
		  TmoHtmlBtn mTmoHtmlBtn = new TmoHtmlBtn();
		  mTmoHtmlDivDropDown.add(mTmoHtmlBtn,aCto);
		  mTmoHtmlBtn.setClass("dropbtn");
		  TmoHtmlString mTmoHtmlString = new TmoHtmlString("Window");
		  mTmoHtmlBtn.add(mTmoHtmlString,aCto);
		  TmoHtmlIdo mIdo = new TmoHtmlIdo();
		  mTmoHtmlBtn.add(mIdo,aCto);
		  mIdo.setClass("fa fa-caret-down");
		  TmoHtmlDiv mTmoHtmlDivContent = new TmoHtmlDiv();
		  mTmoHtmlDivContent.setClass("dropdown-content");
		  mTmoHtmlDivDropDown.add(mTmoHtmlDivContent,aCto);
		  TmoHtmlLink mTmoHtmlLink = new TmoHtmlLink();
		  mTmoHtmlLink.setHRef("insideOverview");
		  mTmoHtmlDivContent.add(mTmoHtmlLink,aCto);
		  TmoHtmlString mLink1 = new TmoHtmlString("Overview");
		  mTmoHtmlLink.add(mLink1,aCto);
	}
	
	protected void buildProfileMenu(TmoHtmlDiv mTmoHtmlDivNavBar,CTO aCto)
	{
		  TmoHtmlDiv mTmoHtmlDivDropDown = new TmoHtmlDiv();
		  mTmoHtmlDivNavBar.add(mTmoHtmlDivDropDown,aCto);
		  mTmoHtmlDivDropDown.setClass("dropdown");
		  
		  TmoHtmlBtn mTmoHtmlBtn = new TmoHtmlBtn();
		  mTmoHtmlDivDropDown.add(mTmoHtmlBtn,aCto);
		  mTmoHtmlBtn.setClass("dropbtn");
		  TmoHtmlString mTmoHtmlString = new TmoHtmlString("Profiles");
		  mTmoHtmlBtn.add(mTmoHtmlString,aCto);
		  TmoHtmlIdo mIdo = new TmoHtmlIdo();
		  mTmoHtmlBtn.add(mIdo,aCto);
		  mIdo.setClass("fa fa-caret-down");
		  TmoHtmlDiv mTmoHtmlDivContent = new TmoHtmlDiv();
		  mTmoHtmlDivContent.setClass("dropdown-content");
		  mTmoHtmlDivDropDown.add(mTmoHtmlDivContent,aCto);
		  TmoHtmlLink mTmoHtmlLink = new TmoHtmlLink();
		  mTmoHtmlLink.setHRef("insideOverview");
		  mTmoHtmlDivContent.add(mTmoHtmlLink,aCto);
		  TmoHtmlString mLink1 = new TmoHtmlString("Overview");
		  mTmoHtmlLink.add(mLink1,aCto);
	}

	protected void buildHelpMenu(TmoHtmlDiv mTmoHtmlDivNavBar,CTO aCto)
	{
		  TmoHtmlDiv mTmoHtmlDivDropDown = new TmoHtmlDiv();
		  mTmoHtmlDivNavBar.add(mTmoHtmlDivDropDown,aCto);
		  mTmoHtmlDivDropDown.setClass("dropdown");
		  
		  TmoHtmlBtn mTmoHtmlBtn = new TmoHtmlBtn();
		  mTmoHtmlDivDropDown.add(mTmoHtmlBtn,aCto);
		  mTmoHtmlBtn.setClass("dropbtn");
		  TmoHtmlString mTmoHtmlString = new TmoHtmlString("Help");
		  mTmoHtmlBtn.add(mTmoHtmlString,aCto);
		  TmoHtmlIdo mIdo = new TmoHtmlIdo();
		  mTmoHtmlBtn.add(mIdo,aCto);
		  mIdo.setClass("fa fa-caret-down");
		  TmoHtmlDiv mTmoHtmlDivContent = new TmoHtmlDiv();
		  mTmoHtmlDivContent.setClass("dropdown-content");
		  mTmoHtmlDivDropDown.add(mTmoHtmlDivContent,aCto);
          addLink(mTmoHtmlDivContent,"Welcome","Welcome",aCto);
          addLink(mTmoHtmlDivContent,"Help Contents","helpContents",aCto);
          addLink(mTmoHtmlDivContent,"Search","Search",aCto);
          addLink(mTmoHtmlDivContent,"Show Context Help","showContextHelp",aCto);
          addLink(mTmoHtmlDivContent,"Tip of the Day","tipOftheDay",aCto);
          addLink(mTmoHtmlDivContent,"Tricks & Tips","trickTips",aCto);
          addLink(mTmoHtmlDivContent,"About R2C","about",aCto);
          
	}
	
	protected void addLink(TmoHtmlDiv mTmoHtmlDivContent,String aLabel,String aRef,CTO aCto)
	{
		  TmoHtmlLink mTmoHtmlLink = new TmoHtmlLink();
		  mTmoHtmlLink.setHRef(aRef);
		  mTmoHtmlDivContent.add(mTmoHtmlLink,aCto);
		  TmoHtmlString mLink1 = new TmoHtmlString(aLabel);
		  mTmoHtmlLink.add(mLink1,aCto);		
	}
	
	protected void addLinkWithBreak(TmoHtmlDiv mTmoHtmlDivContent,String aLabel,String aRef,CTO aCto)
	{
		addLink(mTmoHtmlDivContent,aLabel,aRef,aCto); 
		mTmoHtmlDivContent.add(new TmoHtmlBreak(), aCto);
	}
	
	
	protected void buildMenu(TmoHtmlRoot aRoot,CTO aCto)
	{
		TmoHtmlDiv mTmoHtmlDivDropDown = new TmoHtmlDiv();
		mTmoHtmlDivDropDown.setClass("dropdown");
	}
	
	protected void buildHead(TmoHtmlRoot aRoot,CTO aCto)
	{
		TmoHtmlHead mHtmlHead = new TmoHtmlHead();
		aRoot.add(mHtmlHead,aCto);
		TmoHtmlStyle mTmoHtmlStyle = new TmoHtmlStyle();
		getMenuStyles(mTmoHtmlStyle,aCto);
		mHtmlHead.add(mTmoHtmlStyle,aCto);
	}
	
	protected void getMenuStyles(TmoHtmlStyle aHtmlStyle,CTO aCto)
	{
		List<TmoHtmlCssClass> mCssStyles = R2CHtmlBuildMainMenu.getListOfCssClasses();
		aHtmlStyle.setCssClasses(mCssStyles);
	}
	

}
