(ns leiningen.pact-publish
  (:import [au.com.dius.pact.core.pactbroker PactBrokerClient]
           (java.io FileNotFoundException)))

(defn- filter-json-files [files]
  (filter #(and (.isFile %) (re-find #".*\.json" (.getPath %))) files))

(defn- publish-pacts-to-broker [pact-broker-url pact-directory version]
  (println "Getting all pact files from " pact-directory)
  (let [pact-file-dir (clojure.java.io/file pact-directory)]
    (if (.exists pact-file-dir)
      (let [error-count (atom 0)
            pact-broker-client (PactBrokerClient. pact-broker-url)
            filtered-pact-files (filter-json-files (file-seq pact-file-dir))]
        (doseq [pact-file filtered-pact-files]
          (println "Uploading file: " (.getName pact-file))
          (let [result (.uploadPactFile pact-broker-client pact-file version)]
            (println (format "Uploading result '%s' for file '%s'" result (.getName pact-file)))
            (when (.startsWith result "FAILED!")
              (swap! error-count inc))))
        (when (> @error-count 0)
          (throw (Exception. "One or more of the pact files were rejected by the pact broker"))))
      (throw (FileNotFoundException. (format "Pact directory '%s' does not exist" pact-directory))))))

(defn pact-publish [project & args]
  (let [pact-publish (:pact-publish project)
        pact-broker-url (:pact-broker-url pact-publish)
        pact-directory (:pact-directory pact-publish)
        version (:version pact-publish)]
    (publish-pacts-to-broker pact-broker-url pact-directory version)))