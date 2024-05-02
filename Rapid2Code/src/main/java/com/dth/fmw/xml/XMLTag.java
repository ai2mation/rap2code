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

package com.dth.fmw.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import com.dth.fmw.util.KeyValue;

public class XMLTag extends XMLBase implements Iterable<XMLType>
{
  private String tagName;
  private List<XMLType> xmlTypes = new ArrayList<XMLType>();
  private List<KeyValue<String>> attributes = new ArrayList<KeyValue<String>>();
  
  
  public XMLTag(String aTagName)
  {
    tagName = aTagName;
  }
  
  public String getTagName()
  {
    return tagName;
  }
  
  public String getValue()
  {
    StringBuilder mBuilder = new StringBuilder();
    for(XMLType mXMLType:xmlTypes)
    {
      if(mXMLType instanceof XMLValue)
      {
        XMLValue mXMLValue = (XMLValue)mXMLType;
        mBuilder.append(mXMLValue.getValue());
      }
    }
    return mBuilder.toString();
  }
  
  public void add(XMLType aXMLType)
  {
    xmlTypes.add(aXMLType);
  }
  
  public String toXMLString(int aIndentValue,boolean aUseIndent)
  {
    StringBuilder mBuilder = new StringBuilder();
    toXMLString(mBuilder,0,aIndentValue,aUseIndent);
    return mBuilder.toString();
  }  
  
  public String toXMLString(boolean aUseIndent)
  {
    StringBuilder mBuilder = new StringBuilder();
    toXMLString(mBuilder,0,2,aUseIndent);
    return mBuilder.toString();
  }
  
  public String toXMLString()
  {
    StringBuilder mBuilder = new StringBuilder();
    toXMLString(mBuilder);
    return mBuilder.toString();
  }
  
  public void toXMLString(StringBuilder aBuilder)
  {
    toXMLString(aBuilder,0,0,false);
  }
  
  public void toXMLString(StringBuilder aBuilder,int aIndent,int aIndentValue,boolean aUseIndent)
  {
    int mIndent = indent(aBuilder,aIndent,aIndentValue,aUseIndent);
    aBuilder.append("<"+tagName);    
    toXMLStringAttributes(aBuilder);
    aBuilder.append(">"+newLine(aUseIndent));
    for(XMLType mXMLType:xmlTypes)
    {
      mXMLType.toXMLString(aBuilder,mIndent,aIndentValue,aUseIndent);
    }
    mIndent = indent(aBuilder,aIndent,aIndentValue,aUseIndent);
    aBuilder.append("</"+tagName+">"+newLine(aUseIndent));
  }
  
  public void toXMLStringAttributes(StringBuilder aBuilder)
  {
    for(KeyValue<String> mKeyValue:attributes)
    {
      aBuilder.append(" "+mKeyValue.getId()+"'"+mKeyValue.getValue()+"'");
    }
  }
  
  public String getValue(String aPath)
  {
    String mRtnValue = null;
    List<String> mPaths = parsePath(aPath);
    XMLTag mTag = this;
    for(String mName:mPaths)
    {      
      if(tagName.equals(mName))
      {
        mTag = this;
      }
      else
      {
        mTag =  mTag.getXMLTag(mName);
      }
      
      if(mTag == null) break;
    }    
    if(mTag != null)
    {
      mRtnValue = mTag.getValue();
    }
    return mRtnValue;
  }
  
  public List<String> getValues(String aPath)
  {
    List<String> mRtnValue = new ArrayList<String>();
    List<String> mPaths = parsePath(aPath);
    XMLTag mTag = this;
    for(String mName:mPaths)
    {      
      mTag =  mTag.getXMLTag(mName); 
      if(mTag == null) break;
    }    
    if(mTag != null)
    {
      for(XMLType mXMLType:xmlTypes)
      {
        if(mXMLType instanceof XMLValue)
        {
          XMLValue mXMLValue = (XMLValue)mXMLType;
          mRtnValue.add(mXMLValue.getValue());
        }
      }      
    }
    return mRtnValue;
  }  
  
  public XMLTag getXMLTag(String mKey)
  {
    XMLTag mRtnValue = null;
    for(XMLType mXMLType:xmlTypes)
    {
      if(mXMLType instanceof XMLTag)
      {
        XMLTag mXMLTag = (XMLTag)mXMLType;
        if(mKey.equals(mXMLTag.getTagName()))
        {
          mRtnValue = mXMLTag;
          break;
        }
      }
    }
    return mRtnValue;
  }
  
  public XMLTag getXMLTagEndsWith(String mKey)
  {
    XMLTag mRtnValue = null;
    List<String> mPaths = parsePath(mKey);
    for(XMLType mXMLType:xmlTypes)
    {
      if(mXMLType instanceof XMLTag)
      {
        XMLTag mXMLTag = (XMLTag)mXMLType;
        int mCount = mPaths.size();
        boolean mFirstTime = true;
        for(String mPath:mPaths)
        {
        	mCount--;
            if(mPath.endsWith(mXMLTag.getTagName()) || !mFirstTime)
            {              
              if(!mFirstTime)
              {
            	  mXMLTag = mXMLTag.getXMLTagEndsWith(mPath);
            	  mRtnValue = mXMLTag;
            	  if(mCount == 0) break;
              }
              else
              {
            	  mRtnValue = mXMLTag; 
            	  
              }   
              
              mFirstTime = false;
            }
            
        }

      }
    }
    return mRtnValue;
  }
  
