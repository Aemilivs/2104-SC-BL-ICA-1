(ns chatbot.core
  (:gen-class)
  (:require [clojure.data.json :as json]))
  
  (defn prompt
    "Introduce CLI"
    []
    (println "Hello username!")
    (flush)
    (let [input (read)]
      (when (not= input 'exit)
        (println "How can I help you? Type 'exit' to finish")
        (recur))))

  (defn read-data
    "Read data"
    []
    (
      json/read-str
      (
        slurp "resources/data-en.json"
      )
    )
  )

  (defn -main
    [& args]
    (prompt))
