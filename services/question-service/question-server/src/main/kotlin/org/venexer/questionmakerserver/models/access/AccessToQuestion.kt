package org.venexer.questionmakerserver.models.access

import org.venexer.utils.entity.EntityBase
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
interface AccessToQuestion : EntityBase {

    val targetId: Long

    val questionId: Long

    val creatorId: Long

}
