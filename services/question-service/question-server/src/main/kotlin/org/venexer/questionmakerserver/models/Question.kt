package org.venexer.questionmakerserver.models

import org.venexer.questionmakerclient.dto.QuestionInfoDto
import org.venexer.utils.entity.EntityBase
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet

@Entity
@Table(name = "questions")
data class Question (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        override var id: Long = 0,

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_time")
        override var createdTime: Date = Date(),

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "updated_time")
        override var updatedTime: Date = Date(),

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "deleted_time")
        override var deletedTime: Date? = null,

        val text: String?,

        @Column(name = "resource_link")
        val resourceLink: String?, //sound, picture

        val answers: HashSet<String> = HashSet(),

        @Column(name = "true_answer")
        val trueAnswer: String?,

        @Column(name = "group_id")
        val groupId: Long?, //TODO: oneToMany

        @Column(name = "created_user_id")
        val creatorId: Long?, //TODO: oneToMany

        @Column(name = "organization_id")
        val organizationId: Long?, //TODO: oneToMany

        @Column(name = "is_private")
        val isPrivate: Boolean = false,

        @Column(name = "is_hidden")
        val isHidden: Boolean = false
) : EntityBase {
    fun toQuestionInfoDto() = QuestionInfoDto(
        id = id,
        text = text,
        creatorId = creatorId,
        organizationId = organizationId,
        isPrivate = isPrivate,
        isHidden = isHidden
    )
}