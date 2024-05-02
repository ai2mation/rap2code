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

package com.dth.fmw.dto;

import java.util.ArrayList;
import java.util.List;
import com.dth.fmw.xml.XMLTag;


public class DTOXmlValue extends DTOBase
{
  private static final long serialVersionUID = 1L;
  private XMLTag xmlValue;
  
  public DTOXmlValue()
  {
    
  }
  
  public DTOXmlValue(String aXmlString)
  {
    setXmlValue(aXmlString);
  }  
  
  public String getXmlValue()
  {
    String mRtnValue = null;
    if(xmlValue != null)
    {
      StringBuilder mBuilder  = new StringBuilder();
      xmlValue.toXMLString(mBuilder, 0, 0, false);
      mRtnValue = mBuilder.toString();
    }
    return mRtnValue;
  }
  
  public String getXmlValue(int aIndent)
  {
    String mRtnValue = null;
    if(xmlValue != null)
    {
      StringBuilder mBuilder  = new StringBuilder();
      xmlValue.toXMLString(mBuilder, 0, aIndent, false);
      mRtnValue = mBuilder.toString();
    }
    return mRtnValue;
  } 
  
  public void setXmlValue(String aXMLXmlValue)
  {
    xmlValue = null;
    if(aXMLXmlValue != null && aXMLXmlValue.length() > 0)
    {
      xmlValue = parseXml(aXMLXmlValue);
    }
  }
  
  public String getXmlValue(String aKey)
  {
    String mRtnValue = null;
    if(xmlValue != null)
    {
      mRtnValue = xmlValue.getValue(aKey);
    }
    return mRtnValue;
  }
  
  public List<String> getXmlValueTags()
  {
    List<String> mRtnValue = new ArrayList<String>();
    if(xmlValue != null)
    {
      mRtnValue = xmlValue.getPaths();
    }
    return mRtnValue;
  }  
  
  public XMLTag getXmlTag()
  {
    return xmlValue;
  }
}
