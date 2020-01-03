# lein-pact-publish

A Leiningen plugin to publish pacts to pact broker.

## Installation

Use this for user-level plugins:

Put `[lein-pact-publish "0.1.0"]` into the `:plugins` vector of your project.clj.

## Usage

Add a `:pact-publish` key in your `project.clj` - example below:
   
```clojure
:pact-publish { :pact-broker-url "THE PACT BROKER URL" :pact-directory "THE LOCATION FOR GENERATED PACTS" :version "THE PACT VERSION"}
```    

You can execute a pact publish as follows:

```bash
$ lein pact-publish
```

## License

Copyright © 2019 Neha Rastogi

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
