package local.home.cateat.indication.adapters.api

import local.home.cateat.indication.adapters.api.dto.IndicationDatesDto
import local.home.cateat.indication.adapters.api.dto.IndicationDto
import local.home.cateat.indication.core.IndicationGetUseCase
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/gateway/private/api/v1")
class IndicationGetResource(private val getUseCase: IndicationGetUseCase) {

    @GetMapping("/entry/{key}")
    fun getEntry(@PathVariable("key") key: Long): ResponseEntity<IndicationDto> {
        val item = getUseCase.findByKey(key)
        return ResponseEntity(IndicationDto(item), HttpHeaders(), HttpStatus.OK)
    }

    @PostMapping("/entries")
    fun getEntry(@RequestBody dateInfo: IndicationDatesDto): ResponseEntity<List<IndicationDto>> {
        val items = getUseCase.findByDates(dateInfo.startDate, dateInfo.finishDate)
        return ResponseEntity(IndicationDto.listOf(items), HttpHeaders(), HttpStatus.OK)
    }
}