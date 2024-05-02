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

package com.dth.fmw.sql.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SortableObject implements Sortable
{
	private Object oSortKey;

	public SortableObject()
	{
		super();
		oSortKey = "";
	}

	public SortableObject(Object aSortKey)
	{
		super();
		oSortKey = aSortKey;
	}

	public void buildKey(String[] attib)
	{
		List<Object> keys = new ArrayList<>();
		for (int Lp0 = 0; Lp0 < attib.length; Lp0++)
		{
			keys.add(invokeMethod(getMethod(attib[Lp0])));
		}
		setSortKey(keys);
	}

	private Method getMethod(String attrib)
	{

		Method method = null;

		try
		{
			Class<Object>[] mTypes = null;
			method = getClass().getMethod(attrib, mTypes);
		} catch (Exception ex)
		{
			System.err.println("Unable to locate method: " + attrib + " for class " + getClass());
		}

		return method;
	}

	/**
	 * Insert the method's description here. Creation date: (6/22/2001 10:12:17 AM)
	 * 
	 * @return Object
	 */
	public Object getSortKey()
	{
		return oSortKey;
	}

	private Object invokeMethod(Method aMethod)
	{
		try
		{
			Object invoke = extracted(aMethod);
			return invoke;
		} catch (NullPointerException ex)
		{
			System.out.println("Method: null not found...");
		} catch (InvocationTargetException e)
		{
			System.out.println("Invocation target for " + getClass());
		} catch (IllegalAccessException e)
		{
			System.out.println("Illegal Acess for " + getClass());
		}
		return null;
	}

	private Object extracted(Method aMethod, Object... args) throws IllegalAccessException, InvocationTargetException
	{
		Object invoke = aMethod.invoke(this, args);
		return invoke;
	}

	public void setSortKey(Object aSortKey)
	{
		oSortKey = aSortKey;
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("Key: " + (oSortKey != null ? oSortKey.toString() : "") + "\r\n");
		return sb.toString();
	}
}
