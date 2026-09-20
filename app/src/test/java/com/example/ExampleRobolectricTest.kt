package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Commando Fit", appName)
  }

  @Test
  fun `verify commando fit gym business config`() {
    assertEquals("Commando Fit Gym", com.example.config.CommandoFitConfig.BUSINESS_NAME)
    assertEquals("085788 57536", com.example.config.CommandoFitConfig.PHONE_DISPLAY)
    assertEquals("4.8", com.example.config.CommandoFitConfig.RATING_STARS)
    assertEquals(43, com.example.config.CommandoFitConfig.TOTAL_REVIEWS_COUNT)
    assertEquals(5, com.example.config.CommandoFitConfig.PROGRAMS.size)
    assertEquals(3, com.example.config.CommandoFitConfig.MEMBERSHIP_PLANS.size)

    // Verify weekly opening hours
    val sunday = com.example.config.CommandoFitConfig.WEEKLY_SCHEDULE.find { it.day == "Sunday" }
    assertEquals("Closed", sunday?.hours)
    val monday = com.example.config.CommandoFitConfig.WEEKLY_SCHEDULE.find { it.day == "Monday" }
    assertEquals("5:00 AM – 10:00 PM", monday?.hours)

    // Verify classes schedule
    assertEquals(6, com.example.config.CommandoFitConfig.GYM_CLASSES.size)
    val strengthClasses = com.example.config.CommandoFitConfig.GYM_CLASSES.filter { it.type == "Strength Training" }
    assertEquals(3, strengthClasses.size)

    // Verify trainers placeholders
    assertEquals(3, com.example.config.CommandoFitConfig.TRAINERS.size)
    com.example.config.CommandoFitConfig.TRAINERS.forEach { trainer ->
      assertEquals("[Add Trainer Photo]", trainer.photoPlaceholderText)
      assertEquals("[Trainer Name]", trainer.namePlaceholderText)
      org.junit.Assert.assertTrue(trainer.bioPlaceholderText.startsWith("[Trainer Bio/Expertise]"))
    }
  }
}
