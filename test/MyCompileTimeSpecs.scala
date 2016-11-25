import helpers.{OneAppPerSuiteWithComponents, OneAppPerTestWithComponents, OneServerPerSuiteWithComponents, OneServerPerTestWithComponents}
import org.scalatest.Suite
import play.api.ApplicationLoader.Context
import play.api.libs.ws.ahc.AhcWSComponents

class AppWithTestComponents(context: Context) extends AppComponents(context) with AhcWSComponents

trait OneAppPerTestWithMyComponents
  extends OneAppPerTestWithComponents[AppWithTestComponents] {
  this: Suite =>

  override def createComponents(context: Context) = new AppWithTestComponents(context)
}

trait OneAppPerSuiteWithMyComponents
  extends OneAppPerSuiteWithComponents[AppWithTestComponents] {
  this: Suite =>

  override def createComponents(context: Context) = new AppWithTestComponents(context)
}

trait OneServerPerTestWithMyComponents
  extends OneServerPerTestWithComponents[AppWithTestComponents] {
  this: Suite =>

  override def createComponents(context: Context) = new AppWithTestComponents(context)
}

trait OneServerPerSuiteWithMyComponents
  extends OneServerPerSuiteWithComponents[AppWithTestComponents] {
  this: Suite =>

  override def createComponents(context: Context) = new AppWithTestComponents(context)
}