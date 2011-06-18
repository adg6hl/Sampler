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

package gc.sampler.distribution
import org.scalatest.junit.JUnitSuite
import org.scalatest.mock.MockitoSugar
import org.junit.Test
import scala.math.{abs, max}

class DiscreteDistributionTest extends JUnitSuite with MockitoSugar{
	val once = 1
	val twice = 2
	val thrice = 3
	
	@Test def distanceCalculatedWithInfinityNorm {
		//	distribution1	distribution2
		//						6
		//					  5,6
		//	4,5,6			4,5,6
		val distribution1 = new DiscreteDistribution(Map(4->once, 5->once,  6->once))
		val distribution2 = new DiscreteDistribution(Map(4->once, 5->twice, 6->thrice))
		
		val result = distribution1.distanceTo(distribution1)
		
		val norm1 = 3.0
		val norm2 = 6.0
		//difference between the sixes
		val expected = math.abs(1/norm1 - 3/norm2)
		
		assert( expected === result )
	}
}