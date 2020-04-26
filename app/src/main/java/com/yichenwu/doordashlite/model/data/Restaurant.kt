package com.yichenwu.doordashlite.model.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Restaurant(
    @JsonProperty val id: Long,
    @JsonProperty val name: String,
    @JsonProperty val description: String?,
    @JsonProperty val status: String?,
    @JsonProperty("cover_img_url") val imageUrl: String?,
    @JsonProperty("delivery_fee") val deliveryFee: Long?
)
