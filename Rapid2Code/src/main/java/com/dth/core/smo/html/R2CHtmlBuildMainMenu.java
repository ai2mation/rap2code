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

import com.dth.fmw.tmo.html.TmoHtmlCssClass;

public class R2CHtmlBuildMainMenu
{

	public static List<TmoHtmlCssClass> getListOfCssClasses()
	{
		List<TmoHtmlCssClass> mRtnValue = new ArrayList<>();
		mRtnValue.add(R2CHtmlBuildMainMenu.buildNavBar());
		mRtnValue.add(R2CHtmlBuildMainMenu.buildNavBarA());
		mRtnValue.add(R2CHtmlBuildMainMenu.buildDropDown());
		mRtnValue.add(R2CHtmlBuildMainMenu.buildDropDownDropBtn());
		mRtnValue.add(R2CHtmlBuildMainMenu.buildNavBarHoverDropDown());
		mRtnValue.add(R2CHtmlBuildMainMenu.buildDropdownContent());
		mRtnValue.add(R2CHtmlBuildMainMenu.buildDropdownContenta());
		mRtnValue.add(R2CHtmlBuildMainMenu.buildDropdownContentHover());
		mRtnValue.add(R2CHtmlBuildMainMenu.buildDropdownHoverDropdownContent());
		return mRtnValue;
	}

	private static TmoHtmlCssClass buildNavBar()
	{
		TmoHtmlCssClass mCssClass = new TmoHtmlCssClass("navbar");
		mCssClass.add("overflow", "hidden");
		mCssClass.add("background-color", "#333");
		return mCssClass;
	}

	private static TmoHtmlCssClass buildNavBarA()
	{
		TmoHtmlCssClass mCssClass = new TmoHtmlCssClass("navbar a");
		mCssClass.add("float", "left");
		mCssClass.add("font-size", "16px");
		mCssClass.add("color", "white");
		mCssClass.add("text-align", "center");
		mCssClass.add("padding", "14px 16px");
		mCssClass.add("test-decoration", "none");
		return mCssClass;
	}

	private static TmoHtmlCssClass buildDropDown()
	{
		TmoHtmlCssClass mCssClass = new TmoHtmlCssClass("dropdown");
		mCssClass.add("float", "left");
		mCssClass.add("overflow", "hidden");
		return mCssClass;
	}

	private static TmoHtmlCssClass buildDropDownDropBtn()
	{
		TmoHtmlCssClass mCssClass = new TmoHtmlCssClass("dropdown .dropbtn");
		mCssClass.add("font-size", "16px");
		mCssClass.add("border", "none");
		mCssClass.add("outline", "none");
		mCssClass.add("color", "white");
		mCssClass.add("padding", "14px 16px");
		mCssClass.add("background-color", "inherit");
		mCssClass.add("font-family", "inherit");
		mCssClass.add("margin", "0");
		return mCssClass;
	}

	private static TmoHtmlCssClass buildNavBarHoverDropDown()
	{
		TmoHtmlCssClass mCssClass = new TmoHtmlCssClass("navbar a:hover, .dropdown:hover .dropbtn");
		mCssClass.add("background-color", "red");
		return mCssClass;
	}

	private static TmoHtmlCssClass buildDropdownContent()
	{
		TmoHtmlCssClass mCssClass = new TmoHtmlCssClass("dropdown-content");
		mCssClass.add("display", "none");
		mCssClass.add("position", "absolute");
		mCssClass.add("background-color", "#f9f9f9");
		mCssClass.add("min-width", "160px");
		mCssClass.add("box-shadow", "0px 8px 16px 0px rgba(0,0,0,0.2)");
		mCssClass.add("z-index", "1");
		return mCssClass;
	}

	private static TmoHtmlCssClass buildDropdownContenta()
	{
		TmoHtmlCssClass mCssClass = new TmoHtmlCssClass("dropdown-content a");
		mCssClass.add("float", "none");
		mCssClass.add("color", "black");
		mCssClass.add("padding", "12px 16px");
		mCssClass.add("text-decoration", "none");
		mCssClass.add("display", "block");
		mCssClass.add("text-align", "left");
		return mCssClass;
	}

	private static TmoHtmlCssClass buildDropdownContentHover()
	{
		TmoHtmlCssClass mCssClass = new TmoHtmlCssClass("dropdown-content a:hover");
		mCssClass.add("background-color", "#ddd");
		return mCssClass;
	}

	private static TmoHtmlCssClass buildDropdownHoverDropdownContent()
	{
		TmoHtmlCssClass mCssClass = new TmoHtmlCssClass("dropdown:hover .dropdown-content");
		mCssClass.add("display", "block");
		return mCssClass;
	}
}
