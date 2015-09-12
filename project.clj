(defproject picassos-loft "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [ring "1.4.0"]
                 [org.clojure/clojurescript "1.7.48"]
                 [reagent "0.5.1"]]
  :plugins [[lein-ring "0.8.10"]
            [lein-cljsbuild "1.1.0"]]
  :ring {:handler picassos-loft.api/app
         :nrepl {:start? true
                 :port 9998}}
  :cljsbuild {
    :builds [{
        ; The path to the top-level ClojureScript source directory:
        :source-paths ["src"]
        ; The standard ClojureScript compiler options:
        ; (See the ClojureScript compiler documentation for details.)
        :compiler {
          :output-to "resources/main.js"  ; default: target/cljsbuild-main.js
          :optimizations :whitespace
          :pretty-print true}}]})
