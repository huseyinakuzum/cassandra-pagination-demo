package com.example.cassandrapaginationdemo.domain.entity

import org.springframework.data.cassandra.core.cql.Ordering.ASCENDING
import org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED
import org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table

@Table(value = "connection_networks")
data class ConnectionNetworks(
    @PrimaryKeyColumn(name = "consumer_id", ordinal = 0, type = PARTITIONED)
    var consumerId: String,

    @PrimaryKeyColumn(name = "priority", ordinal = 1, type = CLUSTERED, ordering = ASCENDING)
    var priority: Double,

    @PrimaryKeyColumn(name = "producer_id", ordinal = 2, type = CLUSTERED, ordering = ASCENDING)
    var producerId: String

)
