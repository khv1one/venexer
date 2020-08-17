package org.venexer.questionmakerserver.models

import org.venexer.questionmakerclient.dto.QuestionInfoDto
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet

@Entity
@Table(name = "questions")
data class Question (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

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

    @Column(name = "for_organization_id")
    val forOrganizationId: Long?, //TODO: oneToMany

    @Column(name = "is_private")
    val isPrivate: Boolean = false,

    @Column(name = "is_hidden")
    val isHidden: Boolean = false,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    val createdTime: Date = Date(),

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_time")
    val updatedTime: Date = Date(),

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_time")
    val deletedTime: Date?
) {
    fun toQuestionInfoDto() = QuestionInfoDto(
        id = id,
        text = text,
        creatorId = creatorId,
        forOrganizationId = forOrganizationId,
        isPrivate = isPrivate,
        isHidden = isHidden
    )
}