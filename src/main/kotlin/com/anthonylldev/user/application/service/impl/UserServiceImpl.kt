package com.anthonylldev.user.application.service.impl

import com.anthonylldev.follow.domain.FollowRepository
import com.anthonylldev.post.domain.repository.PostRepository
import com.anthonylldev.user.application.data.ProfilePostResponse
import com.anthonylldev.user.application.data.ProfileResponse
import com.anthonylldev.user.application.service.UserService
import com.anthonylldev.user.domain.User
import com.anthonylldev.user.domain.UserRepository

class UserServiceImpl(
    private val userRepository: UserRepository,
    private val followRepository: FollowRepository,
    private val postRepository: PostRepository
) : UserService {

    override suspend fun loadProfile(userId: String, callerUserId: String): ProfileResponse? {
        val user: User = this.userRepository.getUserById(userId) ?: return null

        return ProfileResponse(
            userId = user.id,
            username = user.username,
            realName = user.realName,
            imageBase64 = user.imageBase64,
            description = user.description,
            website = user.website,
            followerCount = user.followerCount,
            followingCount = user.followingCount,
            postCount = user.postCount,
            isOwnProfile = userId == callerUserId,
            isFollowing = followRepository.isFollowing(callerUserId, userId)
        )
    }

    override suspend fun getProfilePosts(userId: String): List<ProfilePostResponse> {
        return postRepository.findAllByUser(userId)
    }

    override suspend fun updateUser(request: User, userId: String): User? {
        val user: User = this.userRepository.getUserById(userId) ?: return null
        this.userRepository.updateUser(request, userId)

        return this.userRepository.getUserById(userId)
    }

    override suspend fun getUser(userId: String): User? {
        return this.userRepository.getUserById(userId)
    }
}