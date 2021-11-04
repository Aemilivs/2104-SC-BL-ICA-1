(ns chatbot.core-test
  (:require [clojure.test :refer :all]
            [chatbot.core :refer :all]))

  (deftest read-data-first-key-test
    (testing "JSON with park data is being read and first key is stromovka"
      (is (= "stromovka" (first (keys (read-data)))))))


  (deftest read-data-stromovka-playground-test
    (testing "JSON with park data is being read and stromovka has a playground"
      (is (= true (get (get (read-data) "stromovka") "playground")))))