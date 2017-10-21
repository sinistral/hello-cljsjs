
(ns hellocljsjs.core
  (:require [cljsjs.js-joda]))

(def joda js/JSJoda)

(defn ^{:export true} main
  []
  (.now joda.Instant))

(set! *main-cli-fn* main)
(enable-console-print!)
