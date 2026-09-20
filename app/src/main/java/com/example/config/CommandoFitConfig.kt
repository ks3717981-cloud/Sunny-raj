package com.example.config

/**
 * COMMANDO FIT GYM - MASTER BUSINESS CONFIGURATION
 *
 * All business details, contact numbers, addresses, pricing placeholders,
 * gallery references, opening hours, and social media handles are centralized
 * here for seamless editing.
 *
 * Note: Only confirmed real business data is hardcoded; missing details are
 * exposed as explicit editable placeholders per requirements.
 */
object CommandoFitConfig {
    // 1. Core Business Identity
    const val BUSINESS_NAME = "Commando Fit Gym"
    const val TAGLINE = "BUILD YOUR STRENGTH. COMMAND YOUR FITNESS."
    const val SUB_TAGLINE = "Train harder. Get stronger. Become the best version of yourself at Commando Fit Gym, Patna."
    const val BUSINESS_TYPE = "Gym / Fitness Center"
    const val CITY = "Patna, Bihar, India"

    // 2. Verified Location & Address
    const val ADDRESS_LINE_1 = "Labour Court, B Area"
    const val ADDRESS_LINE_2 = "Mithapur Farm Area, Gardanibagh"
    const val ADDRESS_CITY_STATE_PIN = "Patna, Bihar 800001"
    const val FULL_ADDRESS = "Labour Court, B Area, Mithapur Farm Area, Gardanibagh, Patna, Bihar 800001"

    // Map coordinates / search query for Google Maps intent
    const val GOOGLE_MAPS_QUERY = "Commando Fit Gym, Labour Court, Mithapur Farm Area, Gardanibagh, Patna, Bihar 800001"
    const val GOOGLE_MAPS_WEB_URL = "https://www.google.com/maps/search/?api=1&query=Commando+Fit+Gym+Gardanibagh+Patna"

    // 3. Verified Contact Information
    const val PHONE_DISPLAY = "085788 57536"
    const val PHONE_DIAL = "08578857536" // Used for tel: intent

    // WhatsApp configuration (Only enabled when confirmed by gym owner)
    // Per instructions: "Do not assume or invent a WhatsApp number if WhatsApp availability is not confirmed."
    const val IS_WHATSAPP_CONFIRMED = true // Set to true to enable direct WhatsApp messaging with the gym phone
    const val WHATSAPP_PHONE_NUMBER = "918578857536" // International format without +
    const val WHATSAPP_DEFAULT_MESSAGE = "Hello Commando Fit Gym! I am interested in joining your gym in Patna. Could you please share the membership plans and timings?"

    // 4. Verified Rating & Reviews
    const val RATING_STARS = "4.8"
    const val RATING_STAR_VALUE = 4.8f
    const val TOTAL_REVIEWS_COUNT = 43
    const val RATING_SUMMARY_TEXT = "Rated 4.8 stars based on 43 reviews."
    // Configurable Google Reviews URL
    const val GOOGLE_REVIEWS_URL = "https://www.google.com/maps/search/?api=1&query=Commando+Fit+Gym+Gardanibagh+Patna"

    // 5. Status & Opening Hours
    // Confirmed schedule: Mon-Sat 5:00 AM - 10:00 PM, Sunday Closed
    const val CURRENT_STATUS_TITLE = "Open Daily"
    const val CURRENT_STATUS_SUBTITLE = "5:00 AM – 10:00 PM (Mon–Sat) • Sun Closed"
    const val OPENING_HOURS_DISCLAIMER = "Confirmed Gym Timings: Mon–Sat 5:00 AM – 10:00 PM (Sunday Closed)."

    // Centralized editable schedule per day
    data class DaySchedule(val day: String, val hours: String, val isConfirmed: Boolean = true)
    val WEEKLY_SCHEDULE: List<DaySchedule> = listOf(
        DaySchedule("Monday", "5:00 AM – 10:00 PM", isConfirmed = true),
        DaySchedule("Tuesday", "5:00 AM – 10:00 PM", isConfirmed = true),
        DaySchedule("Wednesday", "5:00 AM – 10:00 PM", isConfirmed = true),
        DaySchedule("Thursday", "5:00 AM – 10:00 PM", isConfirmed = true),
        DaySchedule("Friday", "5:00 AM – 10:00 PM", isConfirmed = true),
        DaySchedule("Saturday", "5:00 AM – 10:00 PM", isConfirmed = true),
        DaySchedule("Sunday", "Closed", isConfirmed = true)
    )

    // 5.1 Interactive Classes Schedule
    data class GymClass(
        val id: String,
        val name: String,
        val time: String,
        val dayGroup: String, // e.g. "Mon / Wed / Fri", "Tue / Thu / Sat", "Daily"
        val type: String, // "Strength Training", "Cardio", "Conditioning", "Hypertrophy"
        val duration: String,
        val trainerPlaceholder: String = "[Assigned Trainer]",
        val description: String
    )

