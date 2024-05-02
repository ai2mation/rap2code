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

package com.dth.core.build;

import java.util.Map;

import com.dth.core.db.R2CodeDbManager;
import com.dth.core.json.JsonReader;
//import com.dth.fmw.repo.DthMySqlSession;
import com.dth.fmw.sql.database.SqlDBManager;

public class BuildTables
{

	public static void main(String[] args)
	{
		Map<String,Object> mValue = JsonReader.getConfiguration();
		System.out.println(mValue.toString());
		Object mObject = mValue.get("configurations");
		System.out.println(mObject.toString());
		try
		{
			@SuppressWarnings("unchecked")
			Map<String,Object> mMap = (Map<String,Object>)mObject;
			System.out.println(mMap.toString());
			mObject = mMap.get("r2cdb");
			@SuppressWarnings("unchecked")
			Map<String,Object> mR2cDbMap = (Map<String,Object>)mObject;
			String mUserName = (String)mR2cDbMap.get("UserName");
			String mPassword = (String)mR2cDbMap.get("Password");
			String mUrl = (String)mR2cDbMap.get("Url");
			
			SqlDBManager mSqlDBManager = new R2CodeDbManager(mUrl);
			mSqlDBManager.buildConnection("r2cdb",mUserName, mPassword, false);
		}
		catch(Exception aException)
		{
			
		}
	}

}
