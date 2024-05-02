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

import java.util.List;


public class SortableList extends SortList
{
	public SortableList()
	{
		super();
	}

	public SortableList(List<Object> aList, boolean sortOn)
	{
		super(aList, sortOn);
	}

	public SortableList(List<Object> aList, boolean sortOn, boolean ascendingOn)
	{
		super(aList, sortOn, ascendingOn);
	}

	public SortableList(boolean sortOn)
	{
		super(sortOn);
	}

	public SortableList(boolean sortOn, boolean ascendingOn)
	{
		super(sortOn, ascendingOn);
	}

	public void addElement(Object aSortableObject)
	{
		if (getList().size() == 0 || !isSortOn())
		{
			getList().add(aSortableObject);
		} else
		{
			int count = getList().size();
			boolean inserted = false;
			for (int Lp0 = 0; Lp0 < count; Lp0++)
			{
				Object mBase = ((Sortable) getList().get(Lp0)).getSortKey();
				int mResult = compareObject(((Sortable) aSortableObject).getSortKey(), mBase, true);
				if (isAscending())
				{
					if (mResult < 0)
					{
						getList().add(Lp0, aSortableObject);
						Lp0 = count;
						inserted = true;
					}
				} else
				{
					if (mResult > 0)
					{
						getList().add(Lp0, aSortableObject);
						Lp0 = count;
						inserted = true;
					}
				}
			}
			if (!inserted)
			{
				getList().add(aSortableObject);
			}
		}
	}

	public String toString()
	{
		return super.toString();
	}
}
