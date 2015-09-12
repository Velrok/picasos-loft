(ns picassos-loft.api
  (:require [compojure.core :refer :all]
            [ring.adapter.jetty :as jetty]
            [compojure.route :as route]))

(defroutes app
  (GET "/" []
       (slurp "./resources/index.html"))

  (GET "/status" []
       {:status 200
        :body "ok"})

  (route/files "" {:root "./resources"})

  (route/not-found "<h1>Page not found</h1>"))


(defn -main
  [& args]
  (jetty/run-jetty #'app {:port 3000}))

(comment
  (future (-main))
  )
