package com.paint.fitapp.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@Scope
@Retention(value = RetentionPolicy.RUNTIME)
annotation class AppScope