package no.ntnu.gr04.backend.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import no.ntnu.gr04.backend.dto.household.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/public/household")
@Tag(name = "Households", description = "Operations related to households")
class HouseholdController {
    @Operation(summary = "Create a household and set it as the user's household")
    @PostMapping
    fun create(): ResponseEntity<HouseholdCreateResponse> {
        return ResponseEntity.ok(HouseholdCreateResponse(
            id = UUID.randomUUID()
        ))
    }
}