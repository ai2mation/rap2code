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

public class DTOListMetaValuesIterator implements Iterator<DTOMetaValues>
{
    private DTOList<DTOValues> listOfValues;
	private Set<String>  columnSet;
	private int location = -1;
	
	DTOListMetaValuesIterator(DTOListMetaValues aListMetaValues)
	{
		listOfValues = aListMetaValues.getValues();
		columnSet = aListMetaValues.getColumns();		
	}

	@Override
	public boolean hasNext() 
	{
		boolean mRtnValue = false;
		if(listOfValues != null && location+1 < listOfValues.size()) mRtnValue = true;
		return mRtnValue;
	}

	@Override
	public DTOMetaValues next() 
	{
		location++;		
		return new DTOMetaValues(columnSet, listOfValues.get(location));
	}

	@Override
	public void remove() 
	{
		//empty method
	}

}
