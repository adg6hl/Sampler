/*
* Copyright (c): 
* Oliver Tearne <tearne@gmail.com> 
*
* This program is free software: you can redistribute it and/or modify it under the terms of
* the GNU General Public License as published by the Free Software Foundation, either version
* 3 of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
* without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
* See the GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License along with this program.
* If not, see <http://www.gnu.org/licenses/>.
*/

package gc.sampler.model

import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import gc.sampler.random.Random

class SingleSampleTest extends JUnitSuite with MockitoSugar{
  @Test
  def doRun {
	  val populationSize = 10
	  val sampleSize = 5
	  val numInfected = 6
	  
	  val random = mock[Random]
	  //Expecting population to be
	  // 0,1,2,3,4,5,6,7,8,9
	  // +,+,+,+,+,+,-,-,-,-
	  when(random.nextInt(10)).thenReturn(0)
	  // 0,1,2,3,4,5,6,7,8
	  // +,+,+,+,+,-,-,-,-
	  when(random.nextInt(9)).thenReturn(3)
	  // 0,1,2,3,4,5,6,7
	  // +,+,+,+,-,-,-,-
	  when(random.nextInt(8)).thenReturn(6)
	  // 0,1,2,3,4,5,6
	  // +,+,+,+,-,-,-
	  when(random.nextInt(7)).thenReturn(0)
	  // 0,1,2,3,4,5
	  // +,+,+,-,-,-	 
	  when(random.nextInt(6)).thenReturn(0)
	  // 0,1,2,3,4
	  // +,+,-,-,-   taken 4+,1-  
	  
	  val singleSample = new SingleSample(random)
	  
	  assert(4 === singleSample.nextNumPositives(sampleSize, populationSize, numInfected))
  }
}