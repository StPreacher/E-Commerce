package com.mdgn.ecommerce.model

data class AltKategori(
    var alt_id : Int ?= null,
    var alt_tittle : String ?= null,
    var alt_alt_kategori : List<ProductList> ?= null
)
