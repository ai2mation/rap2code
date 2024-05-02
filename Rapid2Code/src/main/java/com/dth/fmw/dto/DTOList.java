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

package com.dth.fmw.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DTOList<DTO extends DataTransfer> extends DTOBase implements Collection<DTO>
{
  private static final long serialVersionUID = 1L;
  private List<DTO> listOfDTOs = new ArrayList<DTO>();
  
  public boolean add(DTO aDTO)
  {
    return listOfDTOs.add(aDTO);
  }
  
  public Collection<DTO> getList()
  {
    return Collections.unmodifiableList(listOfDTOs);
  }
  
  public int size()
  {
    return listOfDTOs.size();
  }


  @Override
  public void clear()
  {
    listOfDTOs.clear();    
  }

  @Override
  public boolean contains(Object aObject)
  {
    return listOfDTOs.contains(aObject);
  }

  @Override
  public boolean containsAll(Collection<?> aCollection)
  {
    return listOfDTOs.containsAll(aCollection);
  }

  @Override
  public boolean isEmpty()
  {
    // TODO Auto-generated method stub
    return listOfDTOs.isEmpty();
  }

  @Override
  public Iterator<DTO> iterator()
  {
    return ((Iterator<DTO>)new DTOIterator<DTO>(this));
  }

  @Override
  public boolean remove(Object aObject)
  {
    return listOfDTOs.remove(aObject);
  }

  @Override
  public boolean removeAll(Collection<?> aCollection)
  {
    return listOfDTOs.removeAll(aCollection);
  }

  @Override
  public boolean retainAll(Collection<?> aCollection)
  {
    return listOfDTOs.retainAll(aCollection);
  }

  @Override
  public Object[] toArray()
  {
    return listOfDTOs.toArray();
  }

  @Override
  public <T> T[] toArray(T[] aArray)
  {
    return listOfDTOs.toArray(aArray);
  }

  @Override
  public boolean addAll(Collection<? extends DTO> aCollection)
  {
    return listOfDTOs.addAll(aCollection);
  }
  
  public DTO get(int aIndex)
  {    
    return listOfDTOs.get(aIndex);
  }
  
}
