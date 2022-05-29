package com.anthonylldev.follow.application.service.impl

import com.anthonylldev.follow.application.service.FollowService
import com.anthonylldev.follow.domain.FollowRepository
import com.anthonylldev.user.domain.UserRepository

class FollowServiceImpl(
    private val followRepository: FollowRepository,
    private val userRepository: UserRepository
) : FollowService {


    override suspend fun followUserIfExists(userId: String, followedUserId: String): Boolean {
        val userExist = userRepository.getUserById(userId) != null
        val followedUserExist = userRepository.getUserById(followedUserId) != null


        if(!userExist || !followedUserExist) {
            return false
        }

        userRepository.incrementFollow(userId, followedUserId)

        this.followRepository.insertOne(userId, followedUserId)

        return true
    }

    override suspend fun unFollowUserIfExists(userId: String, unFollowedUserId: String): Boolean {
        val isDeleted = followRepository.deleteOne(userId, unFollowedUserId)

        if(isDeleted) userRepository.reduceFollow(userId, unFollowedUserId)

        return isDeleted
    }
}