package com.jujodevs.habitsappcourse.authentication.domain.matcher

interface EmailMatcher {
    fun isValid(email: String): Boolean
}