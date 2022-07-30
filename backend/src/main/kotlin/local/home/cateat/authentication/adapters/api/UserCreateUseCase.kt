package local.home.cateat.authentication.adapters.api

import local.home.cateat.authentication.adapters.api.dto.UserLoginDto
import local.home.cateat.authentication.adapters.api.dto.UserRegistrationDto
import local.home.cateat.authentication.adapters.api.dto.UserTokenDto
import local.home.cateat.authentication.core.UserRegistrationUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/gateway/private/api/v1")
class UserCreateUseCase(
    private val userCreateUseCase: UserRegistrationUseCase
) : UserBaseResource() {

    @PostMapping("/user/register")
    fun registerUser(@RequestBody userRegisterEntry: UserRegistrationDto) : ResponseEntity<UserTokenDto> {
        userCreateUseCase.saveNewUser(userRegisterEntry)
        val jwtToken = createUserToken(UserLoginDto.of(userRegisterEntry))
        return ResponseEntity.ok(UserTokenDto(jwtToken))
    }
}