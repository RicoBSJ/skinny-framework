package skinny.validator

import org.scalatest._

class longValueSpec extends FlatSpec with Matchers {

  behavior of "longValue"

  it should "be available" in {
    val validate = longValue
    validate.name should equal("longValue")

    validate(param("id", "abc")).isSuccess should equal(false)
    validate(param("id", "あ")).isSuccess should equal(false)
    validate(param("id", "1a")).isSuccess should equal(false)

    validate(param("id", null)).isSuccess should equal(true)
    validate(param("id", "")).isSuccess should equal(true)

    validate(param("id", "0")).isSuccess should equal(true)
    validate(param("id", 0)).isSuccess should equal(true)

    validate(param("id", -1)).isSuccess should equal(true)
    validate(param("id", -0.1D)).isSuccess should equal(false)

    validate(param("id", 1)).isSuccess should equal(true)
    validate(param("id", 2)).isSuccess should equal(true)
    validate(param("id", 3)).isSuccess should equal(true)

    validate(param("id", java.lang.Long.MAX_VALUE)).isSuccess should equal(true)
    validate(param("id", s"${java.lang.Long.MAX_VALUE}1")).isSuccess should equal(false)
    validate(param("id", java.lang.Long.MIN_VALUE)).isSuccess should equal(true)
    validate(param("id", s"${java.lang.Long.MIN_VALUE}1")).isSuccess should equal(false)

  }

}
