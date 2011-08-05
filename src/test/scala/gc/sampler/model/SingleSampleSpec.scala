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

import gc.sampler.random.Random

import org.specs2.mock._
import org.specs2.mutable.SpecificationWithJUnit

class SingleSampleSpec extends SpecificationWithJUnit with Mockito {
	val populationSize = 10
	val sampleSize = 5
	val numInfected = 6

	"SingleSampleSpec" should {
		"accumulate samples from random int generator" in {
			  
			val random = mock[Random]
			// Population starts as			+,+,+,+,+,+,-,-,-,-
			random.nextInt(10) returns 0 // +,+,+,+,+,	-,-,-,-
			random.nextInt(9)  returns 3 // +,+,+,+,	-,-,-,-
			random.nextInt(8)  returns 6 // +,+,+,+,	-,-,-
			random.nextInt(7)  returns 0 // +,+,+,		-,-,-
			random.nextInt(6)  returns 0 // +,+,		-,-,-
			// Taken 4+,1-  
			  
			val singleSample = new SingleSample(random)
			  
			4 === singleSample.nextNumPositives(
				sampleSize, 
				populationSize, 
				numInfected
			)
		}
	}
}