package com.example.cassandrapaginationdemo.domain.repository

import com.example.cassandrapaginationdemo.domain.entity.ConnectionNetworks
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice

interface ConnectionNetworksRepository : CassandraRepository<ConnectionNetworks, String> {
    fun findByConsumerId(consumerId: String, pageable: Pageable): Slice<ConnectionNetworks>
}
