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

package com.dth.fmw.error;

import java.util.GregorianCalendar;

public class ErrorNode
{
  private int errorNo;
  private String title;
  private GregorianCalendar time = new GregorianCalendar();
  private ErrorSeverity severity;
  private ErrorType type;
  private ErrorSource source;
  private ErrorReference reference;
  private String userMessage;
  private String logMessage;
  private ErrorNode parentCause;
  private boolean disregard;
  
  private static int currentErrorNo = 0;
  
  public ErrorNode()
  {
    super();
    synchronized(ErrorNode.class)
    {
      errorNo = currentErrorNo++;
    }
  }
  
  public int getErrorNo()
  {
    return errorNo;
  }

  public String getTitle()
  {
    return title;
  }
  public void setTitle(String title)
  {
    this.title = title;
  }
  public GregorianCalendar getTime()
  {
    return time;
  }
  public void setTime(GregorianCalendar time)
  {
    this.time = time;
  }
  public ErrorSeverity getSeverity()
  {
    return severity;
  }
  public void setSeverity(ErrorSeverity severity)
  {
    this.severity = severity;
  }
  public ErrorType getType()
  {
    return type;
  }
  public void setType(ErrorType type)
  {
    this.type = type;
  }
  public ErrorSource getSource()
  {
    return source;
  }
  public void setSource(ErrorSource source)
  {
    this.source = source;
  }
  public ErrorReference getReference()
  {
    return reference;
  }
  public void setReference(ErrorReference reference)
  {
    this.reference = reference;
  }
  public String getUserMessage()
  {
    return userMessage;
  }
  public void setUserMessage(String userMessage)
  {
    this.userMessage = userMessage;
  }
  public String getLogMessage()
  {
    return logMessage;
  }
  public void setLogMessage(String logMessage)
  {
    this.logMessage = logMessage;
  }
  public ErrorNode getParentCause()
  {
    return parentCause;
  }
  public void setParentCause(ErrorNode parentCause)
  {
    this.parentCause = parentCause;
  }
  public boolean isDisregard()
  {
    return disregard;
  }
  public void setDisregard(boolean disregard)
  {
    this.disregard = disregard;
  }
}
