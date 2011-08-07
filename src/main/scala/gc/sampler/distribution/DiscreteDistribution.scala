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

class DiscreteDistribution(val countsMap: Map[Int,Int]) {
	val norm = countsMap.values.foldLeft(0)((acc,count) => acc+count) 
	val densityMap = countsMap.mapValues(count => count.asInstanceOf[Double]/norm)
	
	def distanceTo(that: DiscreteDistribution): Double = {
		val indexes = countsMap.keySet ++ that.countsMap.keySet
		def distAtIndex(i: Int) = math.abs(this(i)-that(i))
		indexes.map(distAtIndex(_)).max
	}
	
	def apply(index: Int): Double = {
		if(densityMap.contains(index)) densityMap(index)
		else 0
	}
	
	def + (that: DiscreteDistribution): DiscreteDistribution = new DiscreteDistribution(
		(Map[Int, Int]() /: (for(map <- List(countsMap, that.countsMap); kvp <- map) yield kvp)) {
			(acc, kvp) => 
				if(acc.contains(kvp._1)) acc + ((kvp._1, acc(kvp._1)+kvp._2))
				else acc + kvp
		}
	)
	
	def canEqual(other: Any): Boolean = other.isInstanceOf[DiscreteDistribution]	
	
	override def equals(other: Any) = other match {
		case that: DiscreteDistribution => 
			(that canEqual this) && (that.countsMap equals countsMap)
		case _ => false
	}
	
	override def hashCode() = countsMap.hashCode	 
}
object DiscreteDistribution{
	def apply(t:Traversable[Int]): DiscreteDistribution = 
		new DiscreteDistribution(t.groupBy(identity).mapValues(_.size))
}