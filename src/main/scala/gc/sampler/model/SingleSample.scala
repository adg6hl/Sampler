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

import gc.sampler.random._

class SingleSample(val random: Random) {
	def nextNumPositives(sampleSize:Int, populationSize:Int, numInfected:Int): Int = {
		val draws = for(i <- 0 until sampleSize) yield random.nextInt(populationSize-i)
		(0 /: draws){ (acc,n) => if(n < numInfected-acc) acc+1 else acc }
		draws.foldLeft(0){ (acc,n) => if(n < numInfected-acc) acc+1 else acc }
	}
}