package com.example.cassandrapaginationdemo.endpoint

import com.example.cassandrapaginationdemo.service.ConnectionNetworksService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class ConnectionNetworksRequest(
    val consumerId: String,
    val priority: Double,
    val producerId: String
)

data class DummyConnectionNetworksRequest(
    val consumerId: String,
    val size: Int
)

data class ConnectionNetworksDTO(
    val consumerId: String,
    val priority: Double,
    val producerId: String
)

data class ConnectionNetworksResponse(
    val connectionNetworks: List<ConnectionNetworksDTO>,
    val cursor: String
) {
    constructor(connectionNetworks: List<ConnectionNetworksDTO>) : this(connectionNetworks, "")
}

@RestController
@RequestMapping("/connection-networks")
class ConnectionNetworksController(val connectionNetworksService: ConnectionNetworksService) {

    @PostMapping
    fun save(request: ConnectionNetworksRequest) {
        connectionNetworksService.save(request.consumerId, request.priority, request.producerId)
    }

    @PostMapping("/dummy")
    fun createDummy(request: DummyConnectionNetworksRequest) {
        connectionNetworksService.createDummy(request.consumerId, request.size)
    }

    @GetMapping("/{consumerId}")
    fun getConnectionNetworksByConsumerId(
        @PathVariable consumerId: String,
        @RequestParam(required = false, defaultValue = "10") pageSize: Int,
        @RequestParam(required = false, defaultValue = "") cursor: String
    ): ResponseEntity<ConnectionNetworksResponse> {
        return ResponseEntity(
            connectionNetworksService.getConnectionNetworksByConsumerId(consumerId, pageSize, cursor),
            HttpStatus.OK
        )
    }

    @DeleteMapping
    fun deleteConnectionNetwork(
        request: ConnectionNetworksRequest
    ): ResponseEntity<Void> {
        connectionNetworksService.deleteConnectionNetwork(request.consumerId, request.priority, request.producerId)
        return ResponseEntity(
            HttpStatus.NO_CONTENT
        )
    }
}
