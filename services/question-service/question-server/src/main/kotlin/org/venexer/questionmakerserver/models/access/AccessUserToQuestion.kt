package org.venexer.questionmakerserver.models.access

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "access_user_to_question")
data class AccessUserToQuestion(
        override var id: Long,

        @Column(name = "question_id")
        override val questionId: Long,

        @Column(name = "created_user_id")
        override val creatorId: Long,

        @Column(name = "target_user_id")
        override val targetId: Long,

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_time")
        override var createdTime: Date = Date(),

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "updated_time")
        override var updatedTime: Date = Date(),

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "deleted_time")
        override var deletedTime: Date? = null
) : AccessToQuestion
