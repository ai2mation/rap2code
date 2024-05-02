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

import com.dth.fmw.component.CTO;
import com.dth.fmw.smo.Verdict;
import com.dth.fmw.smo.VerdictTrue;
import com.dth.fmw.util.DthBuilder;

public class TmoHtmlProps
{
	private static VerdictTrue verdictTrue = new VerdictTrue();
	private List<TmoHtmlProp> listOfProps= new ArrayList<>();
	public static String nl = System.lineSeparator();
	
	public Verdict predicate(CTO aCTO)
	{
		return verdictTrue;
	}
	
	
	public DthBuilder getContents(CTO aCTO)
	{
		DthBuilder mBuilder = new DthBuilder();
		for(TmoHtmlProp mProp: listOfProps)
		{
			mBuilder.append(" "+mProp.prop()+nl);
		}
		return mBuilder;
	}

	public TmoHtmlProps add(String aTrait,String aValue)
	{
		TmoHtmlProp mProp = new TmoHtmlProp(aTrait,aValue);
		listOfProps.add(mProp);
		return this;
	}

}
