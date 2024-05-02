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

import java.util.Iterator;
import java.util.Set;

public class DTOMetaValueIterator implements Iterator<DTOMetaValue>
{
	private Object[] columns;
	private DTOValues values;
	private int location = -1;
	
	DTOMetaValueIterator(Set<String> aColumns,DTOValues aValues)
	{
		columns = aColumns.toArray();
		values = aValues;
	}
	
	@Override
	public boolean hasNext() 
	{
		boolean mRtnValue = false;
		if(columns != null && location+1 < columns.length)
		{
			mRtnValue = true;
		}
		return mRtnValue;
	}

	@Override
	public DTOMetaValue next() 
	{
		DTOMetaValue mRtnValue = null;
		location++;
		Object mObject = columns[location];
		if(mObject instanceof String)
		{
			String mKey = (String)mObject;
			Object mValue = values.getValue(mKey);
			mRtnValue = new DTOMetaValue(mKey,mValue);
		}		
		return mRtnValue;
	}

	@Override
	public void remove() 
	{
		//empty method
	}

}
