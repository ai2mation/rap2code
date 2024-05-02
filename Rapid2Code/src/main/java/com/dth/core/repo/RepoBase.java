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

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.dth.core.db.R2CodeDbManager;
import com.dth.core.json.JsonReader;
import com.dth.core.mdl.qry.SqlFieldList;
import com.dth.core.mdl.qry.SqlFieldNode;
import com.dth.fmw.sql.database.SqlDBManager;
import com.dth.fmw.sql.database.SqlDbConnection;

public class RepoBase
{
	public boolean CreateTable(Class<?> aClass) throws ClassNotFoundException, SQLException
	{
		dropTable(aClass);
		StringBuilder mBuilder = new StringBuilder();
		String mTableName = aClass.getSimpleName();
		mBuilder.append("CREATE TABLE ");
		mBuilder.append(mTableName);
		mBuilder.append("(");
		SqlFieldList mSqlFieldList=processClass(aClass);
		
		mBuilder.append(mSqlFieldList.createTableColumns(mTableName,mTableName));
		mBuilder.append(",PRIMARY KEY ( id )");
		mBuilder.append(")");
		SqlDbConnection mSqlDbConnection = buildConnection();
		boolean mRtnValue = mSqlDbConnection.executeStmt(mBuilder.toString());
		return mRtnValue;
	}
	
	public boolean dropTable(Class<?> aClass)
	{
		boolean mRtnValue = true;
		StringBuilder mBuilder = new StringBuilder();
		String mTableName = aClass.getSimpleName();
		mBuilder.append("DROP TABLE ");
		mBuilder.append(mTableName);
		SqlDbConnection mSqlDbConnection = buildConnection();
		try
		{
			mRtnValue = mSqlDbConnection.executeStmt(mBuilder.toString());
		} catch (SQLException e)
		{
			mRtnValue = false;
		}
		return mRtnValue;
	}
	
	public SqlFieldList processClass(Class<?> mClass) throws ClassNotFoundException
	{
		SqlFieldList mRtnValue = new SqlFieldList();
		processClasses(mClass,mRtnValue);
		return mRtnValue;
        
	}
	
	public void processClasses(Class<?> aClass,SqlFieldList aSqlFieldList)
	{
		if(aClass != Object.class)
		{
			
			Class<?> mSuperClass = aClass.getSuperclass();
			Field[] mFields = aClass.getDeclaredFields();
			getFields(mFields,aSqlFieldList);
			processClasses(mSuperClass,aSqlFieldList);
		}
	}
	
	public void getFields(Field[] mFields,SqlFieldList aSqlFieldList)
	{
		for(Field mField:mFields)
		{
			mField.setAccessible(true);
			String mName = mField.getName();
			Class<?> mClassType = mField.getType();
			String mClassName = mClassType.getName();
			String mSqlName = translateToSql(mClassName);
			SqlFieldNode mNode = new SqlFieldNode();
			mNode.setName(mName);
			mNode.setJavaType(mClassName);
			mNode.setSqlType(mSqlName);
			aSqlFieldList.addNode(mNode);
		}
	}
	
	private String translateToSql(String aValue)
	{
		String mRtnValue = "unknown";
		String mValue = JAVA_TO_SQL_MAP.get(aValue);
		if(mValue != null)
		{
			mRtnValue = mValue;
		}
		return mRtnValue;
	}
	
	private static final Map<Class<?>, Class<?>> WRAPPER_TYPE_MAP;
	static {
	    WRAPPER_TYPE_MAP = new HashMap<>();
	    WRAPPER_TYPE_MAP.put(Integer.class, int.class);
	    WRAPPER_TYPE_MAP.put(Byte.class, byte.class);
	    WRAPPER_TYPE_MAP.put(Character.class, char.class);
	    WRAPPER_TYPE_MAP.put(Boolean.class, boolean.class);
	    WRAPPER_TYPE_MAP.put(Double.class, double.class);
	    WRAPPER_TYPE_MAP.put(Float.class, float.class);
	    WRAPPER_TYPE_MAP.put(Long.class, long.class);
	    WRAPPER_TYPE_MAP.put(Short.class, short.class);
	    WRAPPER_TYPE_MAP.put(Void.class, void.class);
	}
	
	private static final Map<String,String> JAVA_TO_SQL_MAP;
	static {
		JAVA_TO_SQL_MAP = new HashMap<>();
		JAVA_TO_SQL_MAP.put("long","INTEGER");
		JAVA_TO_SQL_MAP.put("java.lang.String","VARCHAR(255)");
		JAVA_TO_SQL_MAP.put("java.util.Date","DATE");
		/*
		JAVA_TO_SQL_MAP.put("long","long");
		JAVA_TO_SQL_MAP.put("long","long");
		JAVA_TO_SQL_MAP.put("long","long");
		JAVA_TO_SQL_MAP.put("long","long");
		JAVA_TO_SQL_MAP.put("long","long");
		JAVA_TO_SQL_MAP.put("long","long");
*/
	}
	
	public static SqlDbConnection buildConnection()
	{
		SqlDbConnection mRtnValue = null;
		Map<String,Object> mValue = JsonReader.getConfiguration();
		Object mObject = mValue.get("configurations");
		try
		{
			@SuppressWarnings("unchecked")
			Map<String,Object> mMap = (Map<String,Object>)mObject;
			mObject = mMap.get("r2cdb");
			@SuppressWarnings("unchecked")
			Map<String,Object> mR2cDbMap = (Map<String,Object>)mObject;
			String mUserName = (String)mR2cDbMap.get("UserName");
			String mPassword = (String)mR2cDbMap.get("Password");
			String mUrl = (String)mR2cDbMap.get("Url");
			SqlDBManager mSqlDBManager = new R2CodeDbManager(mUrl);
			mRtnValue = mSqlDBManager.buildConnection("r2cdb",mUserName, mPassword, false);
		}
		catch(Exception aException)
		{
			
		}
		return mRtnValue;
	}
}
