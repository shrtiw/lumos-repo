package com.Controller

import com.Model.User
import com.Repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/")
    fun getAllUsers(): ResponseEntity<List<User>> {
        return ResponseEntity.ok(userRepository.findAll())
    }

    @PostMapping("/")
    fun saveNewUser(@RequestBody user: User): ResponseEntity<User>{
        return ResponseEntity.ok(userRepository.save(user))
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String): ResponseEntity<User> {
        return ResponseEntity.ok(userRepository.findById(id).orElse(null))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): ResponseEntity<User> {
        val deletedUser = userRepository.findById(id).orElse(null)
        userRepository.deleteById(id)
        return ResponseEntity.ok(deletedUser)
    }
}