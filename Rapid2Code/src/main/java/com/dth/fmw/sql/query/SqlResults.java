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

package com.dth.fmw.sql.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dth.fmw.sql.common.SortableSet;

public class SqlResults
{
	private int oSlot = -1;
	private boolean mProcessed = false;
	private List<String> columNames = new ArrayList<>();
	private List<String> queryNames = new ArrayList<>();
	private SortableSet resultSet = new SortableSet(false);
	protected String resultName = "";
	private int posn = -1;
	private ResultSet oResultSet = null;
	private int[] sortOrder = null;
	private int oMaxRows = 0;
	private boolean oUseResultSetOnly = false;
	private Statement statement = null;

	public SqlResults()
	{
		super();
	}

	public SqlResults(int aMaxRows)
	{
		super();
		setMaxRows(aMaxRows);
	}

	public SqlResults(ResultSet aResultSet)
	{
		oResultSet = aResultSet;
		oSlot = -1;
		posn = -1;
	}

	public SqlResults(ResultSet aResultSet, SqlResults aResults)
	{
		oResultSet = aResultSet;
		resultSet = aResults.resultSet;
		columNames = aResults.columNames;
		posn = aResults.posn;
		oSlot = -1;
		posn = -1;
	}

	public SqlResults(boolean sortOn)
	{
		super();
		resultSet.setSort(sortOn);
	}

	public SqlResults(boolean sortOn, int aMaxRows)
	{
		super();
		resultSet.setSort(sortOn);
		setMaxRows(aMaxRows);
	}

	public SqlResults(boolean sortOn, boolean useResultSetOnly)
	{
		super();
		resultSet.setSort(sortOn);
		useResultSetOnly(useResultSetOnly);
	}

	public SqlResults(boolean sortOn, boolean useResultSetOnly, int maxRows)
	{
		super();
		resultSet.setSort(sortOn);
		useResultSetOnly(useResultSetOnly);
		setMaxRows(maxRows);
	}

	private void buildRowKey(SqlResultRow aRow)
	{
		if (isSortOn())
		{
			List<String> keys = new ArrayList<String>();
			int noOfKeys = sortOrder.length;
			for (int Lp1 = 0; Lp1 < noOfKeys; Lp1++)
			{
				SqlResultColumn mColumn = aRow.getColumnAt(sortOrder[Lp1]);
				keys.add(mColumn.getData());
			}
			aRow.setSortKey(keys);
		}
	}

	public void closeResults()
	{
		closeStatement();
		resultSet = new SortableSet(false);
		columNames = new ArrayList<>();
	}

	public void closeStatement()
	{
		try
		{
			if (getStatement() != null)
			{
				getStatement().close();
				setStatement(null);
			}
		} catch (SQLException aException)
		{
		}
	}

	private int compareString(String aString1, String aString2)
	{
		int mRtnValue = 0;
		if (aString1 != null && aString2 != null)
		{
			mRtnValue = aString1.compareTo(aString2);
		} else
		{
			if (aString1 != null && aString2 == null)
			{
				mRtnValue = 1;
			} else if (aString1 == null && aString2 != null)
			{
				mRtnValue = -1;
			}
		}
		return mRtnValue;
	}

