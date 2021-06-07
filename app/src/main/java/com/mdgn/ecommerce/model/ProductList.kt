package com.mdgn.ecommerce.model

data class ProductList(
        var alt_alt_id : Int ?= null,
        var alt_alt_tittle : String ?= null,
        var detail : List<ProductDetailList> ?= null
)
