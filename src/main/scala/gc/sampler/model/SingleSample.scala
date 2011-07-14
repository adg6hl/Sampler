/*
* Copyright (c): 
* Oliver Tearne <tearne@gmail.com>
* Ashley Goddard <adg6hl@hotmail.com>
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

import gc.sampler.random._
import scala.collection.mutable.ListBuffer

class SingleSample(val random: Random) {
		
	def nextNumPositives(sampleSize:Int, populationSize:Int, numInfected:Int): Int = {
		
		val list = new ListBuffer[Int]()
		
		val positive = 1
		val negative = 0
		val numNegative = populationSize-numInfected
		
		for (i <- 0 until numInfected) {
			list.append(positive)
		}
		
		for (i <- 0 until numInfected) {
			list.append(negative)
		}
		
		var numberDetected = 0
		var index = 0
		
		for (i <- 0 until sampleSize) {
			index = random.nextInt(populationSize-i)
			numberDetected = numberDetected + list(index)
			list.remove(index)
		}
		
		numberDetected
		
		// SETUP BAT POPULATION
		
		// Create sample bat population list? with numInfected positives
		// followed by popSize - numInfected negatives
		
		// TAKE SAMPLES
		
		// Loop from 0 to 4
			// Call random.nextInt(popSize - loop)
			// remove bat from population
		
		// RETURN RESULT
		
		// Return number of positive bats removed
	}
}