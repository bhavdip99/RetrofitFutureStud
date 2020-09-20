package com.bhavdip.retrofitfuturestud.data.model

import java.sql.Date

data class Tutorial(
    var page: Int?,
    var order: String?,
    var author: String?,
    var published_at: Date?,
    var tutorial_name: String?
)