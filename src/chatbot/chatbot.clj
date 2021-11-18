(ns chatbot.chatbot
        (:require clojure.string)
        (:require [chatbot.park :as park])
  (:require [chatbot.responses-and-keywords :as data]))

;; TODO move responses and keywords to a separated file.
;; TODO Receive the list of parks from park.clj
;; TODO Approximate the match between input and the keyword
;; e.g. bertrmk => bertramka; klinskeho zahrada => kinskeho-zahrada

(
        defn get-park-titles-set
        "Get park titles set"
        []
        (
            set (keys (park/read-data))
        )
)

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
                    #(get data/keywords %)
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
                    data/responses
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
    defn measure-match
    "Find the conditional value representing the match between two words."
    (
        ;; TODO: Add type constraint
        [left right]
        (
            measure-match 
                (clojure.string/split left #"") 
                (clojure.string/split right #"") 
                0
        )
    )
    (
        [left right weight]
        (
            if (or (empty? left) (empty? right))
                weight
                (
                    if (= (first left) (first right))
                        (measure-match (rest left) (rest right) (+ weight 1))
                        (measure-match left (rest right) weight)
                )
        )
    )
)

(
    def match-value
    (
        create-struct
            :word
            :value
    )
)

(
    defn match-word-against-collection
    "Find the most matching word in the collection"
    [collection input]
    (
        reduce 
            #(
                if
                    (
                        > 
                            (:value %1) 
                            (:value %2)
                    ) 
                    %1 
                    %2
            )
            (
                map
                    #(
                        struct 
                            match-value 
                            %
                            (
                                measure-match
                                    input
                                    %
                            )
                    )
                    collection
            )
    )
)

(
    defn match-collection-against-collection
    "Find the most matching word among two collections"
    [left right]
    (
        :word
        (
            reduce 
                #(
                    if
                        (
                            > 
                                (:value %1) 
                                (:value %2)
                        ) 
                        %1 
                        %2
                )
                (
                    map
                        #(match-word-against-collection right %)
                        left
                )
        )
    )
)

(
    defn match-subject
    "Find the subject in the input."
    [input]
    (
        match-collection-against-collection 
            (clojure.string/split input #" ")
            (get-park-titles-set)
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
