package samples

/**
  * Created by y28yang on 1/28/2016.
  */
/*
Here's an example of a FunSuite with ShouldMatchers mixed in:
*/
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class ListSuite  extends FunSuite with ShouldMatchers {

  test("An empty list should be empty") {
    List() should be ('empty)
    Nil should be ('empty)
  }

  test("A non-empty list should not be empty") {
    List(1, 2, 3) should not be ('empty)
    List("fee", "fie1", "foe", "fum") should not be ('empty)
  }

  test("A list's length should equal the number of elements it contains") {
    List() should have length (0)
    List(1, 2) should have length (2)
    List("fee", "fie", "foe", "fum") should have length (4)
  }
}
