import org.scalatest.{FunSpec, Matchers}
import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}


class AppSpec extends FunSpec
  with OneServerPerSuiteWithMyComponents
  with Matchers
  with ScalaFutures
  with IntegrationPatience {

  private lazy val ws = components.wsClient

  describe("/hello/$name") {
    it("""should respond with "Hello $name"""") {
      whenReady(ws.url(s"http://localhost:$port/hello/joe").get()) { r =>
        r.body shouldBe "Hello joe"
      }
    }
  }

}
