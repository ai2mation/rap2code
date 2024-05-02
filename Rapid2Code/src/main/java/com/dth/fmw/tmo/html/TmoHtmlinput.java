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

public class TmoHtmlinput extends TmoHtmlRoot
{
	private static VerdictTrue verdictTrue = new VerdictTrue();
	private Object value = null;
	private Object name = null;
	private Object readonly = null;
	private Object disabled = null;
	private Object size = null;
	private Object maxlength = null;
	private Object minlength = null;
	private Object min = null;
	private Object max = null;
	
	
	private Object id = null;
	private Object accept=null;
	private Object src=null;
	private Object alt=null;
	private Object width=null;
	private Object height=null;
	private Object autocomplete=null;
	private Object autofocus=null;
	private Object checked=null;
	private Object dirname=null;
	private Object  form=null;
	private Object formaction=null;
	private Object formenctype=null;
	private Object formmethod=null;
	private Object formnovalidate=null;
	private Object formtarget=null;
	private Object list = null;
	private Object multiple=null;
	private Object pattern=null;
	private Object placeholder=null;
	private Object required=null;
	private Object step=null;
	private Object witdh=null;
	private Object capture=null;
	private Object for_=null;
	private Object rel=null;
	
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
		
		public String getName()
		{
			return name;
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
	
	private void plus(DthBuilder aBuilder,Object aValue,TRAITS aTrait)
	{
		aBuilder.append(" ");
		aBuilder.append(aTrait.get());
		if(aValue instanceof String)
		{
			String mValue = (String)aValue;
			if(mValue.length() > 0)
			{
				aBuilder.append("=");
				aBuilder.append(mValue);			
			}
		}

	}
	
	private boolean plus(Object aValue)
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
		return value.toString();
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getName()
	{
		return name.toString();
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String isReadonly()
	{
		return readonly.toString();
	}

	public void setReadonly(String readonly)
	{
		this.readonly = readonly;
	}

	public String isDisabled()
	{
		return disabled.toString();
	}

	public void setDisabled(String disabled)
	{
		this.disabled = disabled;
	}

	public String getSize()
	{
		return size.toString();
	}

	public void setSize(String size)
	{
		this.size = size;
	}

	public String getMaxlength()
	{
		return maxlength.toString();
	}

	public void setMaxlength(String maxlength)
	{
		this.maxlength = maxlength;
	}

	public String getMin()
	{
		return min.toString();
	}

	public void setMin(String min)
	{
		this.min = min;
	}

	public String getMax()
	{
		return max.toString();
	}

	public void setMax(String max)
	{
		this.max = max;
	}

	public Object getMinlength()
	{
		return minlength;
	}

	public void setMinlength(Object minlength)
	{
		this.minlength = minlength;
	}

	public Object getReadonly()
	{
		return readonly;
	}

	public void setReadonly(Object readonly)
	{
		this.readonly = readonly;
	}

	public Object getDisabled()
	{
		return disabled;
	}

	public void setDisabled(Object disabled)
	{
		this.disabled = disabled;
	}

	public Object getId()
	{
		return id;
	}

	public void setId(Object id)
	{
		this.id = id;
	}

	public Object getAccept()
	{
		return accept;
	}

	public void setAccept(Object accept)
	{
		this.accept = accept;
	}

	public Object getSrc()
	{
		return src;
	}

	public void setSrc(Object src)
	{
		this.src = src;
	}

	public Object getAlt()
	{
		return alt;
	}

	public void setAlt(Object alt)
	{
		this.alt = alt;
	}

	public Object getWidth()
	{
		return width;
	}

	public void setWidth(Object width)
	{
		this.width = width;
	}

	public Object getHeight()
	{
		return height;
	}

	public void setHeight(Object height)
	{
		this.height = height;
	}

	public Object getAutocomplete()
	{
		return autocomplete;
	}

	public void setAutocomplete(Object autocomplete)
	{
		this.autocomplete = autocomplete;
	}

	public Object getAutofocus()
	{
		return autofocus;
	}

	public void setAutofocus(Object autofocus)
	{
		this.autofocus = autofocus;
	}

	public Object getChecked()
	{
		return checked;
	}

	public void setChecked(Object checked)
	{
		this.checked = checked;
	}

	public Object getDirname()
	{
		return dirname;
	}

	public void setDirname(Object dirname)
	{
		this.dirname = dirname;
	}

	public Object getForm()
	{
		return form;
	}

	public void setForm(Object form)
	{
		this.form = form;
	}

	public Object getFormaction()
	{
		return formaction;
	}

	public void setFormaction(Object formaction)
	{
		this.formaction = formaction;
	}

	public Object getFormenctype()
	{
		return formenctype;
	}

	public void setFormenctype(Object formenctype)
	{
		this.formenctype = formenctype;
	}

	public Object getFormmethod()
	{
		return formmethod;
	}

	public void setFormmethod(Object formmethod)
	{
		this.formmethod = formmethod;
	}

	public Object getFormnovalidate()
	{
		return formnovalidate;
	}

	public void setFormnovalidate(Object formnovalidate)
	{
		this.formnovalidate = formnovalidate;
	}

	public Object getFormtarget()
	{
		return formtarget;
	}

	public void setFormtarget(Object formtarget)
	{
		this.formtarget = formtarget;
	}

	public Object getList()
	{
		return list;
	}

	public void setList(Object list)
	{
		this.list = list;
	}

	public Object getMultiple()
	{
		return multiple;
	}

	public void setMultiple(Object multiple)
	{
		this.multiple = multiple;
	}

	public Object getPattern()
	{
		return pattern;
	}

	public void setPattern(Object pattern)
	{
		this.pattern = pattern;
	}

	public Object getPlaceholder()
	{
		return placeholder;
	}

	public void setPlaceholder(Object placeholder)
	{
		this.placeholder = placeholder;
	}

	public Object getRequired()
	{
		return required;
	}

	public void setRequired(Object required)
	{
		this.required = required;
	}

	public Object getStep()
	{
		return step;
	}

	public void setStep(Object step)
	{
		this.step = step;
	}

	public Object getWitdh()
	{
		return witdh;
	}

	public void setWitdh(Object witdh)
	{
		this.witdh = witdh;
	}

	public Object getCapture()
	{
		return capture;
	}

	public void setCapture(Object capture)
	{
		this.capture = capture;
	}

	public Object getFor_()
	{
		return for_;
	}

	public void setFor_(Object for_)
	{
		this.for_ = for_;
	}

	public Object getRel()
	{
		return rel;
	}

	public void setRel(Object rel)
	{
		this.rel = rel;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public void setName(Object name)
	{
		this.name = name;
	}

	public void setSize(Object size)
	{
		this.size = size;
	}

	public void setMaxlength(Object maxlength)
	{
		this.maxlength = maxlength;
	}

	public void setMin(Object min)
	{
		this.min = min;
	}

	public void setMax(Object max)
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
