package com.spring.user.service

import com.google.gson.Gson
import com.spring.user.model.user.SignInRequestDto
import com.spring.user.model.user.SignupRequestDto
import com.spring.user.model.user.UserInfoDetails
import com.spring.user.repository.UserRepository
import com.spring.user.util.ModelMapper
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

@Service
class UserServiceImpl : UserService {

    companion object : KLogging() {
        const val USER_ID: String = "user-id"
        const val USER_NAME: String = "user-name"
        const val PASSWORD: String = "password"
        const val REGISTRATION: String = "isRegistered"
    }

    @Autowired
    lateinit var requestMapper: ModelMapper<SignupRequestDto, UserInfoDetails>

    @Autowired
    lateinit var userRepo: UserRepository

    override fun saveUserRecord(requestedUser: SignupRequestDto): Optional<MutableMap<String, Any>> {

        val entity = requestMapper.toEntity(requestedUser)
        entity.register = true
        logger.info("saveUserRecord -> entity : ${Gson().toJson(entity)}")
        val savedData = userRepo.save(entity)

        val mapResponse = mutableMapOf<String, Any>()
        with(mapResponse) {
            put(USER_ID, savedData.id)
            put(USER_NAME, savedData.firstName)
            put(PASSWORD, savedData.password)
            put(REGISTRATION, savedData.register)
        }
        return Optional.of(mapResponse)
    }

    override fun authenticateLoginUser(credentials: SignInRequestDto): Boolean {

        try {
            val entity = userRepo.findByEmail(credentials.userName)
            return Stream.of(entity)
                .filter { d -> d.register }
                .filter { f -> f.email == credentials.userName }
                .filter { p -> p.password.contentEquals(credentials.password) }.map {
                    entity.login = it.register
                    userRepo.save(entity)
                    entity.login
                }.findFirst()
                .orElseGet {
                    entity.login = false
                    userRepo.save(entity)
                    entity.login
                }
        } catch (exp: Exception) {
            logger.info("Exception in authenticateLoginUser :: ${exp.localizedMessage}")
            return false
        }
    }

    override fun getAllUserRecords(): List<SignupRequestDto> {

        return userRepo.findAll()
            .stream()
            .map { userInfoDetails -> requestMapper.fromEntity(userInfoDetails) }
            .collect(Collectors.toList())
    }

    override fun validateUser(userName: String): Boolean {

        try {
            val user = userRepo.findByEmail(userName)
            if (Objects.nonNull(user)) {
                return Optional.of(user)
                    .filter { l -> l.register && l.login }
                    .map { true }
                    .orElseGet { false }
            }
        } catch (exp: Exception) {
            logger.info("Exception in user validation :: ${exp.localizedMessage}")
            return false
        }
        return false
    }
}