	public SqlResultRow findFirstRowMatching(String aColumnName, String aSearchValue)
	{
		int columnIndex = getColNameIndex(aColumnName);
		SqlResultRow mRtnValue = null;
		int mSize = resultSet.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlResultRow mRow = (SqlResultRow) resultSet.elementAt(Lp0);
			SqlResultColumn mColumn = mRow.getColumnAt(columnIndex);
			int mValue = compareString(aSearchValue, mColumn.getData());
			if (mValue == 0)
			{
				mRtnValue = mRow;
				Lp0 = mSize;
			}
		}
		return mRtnValue;
	}

	public SqlResultRow findKeyedRow(SqlResultRow aRow)
	{
		SqlResultRow mRtnValue = null;
		if (resultSet.isSortOn())
		{
			buildRowKey(aRow);
			List<Object> rows = resultSet.get(aRow);
			if (rows != null && rows.size() > 0)
			{
				mRtnValue = (SqlResultRow) rows.get(0);
			}
		}
		return mRtnValue;
	}

	public List<SqlResultRow> findRowsMatching(String aColumnName, String aSearchValue)
	{
		int columnIndex = getColNameIndex(aColumnName);
		List<SqlResultRow> mRtnValue = new ArrayList<>();
		int mSize = resultSet.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			SqlResultRow mRow = (SqlResultRow) resultSet.elementAt(Lp0);
			SqlResultColumn mColumn = mRow.getColumnAt(columnIndex);
			int mValue = compareString(aSearchValue, mColumn.getData());
			if (mValue == 0)
			{
				mRtnValue.add(mRow);
			}
		}
		return mRtnValue;
	}

	public String getColName(int aIndex)
	{
		String mRtnValue = "";
		int mSize = columNames.size();
		if (aIndex < mSize)
		{
			mRtnValue = (String) columNames.get(aIndex);
		}
		return mRtnValue;
	}

	public int getColNameIndex(String aColumnName)
	{
		int mRtnValue = -1;
		int mSize = columNames.size();
		for (int Lp0 = 0; Lp0 < mSize; Lp0++)
		{
			String mColumnName = (String) columNames.get(Lp0);
			if (mColumnName.equalsIgnoreCase(aColumnName))
			{
				mRtnValue = Lp0;
				Lp0 = mSize;
			}
		}
		return mRtnValue;
	}

	public int getColNamePosition()
	{
		return posn;
	}

	public String getFirstColName()
	{
		String mRtnValue = null;
		posn = -1;
		if (columNames.size() > 0)
		{
			try
			{
				mRtnValue = (String) columNames.get(0);
				if (mRtnValue != null)
					posn = 0;
			} catch (ClassCastException aException)
			{
				posn = -1;
			}
		}
		return mRtnValue;
	}

	public String getFirstQueryName()
	{
		String mRtnValue = null;
		posn = -1;
		if (queryNames.size() > 0)
		{
			try
			{
				mRtnValue = (String) queryNames.get(0);
				if (mRtnValue != null)
					posn = 0;
			} catch (ClassCastException aException)
			{
				posn = -1;
			}
		}
		return mRtnValue;
	}

	public SqlResultRow getFirstRow() throws SQLException
	{
		SqlResultRow mRtnValue = null;
		if (isUseResultSetOnly())
		{
			mRtnValue = getFirstSQLRow();
		} else
		{
			mRtnValue = getFirstSqlRow();
		}
		return mRtnValue;
	}

	private SqlResultRow getFirstSQLRow() throws SQLException
	{
		SqlResultRow mRtnValue = null;
		if (oResultSet != null)
		{
			boolean mFound = oResultSet.next();
			if (mFound)
			{
				mRtnValue = new SqlResultRow();
				int mSize = queryNames.size();
				for (int Lp0 = 0; Lp0 < mSize; Lp0++)
				{
					String mQueryName = (String) queryNames.get(Lp0);
					String mData = oResultSet.getString(mQueryName);
					SqlResultColumn mColumn = new SqlResultColumn(mQueryName, mData);
					mRtnValue.insertColumn(mColumn);
				}
			}
		}
		return mRtnValue;
	}

	private SqlResultRow getFirstSqlRow() throws SQLException
	{
		SqlResultRow mRtnValue = null;
		if (!mProcessed)
			processResultSet();
		int mSize = resultSet.size();
		if (mSize > 0)
		{
			oSlot = 1;
			mRtnValue = (SqlResultRow) resultSet.firstElement();
		}
		return mRtnValue;
	}

	public int getMaxRows()
	{
		return oMaxRows;
	}

	public String getNextColName()
	{
		String mRtnValue = null;
		if (posn + 1 < columNames.size())
			;
		{
			try
			{
				mRtnValue = (String) columNames.get(++posn);
			} catch (ClassCastException aException)
			{
			}
		}
		return mRtnValue;
	}

	public String getNextQueryName()
	{
		String mRtnValue = null;
		if (posn + 1 < queryNames.size())
			;
		{
			try
			{
				mRtnValue = (String) queryNames.get(++posn);
			} catch (ClassCastException aException)
			{
			}
		}
		return mRtnValue;
	}

	public SqlResultRow getNextRow() throws SQLException
	{
		SqlResultRow mRtnValue = null;
		if (isUseResultSetOnly())
		{
			mRtnValue = getNextSQLRow();
		} else
		{
			mRtnValue = getNextVectorRow();
		}
		return mRtnValue;
	}

	private SqlResultRow getNextSQLRow() throws SQLException
	{
		SqlResultRow mRtnValue = null;
		if (oResultSet != null)
		{
			boolean mFound = oResultSet.next();
			if (mFound)
			{
				mRtnValue = new SqlResultRow();
				int mSize = queryNames.size();
				for (int Lp0 = 0; Lp0 < mSize; Lp0++)
				{
					String mQueryName = (String) queryNames.get(Lp0);
					String mData = oResultSet.getString(mQueryName);
					SqlResultColumn mColumn = new SqlResultColumn(mQueryName, mData);
					mRtnValue.insertColumn(mColumn);
				}
			} else
			{
				closeStatement();
			}
		}
		return mRtnValue;
	}

	private SqlResultRow getNextVectorRow() throws SQLException
	{
		SqlResultRow mRtnValue = null;
		int mSize = resultSet.size();
		if (mProcessed && mSize > oSlot && oSlot > -1)
		{
			mRtnValue = (SqlResultRow) resultSet.elementAt(oSlot++);
		}
		return mRtnValue;
	}

	public int getNumberOfRows()
	{
		return resultSet.size();
	}

	public String getResultName()
	{
		return resultName;
	}

	public Statement getStatement()
	{
		return statement;
	}

	public void insertColumnName(String aName)
	{
		columNames.add(aName);
	}

	public void insertQueryName(String aName)
	{
		queryNames.add(aName);
	}

	public void insertResults(ResultSet aResultSet) throws SQLException
	{
		oResultSet = aResultSet;
		oSlot = -1;
		posn = -1;
		processResultSet();
	}

	public boolean insertRow(SqlResultRow aRow)
	{
		boolean mRtnValue = false;
		if (getMaxRows() == 0 || (getNumberOfRows() <= getMaxRows()))
		{
			buildRowKey(aRow);
			resultSet.addElement(aRow);
			mProcessed = true;
			mRtnValue = true;
		}
		return mRtnValue;
	}

	public boolean isSortOn()
	{
		return resultSet.isSortOn();
	}

	public boolean isUseResultSetOnly()
	{
		return oUseResultSetOnly;
	}

	public void processResultSet() throws SQLException
	{
		if (!oUseResultSetOnly)
		{
			SqlResultRow mRow = getFirstSQLRow();
			while (mRow != null)
			{
				if (insertRow(mRow))
				{
					mRow = getNextSQLRow();
				} else
				{
					mRow = null;
				}
			}
			mProcessed = true;
			if (oResultSet != null)
				oResultSet.close();
			oResultSet = null;
		}
	}

	public void setMaxRows(int maxRows)
	{
		oMaxRows = maxRows;
	}

	public void setResultName(String aName)
	{
		resultName = aName;
	}

	public void setSortOrder(int[] aSortOrder)
	{
		sortOrder = aSortOrder;
		setSortOrderOn();
		sortRows();
	}

	public boolean setSortOrderOff()
	{
		boolean mRtnVal = false;
		if (sortOrder.length > 0)
		{
			resultSet.setSort(false);
			mRtnVal = true;
		}
		return mRtnVal;
	}

	public boolean setSortOrderOn()
	{
		boolean mRtnVal = false;
		if (sortOrder.length > 0)
		{
			if (!isSortOn())
			{
				resultSet.setSort(true);
			}
			mRtnVal = true;
		}
		return mRtnVal;
	}

	public void setStatement(Statement aStatement)
	{
		statement = aStatement;
	}

	private void sortRows()
	{
		if (setSortOrderOn())
		{
			int mSize = resultSet.size();
			for (int Lp0 = 0; Lp0 < mSize; Lp0++)
			{
				SqlResultRow mRow = (SqlResultRow) resultSet.elementAt(Lp0);
				buildRowKey(mRow);
			}
		}
	}

	public String toString()
	{
		return "\r\nResultSet with " + resultSet.size() + " rows\r\n";
	}

	public void useResultSetOnly(boolean useResultSetOnly)
	{
		oUseResultSetOnly = useResultSetOnly;
	}
}
