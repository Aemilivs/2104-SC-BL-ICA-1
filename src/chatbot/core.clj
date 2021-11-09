(ns chatbot.core
  (:gen-class))
  
  (defn prompt
    "Introduce CLI"
    []
    (println "Hello username!")
    (flush)
    (let [input (read)]
      (when (not= input 'exit)
        (println "How can I help you? Type 'exit' to finish")
        (recur))))

  (defn -main
    [& args]
    (prompt))
