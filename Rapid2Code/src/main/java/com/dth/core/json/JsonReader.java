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

package com.dth.core.json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONObject;

public class JsonReader
{
	public static Map<String,Object> getConfiguration()
	{
		String mConfigPath = getConfigPath();
		LinkedHashMap<String, Object> mMap = getLinks(mConfigPath);
		//String mValue = mLinkedHashMap.toString();
		return mMap;
	}
	
	public static String getConfigPath()
	{
		LinkedHashMap<String, Object> mLinkedHashMap = getLinks("Rapid2CodeStarter.json");
		String mConfigName = "";
		Object mConfigObject = mLinkedHashMap.get("configFile");
		if(mConfigObject instanceof String)
		{
			mConfigName = (String)mConfigObject;
		}
		return mConfigName;
	}
	
	private static LinkedHashMap<String, Object> getLinks(String aFileName)
	{
		
		LinkedHashMap<String, Object> mLinkedHashMap = null;
		try
		{
			FileInputStream inputReader = new FileInputStream(aFileName);
			JSONParser mParser = new JSONParser(inputReader);
			Object mObject = mParser.parse();
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, Object> mLinkedHashMap1 = (LinkedHashMap<String, Object>) mObject;
			mLinkedHashMap = mLinkedHashMap1;
			/*
			String mConfigName = mLinkedHashMap.get("configFile");
			System.out.println("ConfigName = " + mConfigName);
			System.out.println("ready to exit");
			*/
		} catch (FileNotFoundException aException)
		{

		} catch (ParseException aException)
		{

		}
		catch(Exception aException)
		{
			
		}
		return mLinkedHashMap;

	}

    public static void parse(String aFileName) throws Exception 
    {
    	
		FileInputStream inputReader = new FileInputStream(aFileName);
		JSONParser mParser = new JSONParser(inputReader);
		Object mObject = mParser.parse();
		if(mObject instanceof JSONObject)
		{
			JSONObject mJSONObject = (JSONObject)mObject;
			System.out.println(mJSONObject.toJSONString());
		}
		
		/*

        // parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(new FileReader("JSONExample.json"));
          
        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;
          
        // getting firstName and lastName
        String firstName = (String) jo.get("firstName");
        String lastName = (String) jo.get("lastName");
          
        System.out.println(firstName);
        System.out.println(lastName);
          
        // getting age
        long age = (long) jo.get("age");
        System.out.println(age);
          
        // getting address
        Map address = ((Map)jo.get("address"));
          
        // iterating address Map
        Iterator<Map.Entry> itr1 = address.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
          
        // getting phoneNumbers
        JSONArray ja = (JSONArray) jo.get("phoneNumbers");
          
        // iterating phoneNumbers
        Iterator itr2 = ja.iterator();
          
        while (itr2.hasNext()) 
        {
            itr1 = ((Map) itr2.next()).entrySet().iterator();
            while (itr1.hasNext()) {
                Map.Entry pair = itr1.next();
                System.out.println(pair.getKey() + " : " + pair.getValue());
            }
        }*/
    }

}
