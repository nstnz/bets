package dev.nst.core.domain.mapper

abstract class BaseDataMapper<DOM, DB, NET> {

    abstract fun mapToDomain(model: DB): DOM
    abstract fun mapToDb(model: NET): DB
    abstract fun mapToDb2(model: DOM): DB

    fun mapToDomain(models: List<DB>): List<DOM> {
        return models.map { mapToDomain(it) }
    }

    fun mapToDb(models: List<NET>): List<DB> {
        return models.map { mapToDb(it) }
    }

    fun mapToDb2(models: List<DOM>): List<DB> {
        return models.map { mapToDb2(it) }
    }
}