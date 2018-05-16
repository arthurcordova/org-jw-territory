package org.jw.territory.data

data class Territory(

        var uid: String?,
        var location: Location?

) {
    constructor() : this(null, null)
}
