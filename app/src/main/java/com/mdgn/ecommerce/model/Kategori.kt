package com.mdgn.ecommerce.model

data class Kategori(
    var id: Int? = null,
    var tittle: String? = null,
    var url: String? = null,
    var alt_kategori: List<AltKategori>? = null
)
