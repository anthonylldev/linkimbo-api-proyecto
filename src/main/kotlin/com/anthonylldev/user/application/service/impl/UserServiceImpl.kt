package com.anthonylldev.user.application.service.impl

import com.anthonylldev.follow.domain.FollowRepository
import com.anthonylldev.user.application.ProfileResponse
import com.anthonylldev.user.application.service.UserService
import com.anthonylldev.user.domain.User
import com.anthonylldev.user.domain.UserRepository

class UserServiceImpl(
    private val userRepository: UserRepository,
    private val followRepository: FollowRepository
) : UserService {

    override suspend fun loadProfile(userId: String, callerUserId: String): ProfileResponse? {
        val user: User = this.userRepository.getUserById(userId) ?: return null

        return ProfileResponse(
            userId = user.id,
            username = user.username,
            realName = user.realName,
            profilePictureUrl = user.profilePictureUrl,
            description = user.description,
            website = user.website,
            followerCount = user.followerCount,
            followingCount = user.followingCount,
            postCount = user.postCount,
            isOwnProfile = userId == callerUserId,
            isFollowing = followRepository.isFollowing(callerUserId, userId)
        )
    }

}