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

public class TmoHtmliSelect extends TmoHtmlRoot
{
	private static VerdictTrue verdictTrue = new VerdictTrue();
	private String value = null;
	private String name = null;
	private String readonly = null;
	private String disabled = null;
	private String size = null;
	private String maxlength = null;
	private String min = null;
	private String max = null;
	
	
	private String id = null;
	private String accept=null;
	private String src=null;
	private String alt=null;
	private String width=null;
	private String height=null;
	private String autocomplete=null;
	private String autofocus=null;
	private String checked=null;
	private String dirname=null;
	private String  form=null;
	private String formaction=null;
	private String formenctype=null;
	private String formmethod=null;
	private String formnovalidate=null;
	private String formtarget=null;
	private String list = null;
	
	
	//datalist
	

	
	public enum TRAITS {
		VALUE("value"),
		NAME("name"),
		READONLY("readonly"),
		DISABLED("disable"),
		SIZE("size"),
		MAXLENGTH("maxlength"),
		MIN("min"),
		MAX("max"),
		;
		String value;
		TRAITS(String aValue) {
			value = aValue;
		}
		
		String get()
		{
			return value;
		}
	}
	
	public enum TYPES {
		BUTTON("button"),
		CHECKBOX("checkbox"),
		COLOR("color"),
		DATE("date"),
		LOCALDATETIME("datetime-local"),
		EMAIL("email"),
		FILE("file"),
		HIDDEN("hidden"),
		IMAGE("image"),
		MONTH("month"),
		NUMBER("number"),
		PASSWORD("password"),
		RADIO("radio"),
		RANGE("range"),
		RESET("reset"),
		SEARCH("search"),
		SUBMIT("submit"),
		PHONE("tel"),
		TIME("time"),
		URL("url"),
		WEEK("week"),
		;
		private String name = "";
		TYPES(String aName)
		{
			name = aName;
		}
	}
	
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
		aBuilder.append(TmoHtmlTypes.SInput.tag());
		addTraits(aBuilder);
		aBuilder.append(">"+nl);
		super.getContents(aR2CSession ,aCTO,aBuilder);
		return aBuilder;
	}
	
	private void addTraits(DthBuilder aBuilder)
	{
		if(plus(value)) plus(aBuilder,value,TRAITS.VALUE);
		if(plus(name)) plus(aBuilder,name,TRAITS.NAME);
		if(plus(readonly)) plus(aBuilder,readonly,TRAITS.READONLY);
		if(plus(disabled)) plus(aBuilder,disabled,TRAITS.DISABLED);
		if(plus(size)) plus(aBuilder,size,TRAITS.SIZE);
		if(plus(maxlength)) plus(aBuilder,maxlength,TRAITS.MAXLENGTH);
		if(plus(min)) plus(aBuilder,min,TRAITS.MIN);
		if(plus(max)) plus(aBuilder,max,TRAITS.MAX);
	}
	
	private void plus(DthBuilder aBuilder,String aValue,TRAITS aTrait)
	{
		aBuilder.append(" ");
		aBuilder.append(aTrait.get());
		if(aValue.length() > 0)
		{
			aBuilder.append("=");
			aBuilder.append(aValue);			
		}
	}
	
	private boolean plus(String aValue)
	{
	    boolean mRtnValue = false;
	    if(aValue != null)
	    {
	    	mRtnValue = true;
	    }
	    return mRtnValue;
	}
	
	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String isReadonly()
	{
		return readonly;
	}

	public void setReadonly(String readonly)
	{
		this.readonly = readonly;
	}

	public String isDisabled()
	{
		return disabled;
	}

	public void setDisabled(String disabled)
	{
		this.disabled = disabled;
	}

	public String getSize()
	{
		return size;
	}

	public void setSize(String size)
	{
		this.size = size;
	}

	public String getMaxlength()
	{
		return maxlength;
	}

	public void setMaxlength(String maxlength)
	{
		this.maxlength = maxlength;
	}

	public String getMin()
	{
		return min;
	}

	public void setMin(String min)
	{
		this.min = min;
	}

	public String getMax()
	{
		return max;
	}

	public void setMax(String max)
	{
		this.max = max;
	}
	
	/*
	 * <!DOCTYPE html>
<html>
<head>
<style>
.myDiv {
  border: 5px outset red;
  background-color: lightblue;    
  text-align: center;
}
</style>
</head>
<body>

<h1>The div element</h1>

    <div style="width: 100%;">
        <div style="width: 30%; height: 100px; float: left; background: green;"> 
            Left Div 
        </div>
        <div style="margin-left: 10%; height: 100px; background: blue;"> 
            Right Div
        </div>
    </div>

<p>This is some text outside the div element.</p>

</body>
</html>

	 */

}