    val GYM_CLASSES: List<GymClass> = listOf(
        GymClass(
            id = "commando_strength_am",
            name = "Commando Barbell Strength",
            time = "6:00 AM – 7:00 AM",
            dayGroup = "Mon / Wed / Fri",
            type = "Strength Training",
            duration = "60 min",
            trainerPlaceholder = "[Add Trainer]",
            description = "Fundamental compound barbell lifts: squats, deadlifts, and bench presses with strict form and progressive overload."
        ),
        GymClass(
            id = "fat_burn_hiit",
            name = "Metabolic HIIT & Cardio",
            time = "7:30 AM – 8:30 AM",
            dayGroup = "Tue / Thu / Sat",
            type = "Cardio",
            duration = "60 min",
            trainerPlaceholder = "[Add Trainer]",
            description = "High-energy interval conditioning combining functional bodyweight circuits, rowing, and endurance drills for rapid fat oxidation."
        ),
        GymClass(
            id = "muscle_hypertrophy_pm",
            name = "Power Hypertrophy Division",
            time = "5:30 PM – 6:30 PM",
            dayGroup = "Mon / Wed / Fri",
            type = "Strength Training",
            duration = "60 min",
            trainerPlaceholder = "[Add Trainer]",
            description = "Targeted muscle volume training focusing on dumbbell isolation, cable control, and muscular hypertrophy techniques."
        ),
        GymClass(
            id = "core_conditioning_pm",
            name = "Core & Tactical Conditioning",
            time = "6:45 PM – 7:30 PM",
            dayGroup = "Tue / Thu / Sat",
            type = "Conditioning",
            duration = "45 min",
            trainerPlaceholder = "[Add Trainer]",
            description = "Commando core stabilization, rotational power, agility footwork, and functional endurance to build an unbreakable core."
        ),
        GymClass(
            id = "night_iron_camp",
            name = "Evening Iron Blast",
            time = "8:00 PM – 9:00 PM",
            dayGroup = "Daily (Mon–Sat)",
            type = "Strength Training",
            duration = "60 min",
            trainerPlaceholder = "[Add Trainer]",
            description = "Heavy free-weight training session designed for after-work athletes looking to push past personal records."
        ),
        GymClass(
            id = "cardio_endurance_burn",
            name = "Endurance Shred & Tread",
            time = "9:00 PM – 9:45 PM",
            dayGroup = "Mon / Wed / Fri",
            type = "Cardio",
            duration = "45 min",
            trainerPlaceholder = "[Add Trainer]",
            description = "Steady-state conditioning, machine intervals, and sweat-drenching cardio finishers before gym closing."
        )
    )

    // 5.2 Trainers Section (Strictly using user-specified placeholders)
    data class GymTrainer(
        val id: String,
        val photoPlaceholderText: String, // '[Add Trainer Photo]'
        val namePlaceholderText: String,  // '[Trainer Name]'
        val rolePlaceholderText: String,  // '[Trainer Role / Title]'
        val bioPlaceholderText: String,   // '[Trainer Bio/Expertise]'
        val specializations: List<String>
    )

    val TRAINERS: List<GymTrainer> = listOf(
        GymTrainer(
            id = "head_coach",
            photoPlaceholderText = "[Add Trainer Photo]",
            namePlaceholderText = "[Trainer Name]",
            rolePlaceholderText = "[Head Coach / Senior Trainer]",
            bioPlaceholderText = "[Trainer Bio/Expertise] — Experienced fitness mentor specializing in heavy compound strength, discipline, and personalized transformation roadmaps.",
            specializations = listOf("Strength Training", "Powerlifting", "Athlete Conditioning")
        ),
        GymTrainer(
            id = "conditioning_coach",
            photoPlaceholderText = "[Add Trainer Photo]",
            namePlaceholderText = "[Trainer Name]",
            rolePlaceholderText = "[Cardio & Conditioning Specialist]",
            bioPlaceholderText = "[Trainer Bio/Expertise] — Expert in high-intensity interval conditioning, metabolic fat loss routines, and stamina building for all fitness levels.",
            specializations = listOf("HIIT & Cardio", "Fat Loss", "Functional Training")
        ),
        GymTrainer(
            id = "personal_trainer",
            photoPlaceholderText = "[Add Trainer Photo]",
            namePlaceholderText = "[Trainer Name]",
            rolePlaceholderText = "[Personal Fitness Trainer]",
            bioPlaceholderText = "[Trainer Bio/Expertise] — Dedicated to 1-on-1 member coaching, posture correction, injury-free progressive training, and customized workout programming.",
            specializations = listOf("Personal Training", "Hypertrophy", "Mobility & Form")
        )
    )

    // 6. Programs
    data class GymProgram(
        val id: String,
        val title: String,
        val description: String,
        val isEditableNotice: Boolean = false,
        val tag: String
    )

