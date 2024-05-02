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

package com.dth.fmw.sql.database;

public class SqlDbConnectionNode
{
	private SqlDbConnectionNode prev;
	private SqlDbConnectionNode next;
	private SqlDbConnection sqlDbConnection;

	public SqlDbConnectionNode()
	{
		super();
		next = null;
		prev = null;
		sqlDbConnection = null;
	}

	public SqlDbConnectionNode(SqlDbConnection aData)
	{
		super();
		next = null;
		prev = null;
		sqlDbConnection = aData;
	}

	public SqlDbConnection getSqlDbConnection()
	{
		return sqlDbConnection;
	}

	public SqlDbConnectionNode getNext()
	{
		return next;
	}

	public SqlDbConnectionNode getPrev()
	{
		return prev;
	}

	public boolean hasExpired()
	{
		return sqlDbConnection.hasExpired();
	}

	public SqlDbConnection setData(SqlDbConnection aSqlDbConnection)
	{
		sqlDbConnection = aSqlDbConnection;
		return sqlDbConnection;
	}

	public SqlDbConnectionNode setNext(SqlDbConnectionNode aNode)
	{
		next = aNode;
		return this;
	}

	public SqlDbConnectionNode setPrev(SqlDbConnectionNode aNode)
	{
		prev = aNode;
		return this;
	}
}
