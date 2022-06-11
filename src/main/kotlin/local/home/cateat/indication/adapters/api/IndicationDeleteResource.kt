package local.home.cateat.indication.adapters.api

import local.home.cateat.indication.core.IndicationRemoveUseCase
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/gateway/private/api/v1")
class IndicationDeleteResource(private val deleteUseCase: IndicationRemoveUseCase) {

    @DeleteMapping("/delete/entry/{key}")
    fun deleteEntryByKey(@PathVariable("key") key: Long): ResponseEntity<Long> {
        val id = deleteUseCase.deleteEntryByKey(key)
        return ResponseEntity(id, HttpHeaders(), HttpStatus.OK)
    }
}