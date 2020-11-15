package org.venexer.utils.service

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.venexer.utils.entity.EntityBase
import java.util.*

@Service
abstract class ServiceBase<T : EntityBase, K, R: CrudRepository<T, K>>(private val repo: R) {
    fun create(entity: T): T {
        return repo.save(entity)
    }

    fun delete(entity: T): T {
        entity.deletedTime = Date()
        entity.updatedTime = Date()

        return repo.save(entity)
    }

    fun update(entityNew: T, entityOld: T): T {
        entityNew.id = entityOld.id
        entityNew.createdTime = entityOld.createdTime

        return repo.save(entityNew)
    }

}