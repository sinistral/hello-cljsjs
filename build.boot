
(set-env!
 :dependencies
 '[[org.clojure/clojure         "1.8.0"]
   [org.clojure/clojurescript   "1.9.946"]
   [cljsjs/js-joda              "1.5.0-0"]

   ;; CLJS-build and REPL dependencies.
   [adzerk/boot-cljs            "2.1.4"  :scope "test"]
   [com.cemerick/piggieback     "0.2.1"  :scope "test"]
   [crisptrutski/boot-cljs-test "0.3.4"  :scope "test"]
   [degree9/boot-npm            "0.2.0"  :scope "test"]
   [org.clojure/tools.nrepl     "0.2.12" :scope "test"]
   [stencil                     "0.5.0"  :scope "test"]])

(require
 '[adzerk.boot-cljs            :refer [cljs]]
 '[boot.repl]
 '[cljs.repl.node]
 '[cemerick.piggieback         :as    piggieback]
 '[crisptrutski.boot-cljs-test :refer [test-cljs]])

(swap! boot.repl/*default-middleware* conj 'cemerick.piggieback/wrap-cljs-repl)

(set-env! :source-paths
          #{"source/cljs" "test"}
          :resource-paths
          #{"build-resources/cljs-config"})

(def +build-ids+
  #{"hello-cljsjs"})

(deftask dev
  []
  (comp
   (cljs :ids +build-ids+ :optimizations :none)
   (target :dir #{"out"})))

(defn start-cljs-repl
  "Entry point for REPL-based development."
  []
  @(future (boot (dev)))
  (piggieback/cljs-repl (cljs.repl.node/repl-env :path ["out/node_modules"])))

(deftask +test
  []
  (comp (test-cljs :js-env :node)))

(deftask +build
  [i id VAL str "The IDs of the lambda functions to build"]
  (when (nil? id)
    (throw (ex-info "The ID of a lambda function to build is required"
                    {})))
  (when-not (contains? +build-ids+ id)
    (throw (ex-info (format "Invalid lambda function ID: %s" id)
                    {:valid-ids   +build-ids+
                     :invalid-ids id})))
  (comp
   (cljs    :ids  #{id})
   (target  :dir  #{"out"})))
