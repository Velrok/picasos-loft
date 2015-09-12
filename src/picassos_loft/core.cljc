(ns picassos-loft.core
   (:require [reagent.core :as r]))

#?(:cljs
    (enable-console-print!))

(def query (r/atom "https://en.wikipedia.org/wiki/Pablo Picasso"))

(def selected-artist (r/atom {:name "Pablo Picasso"
                              :influenced-by []
                              :inspired ["Vincent van Gogh"
                                         "Henri Matisse"
                                         "Joan Miró"]}))

(def db (r/atom {"pablo picasso" {:name "Pablo Picasso"
                                  :influenced-by []
                                  :inspired ["Vincent van Gogh"
                                             "Henri Matisse"
                                             "Joan Miró"]}

                 "vincent van gogh" {:name "Vincent van Gogh"
                                     :influenced-by ["Pablo Picasso"]
                                     :inspired []}

                 "henri matisse" {:name "Henri Matisse"
                                  :influenced-by ["Pablo Picasso"]
                                  :inspired []}

                 "joan miró" {:name "Joan Miró"
                              :influenced-by ["Pablo Picasso"]
                              :inspired []}}))

(defn set-artist
  [artist-name]
  (println (str "set-artist:" artist-name))
  (reset! selected-artist (get @db (clojure.string/lower-case artist-name)))
  (reset! query 
          (str "https://en.wikipedia.org/wiki/" artist-name)))

(defn search
  [_]
  (set-artist (-> (js/document.getElementById "search-query") .-value)))

(defn main
  []
  [:h1 "Picassos Loft"]
  [:div 
   [:div
    [:input {:type "text" 
             :id "search-query"
             :placeholder (-> @selected-artist :name)}]
    [:button {:on-click search}
     "search"]]
   [:h2 (-> @selected-artist :name)]
   [:div
    [:span "Influenced by"]
    [:ul
     (for [artist (-> @selected-artist :influenced-by)]
       [:li [:a {:on-click (fn [event] 
                             (set-artist (-> event .-target .-innerHTML)))} 
             artist]])]]
   [:div
    [:span "Inspired"]
    [:ul
     (for [artist (-> @selected-artist :inspired)]
       [:li [:a {:on-click (fn [event] 
                             (set-artist (-> event .-target .-innerHTML)))} 
             artist]])]]
   [:div
    [:iframe {:src @query 
              :width "100%"
              :height "500px"}]]])

(defn mountit []
  (r/render-component [main]
                      (.-body js/document)))
