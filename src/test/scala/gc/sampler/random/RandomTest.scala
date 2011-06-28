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

package gc.sampler.random

import org.junit.Test
import org.scalatest.junit.JUnitSuite
import org.junit.Assert.assertTrue

class RandomTest extends JUnitSuite{
	@Test def getInt {
		val random = new Random()
		
		val numbers = for (i <- 1 to 1000000) yield random.nextInt(100)
		val counts = numbers.groupBy(identity).map{case (k,v) => (k,v.size)}
		
		assert(counts.size === 100)
		counts.foreach{
			case (k,v) => assertTrue("int "+k+" encountered fewer than 10 times",+v >= 10)
		}
	}
}