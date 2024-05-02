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


public abstract class XMLBase implements XMLType
{
  private static String newline = System.getProperty("line.separator");
  
  public static String newLine(boolean aValue)
  {
    String mRtnValue = "";
    if(aValue) mRtnValue = newline;
    return mRtnValue;
  }
  
  public int indent(StringBuilder aBuilder,int aIndent,int aIndentValue,boolean aUseIndent)
  {
    int mRtnValue = aIndent+aIndentValue;
    if(aUseIndent)
    {
      for(int mLp0=0;mLp0<mRtnValue;mLp0++)
      {
        aBuilder.append(" ");
      }
    }
    return mRtnValue;
  } 
}
