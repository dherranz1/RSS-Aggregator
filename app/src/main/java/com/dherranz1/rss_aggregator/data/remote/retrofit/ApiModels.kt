package com.dherranz1.rss_aggregator.data.remote.retrofit

import org.simpleframework.xml.*

@Root(name = "rss", strict = false)
data class RssApiModel @JvmOverloads constructor(

    @field:Attribute(name = "version")
    var version: String? = null,

    @field:Element(name="channel", required = false)
    var channel: ChannelApiModel = ChannelApiModel()

)

@Root(name = "channel", strict = false)
data class ChannelApiModel @JvmOverloads constructor(

    @field:Path("title")
    @field:Text(required = false)
    var title: String? = null,

    @field:Path("link")
    @field:Text(required = false)
    var link: String? = null,

    @field:Path("pubDate")
    @field:Text(required = false)
    var pubDate: String? = null,

    @field:Path("description")
    @field:Text(required = false)
    var description: String? = null,

    @field:ElementList(name = "item", inline = true, required = false)
    var itemList: List<ItemApiModel>? = null
)

@Root(name = "item", strict = false)
data class ItemApiModel @JvmOverloads constructor(

    @field:Path("title")
    @field:Text(required = false)
    var title: String? = null,

    @field:Path("description")
    @field:Text(required = false)
    var description: String? = null,

    @field:Path("link")
    @field:Text(required = false)
    var link: String? = null,

    @field:Path("media:thumbnail")
    @field:Attribute(name = "url", required = false)
    var linkmedia: String? = null
)
