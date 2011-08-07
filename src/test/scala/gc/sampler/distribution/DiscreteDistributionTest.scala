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
import org.junit.Assert.{assertTrue, assertFalse}
import scala.math.{abs, max}
import org.junit.Before

class DiscreteDistributionTest extends JUnitSuite with MockitoSugar{
	val once = 1
	val twice = 2
	val thrice = 3
	
	var d1, d2, d3: DiscreteDistribution = _
	
	@Before def setup {
		//	--d1--		--d2--		---d3---
		//					6		    3
		//				  5,6		  2,3
		//	4,5,6		4,5,6		1,2,3,4
		d1 = new DiscreteDistribution(Map(4->once, 5->once,  6->once))
		d2 = new DiscreteDistribution(Map(4->once, 5->twice, 6->thrice))
		d3 = new DiscreteDistribution(Map(1->once, 2->twice, 3->thrice, 4->once))
	}
	
	@Test def distanceUsesInfinityNorm {
		def expectedDist(dA: DiscreteDistribution, dB: DiscreteDistribution, position: Int) =
			math.abs(dA(position)-dB(position))
		
		assert( d1.distanceTo(d2) ===  expectedDist(d1, d2, 6))
		assert( d1.distanceTo(d3) ===  expectedDist(d1, d3, 3))
		assert( d2.distanceTo(d3) ===  expectedDist(d2, d3, 6)) // index 6, not 3
	}
	
	@Test def normalisedValues {
		assert(d1(3) === 0)
		assert(d1(4) === 1.0/3)
		assert(d1(5) === 1.0/3)
		assert(d1(6) === 1.0/3)
		assert(d1(7) === 0)
		
		assert(d2(3) === 0)
		assert(d2(4) === 1.0/6)
		assert(d2(5) === 2.0/6)
		assert(d2(6) === 3.0/6)
		assert(d2(7) === 0)
		
		assert(d3(0) === 0)
		assert(d3(1) === 1.0/7)
		assert(d3(2) === 2.0/7)
		assert(d3(3) === 3.0/7)
		assert(d3(4) === 1.0/7)
		assert(d3(5) === 0)
	}
	
	@Test def equalsAndHashCode {
		val d1a = new DiscreteDistribution(Map(4->once, 5->twice))
		val d1b = new DiscreteDistribution(Map(4->once, 5->twice))
		val d2 = new DiscreteDistribution(Map(4->once))
		
		assertTrue(d1a.canEqual(d2))
		assertFalse(d1a.canEqual(3))
		
		assert(d1a === d1b)
		assertTrue(d1a != d2)
		
		assert(d1a.hashCode === d1b.hashCode)
		assertTrue(d1a != d2)
	}
	
	@Test def factoryConstruction {
		val seq = Seq(1,2,2,3,3,3,4,4,4,4)
		val d = DiscreteDistribution(seq)
		assert(d === new DiscreteDistribution(Map(1->1, 2->2, 3->3, 4->4)))
	}
}