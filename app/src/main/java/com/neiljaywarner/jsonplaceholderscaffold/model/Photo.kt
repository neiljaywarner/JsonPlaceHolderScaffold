package com.neiljaywarner.jsonplaceholderscaffold.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Photo(@PrimaryKey
                 val id: Int = 0,
                 val albumId: Int = 0,
                 val lastRefresh: Long = System.currentTimeMillis(),
                 val title: String = "", val url: String = "", val thumbnailUrl: String = "")
/*
{
albumId: 1,
id: 1,
title: "accusamus beatae ad facilis cum similique qui sunt",
url: "https://via.placeholder.com/600/92c952",
thumbnailUrl: "https://via.placeholder.com/150/92c952"
}
 */