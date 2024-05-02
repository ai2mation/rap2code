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

public enum TmoHtmlTypes
{
  DocType("<!DOCTYPE html>"),
  SHtml("<html lang\"en\">"),
  EHtml("</html>"),
  SHEAD("<head>"),
  EHEAD("</head>"),
  SBody("<body>"),
  EBody("</body>"),
  SBtn("<button"),
  EBtn("</button>"),
  SStyle("<style>"),
  EStyle("</style>"),
  STable("<Table>"),
  ETable("</Table>"),
  SRow("<tr>"),
  ERow("</tr>"),
  SCol("<td>"),
  ECol("</td>"),
  SDIV("<div"),
  EDIV("</div>"),
  SCENTER("<center>"),
  ECENTER("</center>"),
  SForm("<form action=\"/\">"),
  EForm("<form>"),
  SLabel("<label for=\"%value1\">"),
  ELabel("</label>"),
  SIDO("<i"),
  EIDO("</i>"),
  //<input type="text" id="fname" name="fname"><br><br>
  Input("<input type=\"inputType\" id=\"inputId\" name=\"inputName\" value=\"inputValue\">"),
  Submit("<input type=\"submit\" value=\"%value1\">"),
  SInput("<input"),
  EInput("</Input>"),
  Break("<br>"),
  SH1("<h1>"),
  EH1("</h1>"),
  SH2("<h2>"),
  EH2("</h2>"),

  SH3("<h3>"),
  EH3("</h3>"),
  SH4("<h4>"),
  EH4("</h4>"),

  SH5("<h5>"),
  EH5("</h5>"),
  SH6("<h6>"),
  EH6("</h6>"),
  SIMG("<img"),
  SLink("<a"),
  ELink("</a>"),
  /*
   * <form action="/action_page.php">
  <label for="fname">First name:</label><br>
  <input type="text" id="fname" name="fname" value="John"><br>
  <label for="lname">Last name:</label><br>
  <input type="text" id="lname" name="lname" value="Doe"><br><br>
  <input type="submit" value="Submit">
</form> 
   */
  ;
	private String value;
	
	TmoHtmlTypes(String aTag)
	{
	  value = aTag;	
	}
	
	public String tag()
	{
		return value;
	}
	
	/*
	 * 
	 * void printNumbers(int... numbers) { 
//numbers represents varargs
      for (int num : numbers) {
        System.out.println(num);
    }
	 */
	
	public String tag(String... aValues)
	{
		int mLen = aValues.length;
		String mRtnValue = value;
		for(int mSlot=0;mSlot < mLen;mSlot+=2)
		{
			mRtnValue = mRtnValue.replace(aValues[mSlot],aValues[mSlot+1]);
		}
		return mRtnValue;
	}
}
