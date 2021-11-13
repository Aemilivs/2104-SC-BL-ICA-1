(ns chatbot.chatbot
        (:require [chatbot.park :as park]))
    )

(
    def responses
    {
        "bicicle" "Biking is {biking} in {park}",
        "playground" "There is {playground} in {park}"
    }
)

(
    def subjects
    #{
        "bertramka",
        "frantiskanska-zahrada",
        "obora-hvezda",
        "kampa",
        "kinskeho-zahrada",
        "klamovka",
        "ladronka",
        "letna",
        "petrin",
        "riegrovy-sady",
        "stromovka",
        "vysehrad"
    }
)

(
    defn prepare-text
    "Prepare text for processing by removing all the punctuation signs and making it lower case."
    [input]
    (clojure.string/lower-case 
        (
            apply
                str
                (
                    filter
                        #(or (Character/isLetter %) (Character/isWhitespace %))
                        input
                )
        )
    )
)

(
    defn match-responses
    "Find responses that correspond to the keywords present in the input."
    [input]
    (
        filter
        #(not (nil? %))
        (
            map
                #(get responses %)
                (clojure.string/split input #" ")
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
                        subjects
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
        -> response
            ;; Replaced with placeholder because function can't be reached without leinengen working correctly
            ;; (clojure.string/replace #"\{biking\}" (park/get-biking subject))
            (clojure.string/replace #"\{biking\}" "true")
            (clojure.string/replace #"\{park\}" subject)
    )
)

(
    defn select-response
    "Select correct responses depending on the input"
    [input]
    (
        let 
        [
            responses (match-responses input)
            subject (match-subject input)
        ]
        (
            do
            (
                ;; TODO: Introduce a guard clause to handle empty responses list
                ;; TODO: Introduce a guard clause to handle missing subject
                replace-placeholders
                    ;; TODO Select a random response instead of a first one
                   (first responses)
                    subject
            )
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