package helpers

import org.scalatest.{TestSuite, TestData}
import org.scalatestplus.play.{OneAppPerSuite, OneAppPerTest, OneServerPerSuite, OneServerPerTest}
import play.api.{BuiltInComponents, _}
import play.api.ApplicationLoader.Context
import play.api._

/**
  * Taken from https://github.com/playframework/play-scala-compile-di-with-tests
  */

trait OneAppPerTestWithComponents[T <: BuiltInComponents]
  extends OneAppPerTest
    with WithApplicationComponents[T] {
  this: TestSuite =>

  override def newAppForTest(testData: TestData): Application = newApplication
}

trait OneAppPerSuiteWithComponents[T <: BuiltInComponents]
  extends OneAppPerSuite
    with WithApplicationComponents[T] {
  this: TestSuite =>
  override implicit lazy val app: Application = newApplication
}

trait OneServerPerTestWithComponents[T <: BuiltInComponents]
  extends OneServerPerTest
    with WithApplicationComponents[T] {
  this: TestSuite =>

  override def newAppForTest(testData: TestData): Application = newApplication
}

trait OneServerPerSuiteWithComponents[T <: BuiltInComponents]
  extends OneServerPerSuite
    with WithApplicationComponents[T] {
  this: TestSuite =>

  override implicit lazy val app: Application = newApplication
}


trait WithApplicationComponents[T <: BuiltInComponents] {
  private var _components: T = _

  // accessed to get the components in tests
  final def components: T = _components

  // overridden by subclasses
  def createComponents(context: Context): T

  // creates a new application and sets the components
  def newApplication: Application = {
    _components = createComponents(context)
    _components.application
  }

  def context: ApplicationLoader.Context = {
    val classLoader = ApplicationLoader.getClass.getClassLoader
    val env = new Environment(new java.io.File("."), classLoader, Mode.Test)
    ApplicationLoader.createContext(env)
  }
}