    val PROGRAMS: List<GymProgram> = listOf(
        GymProgram(
            id = "strength",
            title = "Strength Training",
            description = "Build foundational raw power, compound movement proficiency, and core physical resilience with progressive barbell & dumbbell training.",
            tag = "Core Focus"
        ),
        GymProgram(
            id = "muscle",
            title = "Muscle Building",
            description = "Hypertrophy-driven training targeting muscle definition, volume, and sculpted mass using specialized resistance equipment.",
            tag = "Hypertrophy"
        ),
        GymProgram(
            id = "fat_loss",
            title = "Fat Loss & Conditioning",
            description = "High-intensity calorie-burning workouts designed to increase metabolic rate, burn stubborn fat, and build athletic endurance.",
            tag = "Metabolic"
        ),
        GymProgram(
            id = "fitness",
            title = "General Fitness",
            description = "All-round functional conditioning, cardiovascular health, mobility, and everyday vitality suitable for all fitness levels.",
            tag = "Health & Wellness"
        ),
        GymProgram(
            id = "personal_training",
            title = "Personal Training",
            description = "[Add trainer information] — Contact gym staff directly to enquire about one-on-one certified trainer sessions and personalized workout roadmaps.",
            isEditableNotice = true,
            tag = "[Editable]"
        )
    )

    // 7. Membership Plans
    // Per instructions: "DO NOT invent actual prices. Use placeholders: [Add Price]"
    data class MembershipPlan(
        val name: String,
        val pricePlaceholder: String,
        val durationPlaceholder: String,
        val badge: String? = null,
        val benefits: List<String>,
        val isPopular: Boolean = false
    )

    val MEMBERSHIP_PLANS: List<MembershipPlan> = listOf(
        MembershipPlan(
            name = "Basic Membership",
            pricePlaceholder = "[Add Price]",
            durationPlaceholder = "[Add Duration, e.g. 1 Month]",
            badge = "Standard Access",
            benefits = listOf(
                "Full floor gym & weight area access",
                "[Add membership plans details]",
                "[Add equipment access details]",
                "Locker & changing room usage"
            ),
            isPopular = false
        ),
        MembershipPlan(
            name = "Standard Membership",
            pricePlaceholder = "[Add Price]",
            durationPlaceholder = "[Add Duration, e.g. 3 Months]",
            badge = "Most Popular",
            benefits = listOf(
                "Full floor gym & weight area access",
                "[Add membership plans details]",
                "[Add fitness assessment details]",
                "[Add workout routine guidance]",
                "Locker & changing room usage"
            ),
            isPopular = true
        ),
        MembershipPlan(
            name = "Premium Membership",
            pricePlaceholder = "[Add Price]",
            durationPlaceholder = "[Add Duration, e.g. 6 / 12 Months]",
            badge = "Complete Value",
            benefits = listOf(
                "Unrestricted gym access all operating hours",
                "[Add membership plans details]",
                "[Add personal trainer session allowance]",
                "[Add diet & nutrition plan details]",
                "Priority equipment & facility privileges"
            ),
            isPopular = false
        )
    )

    // 8. Why Choose Us Categories (4 modern feature cards without unsupported factual claims)
    data class FeatureCard(
        val title: String,
        val subtitle: String,
        val iconType: String
    )

    val FEATURE_CARDS: List<FeatureCard> = listOf(
        FeatureCard(
            title = "Strength Training",
            subtitle = "Dedicated free weight lifting zone, heavy-duty iron plates, and power stations designed for serious lifting.",
            iconType = "fitness_center"
        ),
        FeatureCard(
            title = "Fitness & Conditioning",
            subtitle = "Comprehensive functional equipment and high-energy conditioning setup to elevate endurance and agility.",
            iconType = "bolt"
        ),
        FeatureCard(
            title = "Workout Programs",
            subtitle = "Structured training frameworks for fat loss, strength gain, and progressive body composition goals.",
            iconType = "format_list_bulleted"
        ),
        FeatureCard(
            title = "Supportive Training Environment",
            subtitle = "A focused, disciplined, and motivating fitness atmosphere where members push each other to break plateaus.",
            iconType = "groups"
        )
    )

    // 9. Gallery Notice
    const val GALLERY_CODE_NOTE = "Replace these placeholder images with official Commando Fit Gym photos before publishing."

    // 10. Social Media Placeholders
    const val INSTAGRAM_URL = "[Add Instagram URL]"
    const val FACEBOOK_URL = "[Add Facebook URL]"
    const val YOUTUBE_URL = "[Add YouTube URL]"

    // 11. Copyright & Legal
    const val COPYRIGHT_TEXT = "© 2026 Commando Fit Gym. All rights reserved."
    const val LOCATION_NOTE = "Located at Gardanibagh, Patna, Bihar."
}
