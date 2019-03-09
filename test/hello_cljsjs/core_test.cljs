
(ns hello-cljsjs.core-test
  (:require [cljs.test        :refer-macros [deftest testing is]]
            [hello-cljsjs.core :refer        [main]]))

(deftest test:a-thing
  (is (nil? (main))))
