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
import com.dth.fmw.logger.SmartLogger;
import com.dth.fmw.smo.Verdict;
import com.dth.fmw.smo.VerdictTrue;
import com.dth.fmw.util.DthBuilder;

public class TmoHtmlFormInput extends TmoHtmlRoot
{
	private static VerdictTrue verdictTrue = new VerdictTrue();
	
	private String id = "id";
	private String name = "name";
	private String value = "";
	private String type = "text";
	
	public TmoHtmlFormInput()
	{
		
	}
	
	public TmoHtmlFormInput(String aId,String aName)
	{
		id = aId;
		name = aName;
	}
	
	public TmoHtmlFormInput(String aId,String aName,String aValue)
	{
		id = aId;
		name = aName;
		value = aValue;
	}
	
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public Verdict predicate(R2CSession aR2CSession,CTO aCTO)
	{
		SmartLogger.Trace(1,"");
		return verdictTrue;
	}
	
	protected String getTextValue(R2CSession aR2CSession,CTO aCto)
	{
		SmartLogger.Trace(1,"");
		DthBuilder mContents = new DthBuilder();
		mContents.append(TmoHtmlTypes.Input.tag()+nl);
		SmartLogger.Trace(2,"");
		return mContents.toString();
	}

	
	public DthBuilder getContents(R2CSession aR2CSession,CTO aCTO,DthBuilder aBuilder)
	{
		SmartLogger.Trace(1,"");
		//Input("<input type=\"inputType\" id=\"inputId\" name=\"inputName\" value=\"inputValue\">"),
		String mId = this.getId();
		String mName = this.getName();
		String mType = this.getType();
		aBuilder.append(TmoHtmlTypes.Input.tag("inputType",mType,"inputId",mId,"inputName",mName,"inputValue",value)+nl);
		SmartLogger.Trace(2,"");
		return aBuilder;
	}

}
