package helpers

import org.scalatest.TestSuite
import org.scalatestplus.play._
import play.api.{BuiltInComponents, _}
import play.api.ApplicationLoader.Context
import play.api._

/**
  * Taken from https://github.com/playframework/play-scala-compile-di-with-tests
  */

trait OneAppPerTestWithComponents[T <: BuiltInComponents]
  extends BaseOneAppPerTest
    with FakeApplicationFactory
    with WithApplicationComponents[T] {
  this: TestSuite =>
}

trait OneAppPerSuiteWithComponents[T <: BuiltInComponents]
  extends BaseOneAppPerSuite
    with FakeApplicationFactory
    with WithApplicationComponents[T] {
  this: TestSuite =>
}

trait OneServerPerTestWithComponents[T <: BuiltInComponents]
  extends BaseOneServerPerTest
    with FakeApplicationFactory
    with WithApplicationComponents[T] {
  this: TestSuite =>

}

trait OneServerPerSuiteWithComponents[T <: BuiltInComponents]
  extends BaseOneServerPerSuite
    with FakeApplicationFactory
    with WithApplicationComponents[T] {
  this: TestSuite =>

}


trait WithApplicationComponents[T <: BuiltInComponents] {
  private var _components: T = _

  // accessed to get the components in tests
  final def components: T = _components

  // overridden by subclasses
  def createComponents(context: Context): T

  // creates a new application and sets the components
  def fakeApplication(): Application = {
    _components = createComponents(context)
    _components.application
  }

  def context: ApplicationLoader.Context = {
    val classLoader = ApplicationLoader.getClass.getClassLoader
    val env = new Environment(new java.io.File("."), classLoader, Mode.Test)
    ApplicationLoader.createContext(env)
  }
}