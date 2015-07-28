## Slim Play App

Wanna build a really, really slim Play project?

This project demonstrates how you can easily build a non-blocking, threadsafe, and fast Play app without having to
use the default `routes` file. The end-result should be familiar territory for people who are used to the simplicity of Sinatra/Bottle
but want to take advantage of Scala's concurrent, type-safe and scalable nature.

### Templates

For [Typesafe activator](https://www.typesafe.com/activator/templates): `$ activator new my-slim-project slim-play-scala`

For [Giter8](https://github.com/n8han/giter8): `$ g8 lloydmeta/slim-play`

### How to run

1. Git clone this project or use [a template](#templates)
2. `./activator run` from the project's root directory
3. Open a browser and hit:
  - [Hello $name endpoint](http://localhost:9000/hello/beachape)
  - [Async Sqrt calculator](http://localhost:9000/sqrt/1764)

### How it was built

All I did was:

1. Use activator to generate a new Play app (`$ activator new slim-play play-scala`)
2. Delete the auto-generated controller, public, and view directories (won't be using them)
3. Create a `AppLoader.scala` file in the `./app` directory, which holds an ApplicationLoader and the router, which is
  super simple:

  ```scala
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
  ```

4. Add `play.application.loader=AppLoader` to `./conf/application.conf` so that Play knows to load our custom app (that
  contains our simple router)

### More info

The following links may be useful for further understanding on what is happening here:

1. Official Play docs on [String Interpolating Routing DSL](https://www.playframework.com/documentation/2.4.x/ScalaSirdRouter)
2. Official Play docs on [Compile-time dependency injection](https://www.playframework.com/documentation/2.4.x/ScalaCompileTimeDependencyInjection)


## Licence

The MIT License (MIT)

Copyright (c) 2015 by Lloyd Chan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
