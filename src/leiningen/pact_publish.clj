(ns leiningen.pact-publish
  (:import [au.com.dius.pact.core.pactbroker PactBrokerClient]))

(defn- publish-pacts-to-broker [pact-broker-url pact-directory version]
  (let [pact-broker-client (PactBrokerClient. pact-broker-url)
        pact-files (file-seq (clojure.java.io/file pact-directory))
        filtered-pact-files (filter #(and (.isFile %) (re-find #".*\.json" (.getPath %))) pact-files)]
    (doseq [pact-file filtered-pact-files]
      (.uploadPactFile pact-broker-client pact-file version))))

(defn pact-publish [project & args]
  (let [pact-publish (:pact-publish project)
        pact-broker-url (:pact-broker-url pact-publish)
        pact-directory (:pact-directory pact-publish)
        version (:version pact-publish)]
    (publish-pacts-to-broker pact-broker-url pact-directory version)))