package com.example.cassandrapaginationdemo.service

import com.datastax.oss.protocol.internal.util.Bytes
import com.example.cassandrapaginationdemo.domain.entity.ConnectionNetworks
import com.example.cassandrapaginationdemo.domain.repository.ConnectionNetworksRepository
import com.example.cassandrapaginationdemo.endpoint.ConnectionNetworksDTO
import com.example.cassandrapaginationdemo.endpoint.ConnectionNetworksResponse
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.data.cassandra.core.query.CassandraPageRequest
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.nio.ByteBuffer
import javax.xml.bind.DatatypeConverter

@Service
class ConnectionNetworksService(val connectionNetworksRepository: ConnectionNetworksRepository) {
    fun save(consumerId: String, priority: Double, producerId: String) {
        connectionNetworksRepository.save(ConnectionNetworks(consumerId, priority, producerId))
    }

    fun createDummy(consumerId: String, size: Int) {
        (0..size).map { i -> save(consumerId, i.toDouble(), RandomStringUtils.randomAlphabetic(10)) }
    }

    fun getConnectionNetworksByConsumerId(
        consumerId: String,
        pageSize: Int,
        cursor: String
    ): ConnectionNetworksResponse {
        val pageRequest = getCassandraPageRequest(pageSize, cursor)
        val connectionNetworks = connectionNetworksRepository.findByConsumerId(consumerId, pageRequest)

        val currentPagingState = (connectionNetworks.pageable as CassandraPageRequest).pagingState
        return currentPagingState?.let { byteBuffer ->
            ConnectionNetworksResponse(
                connectionNetworks.map {
                    ConnectionNetworksDTO(it.consumerId, it.priority, it.producerId)
                }.toList(),
                DatatypeConverter.printHexBinary(Bytes.getArray(byteBuffer))
            )
        } ?: ConnectionNetworksResponse(
            connectionNetworks.map {
                ConnectionNetworksDTO(it.consumerId, it.priority, it.producerId)
            }.toList()
        )
    }

    private fun getCassandraPageRequest(
        pageSize: Int,
        cursor: String?
    ): CassandraPageRequest {
        return when (StringUtils.isBlank(cursor)) {
            true -> {
                CassandraPageRequest.first(pageSize)
            }

            false -> {
                CassandraPageRequest.of(
                    PageRequest.ofSize(pageSize),
                    ByteBuffer.wrap(DatatypeConverter.parseHexBinary(cursor))
                )
            }
        }
    }

    fun deleteConnectionNetwork(consumerId: String, priority: Double, producerId: String) {
        connectionNetworksRepository.delete(ConnectionNetworks(consumerId, priority, producerId))
    }
}
