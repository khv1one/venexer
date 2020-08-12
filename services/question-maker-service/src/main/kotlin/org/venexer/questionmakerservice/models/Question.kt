package org.venexer.questionmakerservice.models

import java.time.Instant
import java.time.format.DateTimeFormatter
import javax.persistence.*

@Entity
@Table(name = "questions")
data class Question (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    val text: String?,

    @Column(name = "resource_link")
    val resourceLink: String?, //sound, picture

    val answers: Set<String> = HashSet(),

    @Column(name = "true_answer")
    val trueAnswer: String?,

    @Column(name = "sub_group_id")
    val subGroupId: Long?, //TODO: oneToMany

    @Column(name = "created_user_id")
    val createdUserId: Long?, //TODO: oneToMany

    @Column(name = "for_organization_id")
    val forOrganizationId: Long?, //TODO: oneToMany

    @Column(name = "is_private")
    val isPrivate: Boolean = false,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    val createdTime: String = DateTimeFormatter.ISO_INSTANT.format(Instant.now()),

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_time")
    val updatedTime: String = DateTimeFormatter.ISO_INSTANT.format(Instant.now()),

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_time")
    val deletedTime: String?
)