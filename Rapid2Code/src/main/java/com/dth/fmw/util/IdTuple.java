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

package com.dth.fmw.util;

public class IdTuple<ID, VALUE1, VALUE2>
{
	private ID id;
	private VALUE1 value1;
	private VALUE2 value2;

	IdTuple()
	{
		// empty method
	}

	IdTuple(ID aId, VALUE1 aValue1, VALUE2 aValue2)
	{
		this();
		id = aId;
		value1 = aValue1;
		value2 = aValue2;
	}

	public ID getId()
	{
		return id;
	}

	public void setId(ID id)
	{
		this.id = id;
	}

	public VALUE1 getValue1()
	{
		return value1;
	}

	public void setValue1(VALUE1 value1)
	{
		this.value1 = value1;
	}

	public VALUE2 getValue2()
	{
		return value2;
	}

	public void setValue2(VALUE2 value2)
	{
		this.value2 = value2;
	}

}