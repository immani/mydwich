package com.immani.mydwich

class RestaurantService {

    static transactional = true

    def fillNewRestaurant(Restaurant restaurant){

        // Creating product categories

        ProductCategory sandwich =  new ProductCategory(
                restaurant: restaurant,
                name_fr: "Sandwich",
                name_en: "Sandwichs",
                name_nl: "Sandwich",
                catorder:1);

        ProductCategory salad = new ProductCategory(
                restaurant: restaurant,
                name_fr: "Salade",
                name_en: "Salad",
                name_nl: "Salad",
                catorder:2);

        ProductCategory drinks = new ProductCategory(
                restaurant: restaurant,
                name_fr: "Boisson",
                name_en: "Drinks",
                name_nl: "Drinken",
                catorder:3);

        ProdOptionCategory typedepain = new ProdOptionCategory(
                productCategories: [sandwich],
                restaurant: restaurant,
                name_fr: "type de pain",
                name_nl: "type broode",
                name_en: "bread type",
                type: "radio");

        ProdOptionCategory garniture = new ProdOptionCategory(
                productCategories: [sandwich],
                restaurant: restaurant,
                name_fr: "Garniture",
                name_nl: "Garniture_NL",
                name_en: "Garniture",
                type: "checkbox");

        ProdOptionCategory sauce = new ProdOptionCategory(
                productCategories: [sandwich],
                restaurant: restaurant,
                name_fr: "Sauce",
                name_nl: "Saus",
                name_en: "Sauce",
                type: "checkbox"
        )

        ProdOption andalouse = new ProdOption(
                name_fr: "Andalouse",
                name_nl: "Andalous",
                name_en: "Andalouse",
                price: 0.3);

        ProdOption moutarde = new ProdOption(
                name_fr: "Moutarde",
                name_nl: "Mustard",
                name_en: "Mustard",
                price: 0.3);

        ProdOption blanche = new ProdOption(
                name_fr: "Blanche",
                name_nl: "Witte",
                name_en: "White",
                price: 0.3);



        ProdOption cornichon = new ProdOption(
                name_fr: "Cornichon",
                name_nl: "cornichon_nl",
                name_en: "pickles",
                price: 0.3);

        ProdOption salade = new ProdOption(
                name_fr: "Salade",
                name_nl: "Salad",
                name_en: "Salad",
                price: 0.3);

        ProdOption oeufdur = new ProdOption(
                name_fr: "Oeuf dur",
                name_nl: "Ei",
                name_en: "Hard boiled egg",
                price: 0.3);

        ProdOption anchois = new ProdOption(
                name_fr: "Anchois",
                name_nl: "anchois_nl",
                name_en: "Anchovies",
                price: 0.3);



    }
                }
