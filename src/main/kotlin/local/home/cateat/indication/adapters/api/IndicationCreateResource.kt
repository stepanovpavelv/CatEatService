package local.home.cateat.indication.adapters.api

import local.home.cateat.indication.adapters.api.dto.IndicationCreateDto
import local.home.cateat.indication.core.IndicationCreateUseCase
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/gateway/private/api/v1")
class IndicationCreateResource(private val createUseCase: IndicationCreateUseCase) {

    @PostMapping("/create/entry")
    fun createEntry(@RequestBody entry: IndicationCreateDto): ResponseEntity<Long> {
        val id = createUseCase.createEntry(entry)
        return ResponseEntity(id, HttpHeaders(), HttpStatus.OK)
    }
}