  public XMLTag getXMLTagIgnoreCase(String mKey)
  {
    XMLTag mRtnValue = null;
    for(XMLType mXMLType:xmlTypes)
    {
      if(mXMLType instanceof XMLTag)
      {
        XMLTag mXMLTag = (XMLTag)mXMLType;
        if(mKey.equalsIgnoreCase(mXMLTag.getTagName()))
        {
          mRtnValue = mXMLTag;
          break;
        }
      }
    }
    return mRtnValue;
  }  
  
  public static List<String> parsePath(String aValue)
  {
    List<String> mRtnValue = new ArrayList<String>();
    StringTokenizer st = new StringTokenizer(aValue, ".");
    while (st.hasMoreTokens()) 
    {
         String mToken = st.nextToken();
         if(mToken != null) mToken = mToken.trim();
         mRtnValue.add(mToken);
    }    
    return mRtnValue;
  }   
  
  public List<String> getPaths()
  {
    List<String> mRtnValue = new ArrayList<String>();
    for(XMLType mXMLType:xmlTypes)
    {
      if(mXMLType instanceof XMLTag)
      {
        XMLTag mXMLTag = (XMLTag)mXMLType;
        List<String> mResults = mXMLTag.getPaths();
        for(String mValue:mResults)
        {
          if(!("ROW".equals(tagName)))
          {
            mRtnValue.add(tagName+"."+mValue);
          }
          else
          {
            mRtnValue.add(mValue);
          }
          
        }
      }
      else if(mXMLType instanceof XMLValue)
      {
         mRtnValue.add(tagName);
      }
    }
    return mRtnValue;
  }
  
  
  public List<XMLTagPath> getTagPaths(String aTagName)
  {
    List<XMLTagPath> mRtnValue = new ArrayList<XMLTagPath>();    
    for(XMLType mXMLType:xmlTypes)
    {
      if(mXMLType instanceof XMLTag)
      {
        XMLTag mXMLTag = (XMLTag)mXMLType;
        XMLTag mSaveTag = mXMLTag;
        List<String> mResults = mXMLTag.getPaths();
        boolean mFoundPath = false;
        String mFullPath = "";
        for(String mValue:mResults)
        {
          if(aTagName != null && mValue.endsWith(aTagName))
          {
        	mFoundPath = true;
            break;
          }
          else
          {
        	  mFullPath=mValue;
        	  if(mXMLTag.getTagName().equals(mValue))
        	  {
        		  mSaveTag = mXMLTag;
        	  }
        	  else
        	  {
        		  if(mXMLTag.getXMLTagEndsWith(mValue) != null)
        		  {
        			  mSaveTag = mXMLTag.getXMLTagEndsWith(mValue); 
        		  }
        		  else
        		  {
        			  mSaveTag = mXMLTag.getXMLTagEndsWith(mValue); 
        			  System.out.println("why am I here?");
        		  }
        		  
        	  }        	  
          }
        }       
        if(mFoundPath && mRtnValue.add(new XMLTagPath(mFullPath,mSaveTag)));
        mFullPath = "";
      }
    }
    return mRtnValue;
  }
  
  
  public boolean hasChildren()
  {
    return (xmlTypes != null && xmlTypes.size() > 0);
  }
  
  public Iterator<XMLType> iterator()
  {
    Iterator<XMLType> mRtnValue = null;
    if(xmlTypes != null)
    {
      mRtnValue = xmlTypes.iterator();
    }
    return mRtnValue;
  }
  
  public boolean isName(String aName)
  {
    boolean mRtnValue = false;
    if(tagName != null)
    {
      mRtnValue = tagName.equals(aName);
    }
    return mRtnValue;
  }  
  
  public boolean isNameIgnoreCase(String aName)
  {
    boolean mRtnValue = false;
    if(tagName != null)
    {
      mRtnValue = tagName.equalsIgnoreCase(aName);
    }
    return mRtnValue;
  }
  
  public static boolean XMLBuildMethod(String aPath,XMLTag aTag, List<List<XMLTagPath>> aList,String aKey)
  {
	   boolean mRtnValue = false;
	   boolean mInsertData = false;
	    List<XMLTagPath> mTagPathList = new ArrayList<XMLTagPath>();
	    for(XMLType mXMLType:aTag)
	    {
	      if(mXMLType instanceof XMLTag)
	      {
	    	  XMLTag mTag = (XMLTag)mXMLType;
	    	  if(mTag.getTagName().equals(aKey)) 
	    	  {
	    		  mRtnValue = true;
	    		  mInsertData = false;
	    		  break;
	          }
	    	  else
	    	  {
	    		  String mPath = aPath+"."+mTag.getTagName();
	    		  boolean mResults = XMLBuildMethod(mPath,mTag,aList,aKey);
	    		  if(mResults)
	    		  {
	    			  mInsertData = true;
	    		  }
	    		  mTagPathList.add(new XMLTagPath(mPath, mTag));
	    	  }
	      }
	    }
	    if(mInsertData)
	    {
	    	aList.add(mTagPathList);
	    }
	    return mRtnValue;
  }
  
}
