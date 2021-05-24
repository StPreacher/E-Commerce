package com.mdgn.ecommerce.model

data class Kategori(
    val id : Int,
    val title : String,
    val image : String,
    val altKategori : List<AltKategori>
)
