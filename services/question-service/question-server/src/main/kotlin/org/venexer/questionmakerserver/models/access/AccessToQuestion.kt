package org.venexer.questionmakerserver.models.access

import org.venexer.questionmakerclient.dto.access.AccessToQuestionDto
import org.venexer.utils.entity.EntityBase
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
interface AccessToQuestion : EntityBase {

    val targetId: Long

    val questionId: Long

    val creatorId: Long

    fun toAccessToQuestionDto() = AccessToQuestionDto(
            id = id,
            creatorId = creatorId,
            targetId = targetId,
            questionId = questionId
    )
}
