/*
 * The MIT License (MIT)
 *
 * Designed and developed by 2018 skydoves (Jaewoong Eum)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.skydoves.githubfollows.persistence.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.test.runner.AndroidJUnit4
import com.skydoves.githubfollows.preference.PreferenceComponent_PrefAppComponent
import com.skydoves.githubfollows.preference.Preference_UserProfile
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Developed by skydoves on 2018-03-03.
 * Copyright (c) 2018 skydoves rights reserved.
 */

@RunWith(AndroidJUnit4::class)
class ProfileEntityTest : PreferenceTest() {

  private lateinit var sharedPreferences: SharedPreferences

  private lateinit var component: PreferenceComponent_PrefAppComponent
  private lateinit var profile: Preference_UserProfile

  private val test_name = "test_skydoves"
  private val test_position = 1000

  @Before
  fun initialize() {
    component = PreferenceComponent_PrefAppComponent.getInstance()
    profile = Preference_UserProfile.getInstance(context)
    sharedPreferences = context.getSharedPreferences(profile.entityName, Context.MODE_PRIVATE)
  }

  @Test
  fun initTest() {
    assertThat(component, notNullValue())
    assertThat(profile, notNullValue())
  }

  @Test
  fun profileTest() {
    profile.clear()
    sharedPreferences.edit().apply {
      putString(profile.nameKeyName(), test_name)
      putInt(profile.menuPositionKeyName(), test_position)
    }.apply()

    assertThat(profile.name, `is`(test_name))
    assertThat(profile.menuPosition, `is`(test_position))
    assertThat(sharedPreferences.getString(profile.nameKeyName(), ""), `is`(profile.name))
    assertThat(sharedPreferences.getInt(profile.menuPositionKeyName(), 0), `is`(profile.menuPosition))
  }
}
