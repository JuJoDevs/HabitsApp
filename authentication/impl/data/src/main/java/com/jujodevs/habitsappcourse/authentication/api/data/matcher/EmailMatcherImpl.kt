package com.jujodevs.habitsappcourse.authentication.api.data.matcher

import android.util.Patterns
import com.jujodevs.habitsappcourse.authentication.domain.matcher.EmailMatcher

class EmailMatcherImpl : EmailMatcher {
    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}