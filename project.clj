(defproject lein-pact-publish "0.1.0-SNAPSHOT"
  :description "Leiningen plugin to publish pacts to pact broker"
  :url "https://github.com/neharastogi093/lein-pact-publish"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [au.com.dius/pact-jvm-consumer "4.0.4" :exclusions [commons-logging]]]
  :eval-in-leiningen true
  :min-lein-version "2.0.0"
  :scm {:name "git"
        :url "https://github.com/neharastogi093/lein-pact-publish"})