package com.neiljaywarner.jsonplaceholderscaffold.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Photo(
        @PrimaryKey
        var id: Int = 0,var albumId: Int = 0,
                 var title: String = "",
                 var url: String = "",
                 var thumbnailUrl: String = "")
/*
{
albumId: 1,
id: 1,
title: "accusamus beatae ad facilis cum similique qui sunt",
url: "https://via.placeholder.com/600/92c952",
thumbnailUrl: "https://via.placeholder.com/150/92c952"
}
 */