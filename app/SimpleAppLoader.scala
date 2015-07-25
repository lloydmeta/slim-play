import play.api.ApplicationLoader.Context
import play.api._
import play.api.libs.concurrent.Execution.Implicits._
import play.api.mvc.Results._
import play.api.mvc._
import play.api.routing.Router
import play.api.routing.sird._

import scala.concurrent.Future

class SimpleAppLoader extends ApplicationLoader {
  def load(context: Context) = {
    new AppComponents(context).application
  }
}

class AppComponents(context: Context) extends BuiltInComponentsFromContext(context) {

  val router = Router.from {
    case GET(p"/hello/$to") => Action {
      Ok(s"Hello $to")
    }
    /*
     Use Action.async to return a Future result (sqrt can be intense :P)
     Note the use of double(num) to bind only numbers (built-in :)
      */
    case GET(p"/sqrt/${double(num)}") => Action.async {
      Future {
        Ok(Math.sqrt(num).toString)
      }
    }
  }

}