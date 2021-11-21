(ns chatbot.core
  (:gen-class)
  (:require [chatbot.chatbot :as chatbot]))
  
  (
    defn prompt
    "Introduce CLI"
    []
    (
      do
        (println "Hello, please type exit to finish the application.")
        (
          let [input (read-line)]
          (
            when
              (not= input "exit")
              (
                do
               (Thread/sleep (rand-int 1250))
                  (
                    println (chatbot/process input)
                  )
                  (recur)
              )
          )
        )
    )
  )

  (defn -main
    [& args]
    (prompt))
