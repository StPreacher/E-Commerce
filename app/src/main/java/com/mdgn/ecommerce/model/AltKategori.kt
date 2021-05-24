package com.mdgn.ecommerce.model

data class AltKategori(
    val altID : Int,
    val altTittle : String,
    val altAltKategori : List<ProductList>
)
