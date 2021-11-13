(ns chatbot.chatbot
        (:require [chatbot.park :as park]))

(
    def keywords
    {
        "bicicle" "bicicle",
        "bike" "bicicle",
        "ride" "bicicle",
        "two-wheels" "bicicle",
        "playground" "playground"
    }
)

(
    def responses
    {
        "bicicle"
        {
            true 
            '(
                "You can ride bicicle in {park}",
                "There is a bicicle road in {park}",
                "{park} has the roads, you will be on two wheels"
            )
            false
            '(
                "Unfortunately, you can't ride bicicle there.",
                "You can't ride bicicle in {park}"
            )
        },
        "playground"
        {
            true
            '(
                "There is a playground in {park}", 
                "Kids can play in a playground in {park}"
            )
            false
            '("Unfortunately, there is no playground in {park}")
        }
    }
)

(
    ;; TODO Receive the list of parks from park.clj
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
                    #(get keywords %)
                    (clojure.string/split input #" ")
            )
        )
    )
)

(
    defn get-keyword-data
    "Receive the data regarding the keyword"
    [keyword park]
    (
        case keyword
            "bicicle"
            true
            ;; (
                ;; park/get-bicycle-data park
            ;; )
            "playground"
            true
            ;; (
                ;; park/get-playground-data park
            ;; )
    )
)

(
    defn build-response
    "Build the response depending on what is the keyword and what is the park"
    [keyword park]
    (
        rand-nth
        (
            get
            (
                get
                    responses
                    keyword
            )
            (
                get-keyword-data
                keyword
                park
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
            do
            (
                println keyword subject
            )
            (
                ;; TODO: Introduce a guard clause to handle empty responses list
                ;; TODO: Introduce a guard clause to handle missing subject
                replace-placeholders
                   (build-response keyword subject)
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
