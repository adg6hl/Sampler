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

package gc.sampler.run

import org.junit.Test
import org.scalatest.junit.JUnitSuite
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito.when
import gc.sampler.random.Random
import gc.sampler.model.SingleSample
import gc.sampler.distribution.DiscreteDistribution

class SerialRunnerTest extends JUnitSuite with MockitoSugar{
	@Test def createDist {
		val runner = new SerialRunner(mock[Random])
		
		val model = mock[SingleSample]
		val sample = 2;
		val popSize = 3;
		val positives = 4;
		when(model.nextNumPositives(sample, popSize, positives)).thenReturn(
			1,2,2,3,3,3,4,4,4,4
		)
		
		val result: DiscreteDistribution = runner(10){
			model.nextNumPositives(sample, popSize, positives)
		}
		
		assert( 
			result === new DiscreteDistribution(Map(1->1, 2->2, 3->3, 4->4))
		)
	}
}