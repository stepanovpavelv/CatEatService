package local.home.cateat.authentication.adapters.api

import local.home.cateat.authentication.adapters.api.dto.UserLoginDto
import local.home.cateat.authentication.adapters.api.dto.UserTokenDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/gateway/private/api/v1")
@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:3001"])
class UserGetResource : UserBaseResource() {

    @PostMapping("/user/login")
    fun getUserToken(@RequestBody userEntry: UserLoginDto) : ResponseEntity<UserTokenDto> {
        val jwtToken = createUserToken(userEntry)
        return ResponseEntity.ok(UserTokenDto(jwtToken))
    }
}