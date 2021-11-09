(ns chatbot.park
    (:require [clojure.data.json :as json]))
  
    (
        defn read-data
        "Read data"
        []
        (
            json/read-str
            (
                slurp "resources/data-en.json"
            )
        )
    )

    (
        defn get-park-titles
        "Get park titles"
        []
        (
            keys (read-data)
        )
    )

    (
        defn get-park-property
        "Get park titles"
        [
            title
            property
        ]
        (
            (get (read-data) title) 
            property
        )
    )