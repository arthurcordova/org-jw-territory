package org.jw.territory.data

data class Territory(

        var uid: String?,
        var name: String?,
        var latitude: Double?,
        var longitude: Double?

) {
    constructor() : this(null, null, null, null)
}
