(ns app.introduce-cli)

(defn prompt
  "Introduce CLI"
  []
  (println "Hello username!")
  (flush)
  (let [input (read)]
    (when (not= input 'exit)
      (println "How can I help you? Type 'exit' to finish")
      (recur))))

(prompt)