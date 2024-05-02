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

package com.dth.fmw.sql.query;

import com.dth.fmw.sql.repo.RepoCriteria;

public class SqlFieldTag extends SqlField 
{
	protected String oFieldTag;
	protected boolean oIsString;

public SqlFieldTag(String aTag,String aName,boolean aIsString) 
{
	super(null, null,null,aName);
	oFieldTag = aTag;
	oIsString = aIsString;
}

public SqlFieldTag(String aTag,String aName,boolean aIsString,int aReformatCode) 
{
	super(null, null,null,aName);
	oFieldTag = aTag;
	oIsString = aIsString;
	setReformatCode(aReformatCode);	
}

public String getValue(RepoCriteria aCriteria)
{
  return aCriteria.getValueAsString(oFieldTag);
}

public boolean isSelectable() 
{
  return false;
}

public boolean isValid(RepoCriteria aCriteria)
{
  boolean mRtnValue = true;
  String mValue = aCriteria.getValueAsString(oFieldTag);
  if(mValue == null) mRtnValue = false;
  return mRtnValue;
}

public boolean toQString(StringBuffer aQueryString,SqlQBase aLastField,RepoCriteria aCriteria)
{
  boolean mRtnValue = false;
  String mValue = aCriteria.getValueAsString(oFieldTag);
  if(mValue != null)
  {
	mValue = reformatData(mValue);
	StringBuffer mQString = new StringBuffer(" ");
	if(oIsString) mQString.append("'");
	mQString.append(mValue);	
	if(oIsString) mQString.append("'");
	mQString.append(" ");
	aQueryString.append(mQString);
	mRtnValue = true;
  }
  return mRtnValue;
}
}
