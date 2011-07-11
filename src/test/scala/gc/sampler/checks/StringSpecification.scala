package gc.sampler.checks

import org.scalacheck._
import org.specs2._

class StringSpecification extends SpecificationWithJUnit with ScalaCheck { def is =
	"Example spec using ScalaCheck, Strings should support" ^
		"startsWith" ! check { (a: String, b: String) => (a+b).startsWith(a) }                                              ^
		"endsWith"   ! check { (a: String, b: String) => (a+b).endsWith(b) }                                                ^
		"concat"     ! check { (a: String, b: String) => (a+b).length >= a.length && (a+b).length >= b.length }             ^
		"substring"  ! check { (a: String, b: String) => (a+b).substring(a.length) == b }                                   ^
		"substring"  ! check { (a: String, b: String, c: String) => (a+b+c).substring(a.length, a.length+b.length) == b }  	^   
	end
}