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

package com.dth.fmw.exception.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dth.fmw.error.ErrorType;
import  com.dth.fmw.error.ErrorPriority;
import com.dth.fmw.exception.Validate;

public class ExceptionHandlerManager extends Validate
{
  public static final String title = "ExceptionHandlerManager";
  
  private Map<ErrorPriority, List<ExceptionHandler>> exceptionHandlers = new HashMap<ErrorPriority, List<ExceptionHandler>>();
  
  public void handle(Throwable aRuntimeException)
  {
    ExceptionHandlerState mRtnValue = handle(ErrorPriority.HIGH,aRuntimeException);
    if(mRtnValue == ExceptionHandlerState.CONTINUE || mRtnValue == ExceptionHandlerState.NOT_PROCESSED)
    {
      ExceptionHandlerState mResult = handle(ErrorPriority.MEDIUM,aRuntimeException);
      if(!(mResult==ExceptionHandlerState.NOT_PROCESSED)) mRtnValue = mResult;
    }
    if(mRtnValue == ExceptionHandlerState.CONTINUE || mRtnValue == ExceptionHandlerState.NOT_PROCESSED)
    {
      ExceptionHandlerState mResult = handle(ErrorPriority.LOW,aRuntimeException);
      if(!(mResult==ExceptionHandlerState.NOT_PROCESSED)) mRtnValue = mResult;
    }  
    if(mRtnValue == ExceptionHandlerState.CONTINUE || mRtnValue == ExceptionHandlerState.NOT_PROCESSED)
    {
      ExceptionHandlerState mResult = handle(ErrorPriority.INFORMATIONAL,aRuntimeException);
      if(!(mResult==ExceptionHandlerState.NOT_PROCESSED)) mRtnValue = mResult;
    }
  }
  
  private ExceptionHandlerState handle(ErrorPriority aPriority,Throwable aException)
  {
    ExceptionHandlerState mRtnValue = ExceptionHandlerState.NOT_PROCESSED;
    List<ExceptionHandler> mPriorityList = exceptionHandlers.get(aPriority);
    if(mPriorityList != null)
    {
      for(ExceptionHandler mHandler:mPriorityList)
      {
        if(mHandler.canHandle(aException))
        {
          boolean mResults = mHandler.handle( aException);
          if(mResults)
          {
            mRtnValue = ExceptionHandlerState.CONTINUE;
            ExceptionHandlerState mState = mHandler.state(aException);
            if(mState.equals(ExceptionHandlerState.END))
            {
              break;
            }
            if(mState.equals(ExceptionHandlerState.RETHROW))
            {
              if(aException instanceof RuntimeException)
              {
                throw(RuntimeException)aException;
              }
              else
              {
                exception("caught exception", title, "ExceptionHandlerState handle(CmdMsg,ErrorPriority,Throwable)", "", "", ErrorType.FRAMEWORK, aException);
              }
            }
          }
        }
      }
    }
    return mRtnValue;
  }
  
  public void addExceptionHandler(ErrorPriority aPriority,ExceptionHandler aHandler)
  {
    List<ExceptionHandler> mPriorityList = exceptionHandlers.get(aPriority);
    if(mPriorityList == null)
    {
      mPriorityList = new ArrayList<ExceptionHandler>();
      exceptionHandlers.put(aPriority,mPriorityList);
    }
    mPriorityList.add(aHandler);
  }
  
  public void addExceptionHandler(String aPriorityValue,ExceptionHandler aHandler)
  {
    for(ErrorPriority mPriority:ErrorPriority.values())
    {
      if(mPriority.getName().equals(aPriorityValue))
      {
        addExceptionHandler(mPriority,aHandler);
      }
    }
  }
}

