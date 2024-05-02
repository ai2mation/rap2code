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
import java.io.InputStream;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;


public class XMLUtil
{
  
  public static XMLTag parseXml(String aXMLPath)
  {
    InputStream mXMLPath = XMLUtil.class.getResourceAsStream(aXMLPath);
    XMLTag mRtnValue = null;
    SAXParserFactory mSaxParserFactory = SAXParserFactory.newInstance();
    try
    {
      SAXParser mSaxParser = mSaxParserFactory.newSAXParser();
      SaxDefaultHandler mSaxDefaultHandler = new SaxDefaultHandler();
      mSaxParser.parse(new InputSource(mXMLPath), mSaxDefaultHandler);
      mRtnValue = mSaxDefaultHandler.getXMLTag();
    }
    catch(Exception aException)
    {
      System.out.println(aException.toString());
    }
    return mRtnValue;
  }

  private static class SaxDefaultHandler extends DefaultHandler
  {

    private XMLTag             xmlTag   = null;

    private LinkedList<XMLTag> mapStack = new LinkedList<XMLTag>();

    public SaxDefaultHandler()
    {
      super();
    }

    public XMLTag getXMLTag()
    {
      return xmlTag;
    }

    // //////////////////////////////////////////////////////////////////
    // Event handlers.
    // //////////////////////////////////////////////////////////////////

    public void startDocument()
    {
      // xmlTag = new XMLTag("DATA");
      // mapStack.addLast(xmlTag);
    }

    public void endDocument()
    {
      // System.out.println("End document");
    }

    public void startElement(String uri, String name, String qName, Attributes atts)
    {
      if (xmlTag != null)
      {
        mapStack.addLast(xmlTag);
      }
      XMLTag mXMLTag = new XMLTag(qName);
      if (xmlTag != null)
      {
        xmlTag.add(mXMLTag);
      }
      else
      {
        mapStack.addLast(mXMLTag);
      }
      xmlTag = mXMLTag;
    }
    
    public void endElement(String uri, String name, String qName) 
    {
      xmlTag = mapStack.removeLast();  
    }
    
    public void characters(char ch[], int start, int length) 
    {
        StringBuilder mBuilder =new StringBuilder();
        for (int i = start; i < start + length; i++) 
        {
          mBuilder.append(ch[i]);
        }
        String mValue = mBuilder.toString().trim();
        if(mValue != null && mValue.length() > 0)
        {
          XMLValue mXMLValue = new XMLValue(mBuilder.toString().trim());
          xmlTag.add(mXMLValue);
        }
      }    
    }
}
