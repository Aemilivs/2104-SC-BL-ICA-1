(ns chatbot.chatbot
        (:require [clojure.tools.trace :as trace])
    )

(
    defn ^:dynamic warn
    "Warn that the bot is WIP"
    [input]
    (+ 3 2)
)

(
    defn process
    "Process the text input and respond using pattern matchinng rules."
    [input]
    (trace/dotrace [warn] (warn input))
)
