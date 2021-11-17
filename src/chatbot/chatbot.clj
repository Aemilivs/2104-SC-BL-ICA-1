(ns chatbot.chatbot
        (:require clojure.string)
        (:require [chatbot.park :as park])
  (:require [chatbot.responses-&-keywords :as rk]))

;; TODO move responses and keywords to a separated file.
;; TODO Receive the list of parks from park.clj
;; TODO Approximate the match between input and the keyword
;; e.g. bertrmk => bertramka; klinskeho zahrada => kinskeho-zahrada

(
    defn prepare-text
    "Prepare text for processing by removing all the punctuation signs and making it lower case."
    [input]
    (
        clojure.string/lower-case 
        (
            clojure.string/join
            (
                filter
                    #(or (Character/isLetter %) (Character/isWhitespace %) (= \- %))
                    input
            )
        )
    )
)

(
    defn match-keyword
    "Find the keyword in the input to hook the responses to it"
    [input]
    (
        ;; TODO Handle multiple keywords?
        first
        (
            filter
            #(not (nil? %))
            (
                map
                    #(get rk/keywords %)
                    (clojure.string/split input #" ")
            )
        )
    )
)

(
    defn get-keyword-data
    "Receive the data regarding the keyword"
    [keyword subject]
    (
        case keyword
            "wc"
            (
                chatbot.park/get-wc subject
            )
            "attractions"
            (
                chatbot.park/get-attractions subject
            )
            "bicicle"
            (
                chatbot.park/get-biking subject
            )
            "skating"
            (
                chatbot.park/get-skating subject
            )
            "sports"
            (
                chatbot.park/get-sports subject
            )
            "playground"
            (
                chatbot.park/get-playground subject
            )
            "dogs"
            (
                chatbot.park/get-dogs subject
            )
            "parking"
            (
                chatbot.park/get-parking subject
            )
    )
)

(
    defn build-response
    "Build the response depending on what is the keyword and what is the park"
    [keyword subject]
    (
        rand-nth
        (
            get
            (
                get
                    rk/responses
                    keyword
            )
            (
                get-keyword-data
                keyword
                subject
            )
        )
    )
)

(
    defn match-subject
    "Find the subject in the input."
    [input]
    (
        ;; TODO Introduce a handling of multiple subject matches.
        first
        (
            filter
                #(
                    contains?
                        rk/subjects
                        %
                )
                (clojure.string/split input #" ")
        )
    )
)

(
    defn replace-placeholders
    "Replace the placeholders with actual values"
    [response subject]
    (
        clojure.string/replace 
            response 
            #"\{park\}" 
            (clojure.string/capitalize subject)
    )
)

(
    defn select-response
    "Select correct responses depending on the input"
    [input]
    (
        let 
        [
            keyword (match-keyword input)
            subject (match-subject input)
        ]
        (
            ;; TODO: Introduce a guard clause to handle exit sequence (e.g. Goodbye!)
            ;; TODO: Introduce a guard clause to handle empty responses list
            ;; TODO: Introduce a guard clause to handle missing subject
            replace-placeholders
               (build-response keyword subject)
                subject
        )
    )
)

(
    defn process
    "Process the text input and respond using pattern matchinng rules."
    [input]
    (
        select-response
        (
            prepare-text input
        )
    )
)