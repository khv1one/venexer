package org.venexer.utils.entity

import java.util.*

interface EntityBase {
        var id: Long

        var createdTime: Date

        var updatedTime: Date

        var deletedTime: Date?

}
