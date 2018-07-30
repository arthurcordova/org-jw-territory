package org.jw.territory.data

import org.jw.territory.TerritoryStatus

data class Territory(

        var uid: String?,
        var name: String?,
        var latitude: Double?,
        var longitude: Double?,
        var zipcode: String?,
        var address: String?,
        var status: TerritoryStatus?

) {
    constructor() : this(null, null, null, null, null, null, null)
}
