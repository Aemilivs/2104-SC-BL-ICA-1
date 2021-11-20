(ns chatbot.responses-and-keywords)

(
    def keywords
    {
        "bicicle" "bicicle",
        "bike" "bicicle",
        "ride" "bicicle",
        "two-wheels" "bicicle",
        "playground" "playground",
     "kids" "playground",
     "wc" "wc",
     "public bathrooms" "wc",
     "attractions" "attractions",
     "skating" "skating",
     "sports" "sports",
     "activities" "sports",
     "fitness" "sports",
     "parking" "parking",
     "vehicle" "parking",
     "dogs" "dogs",
     "pets" "dogs"
    }
)

(
    def responses
    {
        "wc"
        {
            true
            '(
                "Yes, there are public bathrooms in {park}",
              "Yes!",
              "Sure!"
            )
            false
            '(
                "Unfortunatly no wc in {park}",
              "We appologize, {park}'s wc is closed for reconstruction"
            )
        }
        "attractions"
        {
            true
            '(
                "Yes, in {park} you can find cultural and social events, leasure areas, cafes and restaurants",
              "Yes, a lot!"
            )
            false
            '(
                "No, there are no any attractions in {park}",
              "Unfortunatly, main attraction is on reconstruction"
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
                "Yes, there is free skatepark here",
              "You can found publick outdoor skatepark here"
            )
            false
            '(
                "No, there is no skating park here",
              "No"
            )
        }
        "sports"
        {
            true
            '(
                "Different kind of outdoor sports and activities can be found in {park}",
              "Public fitness ground is located in {park}"
            )
            false
            '(
                "No dedicated sport grounds here",
              "No sport grounds, but you can run around the {park}"
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
                "Unfortunately, there is no playground in {park}",
              "We appologize, playground is on reconstruction"
            )
        }
        "dogs"
        {
            true
            '(
                "Of course you can visit {pak} with you good boy or girl",
              "You are welcome in {park} wtih your pet"
            )
            false
            '(
                "We appologize, but you can't walk the dog here",
              "Dogs are not allowed"
            )
        }
        "parking"
        {
            true
            '(
                "Big free parking nearby",
              "Yes, parking is available near {park}",
              "Sure, you can park you vehicle opposite to main entrance"
            )
            false
            '(
                "No free parking here",
              "Parking in the adjacent street, free on weekends"
            )
        }
    }
)
