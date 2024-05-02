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

package com.dth.core.repo;

import java.sql.SQLException;

import com.dth.core.entity.Agent;
import com.dth.core.entity.Blueprint;

public class RepoCreateTables extends RepoBase
{
	
	public static void main(String[] args) throws ClassNotFoundException
	{
		createTable(Agent.class);
		createTable(Blueprint.class);
	}
	
	public static void createTable(Class<?> aClass) throws ClassNotFoundException
	{
		RepoCreateTables mRepo = new RepoCreateTables();
		try
		{
			boolean mValue = mRepo.CreateTable(aClass);
			String mName = aClass.getSimpleName();
			if(!mValue)
			{
				System.out.println("build failed: "+mName);
			}
			else
			{
				System.out.println("build success: "+mName);
			}
		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
