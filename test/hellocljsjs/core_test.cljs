
(ns hellocljsjs.core-test
  (:require [cljs.test        :refer-macros [deftest testing is]]
            [hellocljsjs.core :refer        [main]]))

(deftest test:a-thing
  (is (nil? (main))))
