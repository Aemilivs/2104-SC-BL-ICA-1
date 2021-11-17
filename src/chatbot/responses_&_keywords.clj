(ns chatbot.responses-&-keywords)

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
        "wc"
        {
            true
            '(
                ""
            )
            false
            '(
                ""
            )
        }
        "attractions"
        {
            true
            '(
                ""
            )
            false
            '(
                ""
            )
        }
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
        "skating"
        {
            true
            '(
                ""
            )
            false
            '(
                ""
            )
        }
        "sports"
        {
            true
            '(
                ""
            )
            false
            '(
                ""
            )
        }
        "playground"
        {
            true
            '(
                "There is a playground in {park}", 
                "Kids can play in a playground in {park}"
            )
            false
            '(
                "Unfortunately, there is no playground in {park}"
            )
        }
        "dogs"
        {
            true
            '(
                ""
            )
            false
            '(
                ""
            )
        }
        "parking"
        {
            true
            '(
                ""
            )
            false
            '(
                ""
            )
        }
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
