## Slim Play App

Wanna build a really, really slim Play project?

This project demonstrates how you can easily build a non-blocking, threadsafe, and fast Play app without having to
use the default `routes` file. This is familiar territory for people who want the simplicity of Sinatra/Bottle but
want to take advantage of Scala's concurrent, type-safe and scalable nature.

### How to run

1. Git clone this project
2. `./activator run` from the checked-out project's root directory
3. Open a browser and hit:
  - [Hello $name endpoint](http://localhost:9000/hello/smarty-pants)
  - [Async Sqrt calculator](http://localhost:9000/sqrt/1764)

### How it was built

All I did was:

1. Use activator to generate new template (`$ activator new simple-play play-scala`)
2. Delete the auto-generated controller and view directories (won't be using them)
3. Create a `SimpleApp.scala` file in the `./app` directory, which holds an ApplicationLoader and the router, which is
  super simple:

  ```
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

4. Add `play.application.loader=SimpleApp` to `./conf/application.conf` so that Play knows to load our custom app (that
  contains our simple routes)

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
