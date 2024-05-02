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

package com.dth.fmw.html2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HtmlForm implements FormContent
{
	public String action;
	public String method;
	public String target;

	protected List<FormContent> contents;

	private int indent;

	public HtmlForm()
	{
		super();
	}

	public void add(FormContent content)
	{
		if (contents == null)
		{
			contents = new ArrayList<>();
		}

		contents.add(content);
	}

	public void setFormIndent(int indent)
	{
		this.indent = indent;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();

		if (indent > 0)
		{
			for (int i = 0; i < indent; i++)
			{
				buffer.append(" ");
			}
		}

		// create form tag
		buffer.append("<FORM");

		if (method != null)
		{
			buffer.append(" METHOD=");
			buffer.append(method);
		}

		if (action != null)
		{
			buffer.append(" ACTION=\"");
			buffer.append(action);
			buffer.append("\"");
		}

		if (target != null)
		{
			buffer.append(" TARGET=\"");
			buffer.append(target);
			buffer.append("\"");
		}

		buffer.append(">\n");

		Iterator<FormContent> contentList = contents.iterator();
		FormContent formItem = null;
		int childIndent = indent + 2;

		while (contentList.hasNext())
		{
			formItem = contentList.next();
			formItem.setFormIndent(childIndent);
			buffer.append(formItem.toString());
			buffer.append("\n");
		}

		// write end tag
		buffer.append("</FORM>");

		return buffer.toString();
	}
}
