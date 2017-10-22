
(defproject hellocljsjs "0.0.1"
  :dependencies [[org.clojure/clojure       "1.8.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [cljsjs/js-joda            "1.5.0-0"]]
  :plugins      [[lein-cljsbuild            "1.1.7"]]
  :cljsbuild    {:builds [{:source-paths ["source/cljs"]
                           :compiler     {:target        :nodejs
                                          :main          hellocljsjs.core/main
                                          :optimizations :none
                                          :pretty-print  true}}]})
