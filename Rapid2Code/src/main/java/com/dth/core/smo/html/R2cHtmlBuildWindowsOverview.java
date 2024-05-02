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

import java.util.ArrayList;
import java.util.List;

import com.dth.fmw.component.CTO;
import com.dth.fmw.tmo.html.TmoHtmlBody;
import com.dth.fmw.tmo.html.TmoHtmlBreak;
import com.dth.fmw.tmo.html.TmoHtmlCenter;
import com.dth.fmw.tmo.html.TmoHtmlDiv;
import com.dth.fmw.tmo.html.TmoHtmlDoc;
import com.dth.fmw.tmo.html.TmoHtmlForm;
import com.dth.fmw.tmo.html.TmoHtmlFormInput;
import com.dth.fmw.tmo.html.TmoHtmlFormLabel;
import com.dth.fmw.tmo.html.TmoHtmlFormSubmit;
import com.dth.fmw.tmo.html.TmoHtmlH2;
import com.dth.fmw.tmo.html.TmoHtmlImage;
import com.dth.fmw.tmo.html.TmoHtmlMain;
import com.dth.fmw.tmo.html.TmoHtmlRoot;
import com.dth.fmw.tmo.html.TmoHtmlString;

public class R2cHtmlBuildWindowsOverview extends R2cHtmlBuilder
{
	private List<TmoHtmlRoot> htmlList = new ArrayList<>();
	
	public List<TmoHtmlRoot> build(CTO aCto)
	{
	  add(new TmoHtmlDoc());
	  TmoHtmlRoot mMain = add(new TmoHtmlMain());
	  buildHead(mMain,aCto);
	  TmoHtmlRoot mBody = mMain.add(new TmoHtmlBody(),aCto);
	  
	  buildHeader(mBody,aCto);
	  TmoHtmlDiv mTmoHtmlDiv = new TmoHtmlDiv();
	  mTmoHtmlDiv.setStyle("width: 100%;");
	  mBody.add(mTmoHtmlDiv,aCto);
	  TmoHtmlDiv mTmoHtmlDivLeft = new TmoHtmlDiv();
	  mTmoHtmlDivLeft.setStyle("width: 30%; height: 600px; float: left; background: white; border: 2px solid black");
	  mTmoHtmlDiv.add(mTmoHtmlDivLeft,aCto);
	  
	  
	  TmoHtmlDiv mTmoHtmlDivRight = new TmoHtmlDiv();
	  mTmoHtmlDivRight.setStyle("margin-left: 10%; height: 600px; background: white; border: 2px solid black");
	  mTmoHtmlDiv.add(mTmoHtmlDivRight,aCto);
	  getHtmlTemplate(mTmoHtmlDivRight,aCto);
	  /*
	  TmoHtmlRoot mForm =  mTmoHtmlDivLeft.add(new TmoHtmlForm(),aCto);
	  TmoHtmlRoot mLblUserName = mForm.add(new TmoHtmlFormLabel(),aCto);
	  mLblUserName.add(new TmoHtmlString("User Name:"),aCto);
	  
	  mForm.add(new TmoHtmlFormInput(),aCto);
	  mForm.add(new TmoHtmlBreak(),aCto);
	  TmoHtmlRoot mLabelPassword= mForm.add(new TmoHtmlFormLabel(),aCto);
	  mLabelPassword.add(new TmoHtmlString("Password :"),aCto);
	  mForm.add(new TmoHtmlFormInput(),aCto);
	  mForm.add(new TmoHtmlBreak(),aCto);
	  mForm.add(new TmoHtmlFormLabel(),aCto);
	  mForm.add(new TmoHtmlFormSubmit(),aCto);
	  */
	  return htmlList;
	}
	
	public TmoHtmlRoot add(TmoHtmlRoot aRoot)
	{
		htmlList.add(aRoot);
		return aRoot;
	}
	
	List<TmoHtmlRoot> getHtmlList()
	{
		return htmlList;
	}
	
	protected void getHtmlTemplate(TmoHtmlRoot aTmoHtmlDivRight,CTO aCto)
	{
		/*
		  TmoHtmlCenter mTmoHtmlCenter = new TmoHtmlCenter();
		 
		  TmoHtmlImage mTmoHtmlImage= new TmoHtmlImage();
		  mTmoHtmlImage.setSource("./images/rapid2code.gif");
		  mTmoHtmlCenter.add(mTmoHtmlImage,aCto);
		  aTmoHtmlDivRight.add(mTmoHtmlCenter,aCto);
		  */
		  TmoHtmlRoot mHeading = new TmoHtmlH2();
		  mHeading.setStyle("text-align: center;");
		 
		  mHeading.add(new TmoHtmlString("Not Implemented yet!"),aCto);
		 
		  aTmoHtmlDivRight.add(mHeading,aCto);
		 
	}
}
