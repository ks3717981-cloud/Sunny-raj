package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.config.CommandoFitConfig
import com.example.ui.components.*
import com.example.ui.theme.GymBlack
import com.example.ui.theme.GymRed
import com.example.ui.theme.MyApplicationTheme
import com.example.util.GymIntents
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                CommandoFitGymApp()
            }
        }
    }
}

@Composable
fun CommandoFitGymApp() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    var currentSection by remember { mutableStateOf(NavSection.HOME) }
    var enquiryDialogSubject by remember { mutableStateOf<String?>(null) }

    // Scroll targets calculation (approximate offsets for smooth navigation)
    fun scrollToSection(section: NavSection) {
        currentSection = section
        coroutineScope.launch {
            val targetOffset = when (section) {
                NavSection.HOME -> 0
                NavSection.ABOUT -> 700
                NavSection.WHY_US -> 1500
                NavSection.PROGRAMS -> 2300
                NavSection.SCHEDULE -> 3400
                NavSection.TRAINERS -> 4500
                NavSection.MEMBERSHIP -> 5500
                NavSection.GALLERY -> 6800
                NavSection.REVIEWS -> 7800
                NavSection.LOCATION -> 8700
                NavSection.HOURS -> 9500
                NavSection.CONTACT -> 10300
            }
            scrollState.animateScrollTo(targetOffset)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = GymBlack,
        topBar = {
            GymHeaderNavBar(
                selectedSection = currentSection,
                onSelectSection = { section -> scrollToSection(section) },
                onOpenJoinNow = { enquiryDialogSubject = "General Membership" }
            )
        },
        floatingActionButton = {
            // Floating Quick Call Button with Scroll To Top helper
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Scroll to top button when user scrolls down
                AnimatedVisibility(
                    visible = scrollState.value > 800,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    SmallFloatingActionButton(
                        onClick = {
                            coroutineScope.launch { scrollState.animateScrollTo(0) }
                        },
                        containerColor = GymBlack.copy(alpha = 0.85f),
                        contentColor = Color.White,
                        modifier = Modifier.testTag("fab_scroll_top")
                    ) {
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Scroll to Top")
                    }
                }

                // Quick Call Gym FAB
                FloatingActionButton(
                    onClick = { GymIntents.dialPhone(context) },
                    containerColor = GymRed,
                    contentColor = Color.White,
                    shape = CircleShape,
                    modifier = Modifier.testTag("fab_call_gym")
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Call ${CommandoFitConfig.BUSINESS_NAME}"
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(GymBlack),
            contentAlignment = Alignment.TopCenter
        ) {
            // Main content column centered with width limitation for wide screens/tablets
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 780.dp)
                    .verticalScroll(scrollState)
            ) {
                // 1 & 2. Hero Section
                HeroSection(
                    onJoinNowClick = { enquiryDialogSubject = "Join Now" },
                    modifier = Modifier.testTag("section_hero")
                )

                // 3 & 4. About & Why Choose Us Sections
                AboutAndFeaturesSection(
                    onContactClick = { scrollToSection(NavSection.CONTACT) },
                    onDirectionsClick = { GymIntents.openDirections(context) },
                    modifier = Modifier.testTag("section_about_why_us")
                )

                // 5 & 6. Programs & Membership Sections
                ProgramsAndMembershipSection(
                    onEnquireProgram = { programTitle ->
                        enquiryDialogSubject = programTitle
                    },
                    onEnquirePlan = { planName ->
                        enquiryDialogSubject = planName
                    },
                    modifier = Modifier.testTag("section_programs_membership")
                )

                // Interactive Class Schedule Section
                ClassScheduleSection(
                    onBookClass = { gymClass ->
                        enquiryDialogSubject = "Class Booking: ${gymClass.name} (${gymClass.time})"
                    },
                    modifier = Modifier.testTag("section_class_schedule")
                )

                // Meet Our Trainers Section
                TrainersSection(
                    onEnquireTrainer = { trainer ->
                        enquiryDialogSubject = "Trainer Coaching: ${trainer.namePlaceholderText} - ${trainer.rolePlaceholderText}"
                    },
                    modifier = Modifier.testTag("section_trainers")
                )

                // 7 & 8. Gallery & Reviews Sections
                GalleryAndReviewsSection(
                    modifier = Modifier.testTag("section_gallery_reviews")
                )

                // 9 & 11. Location & Opening Hours Sections
                LocationAndHoursSection(
                    modifier = Modifier.testTag("section_location_hours")
                )

                // 10, 12, 13. Contact, Final CTA & Footer Sections
                ContactAndFooterSection(
                    onNavigateSection = { section -> scrollToSection(section) },
                    modifier = Modifier.testTag("section_contact_footer")
                )

                // Navigation Bar bottom spacer
                Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
            }
        }

        // Enquiry / Join Now Modal Dialog
        enquiryDialogSubject?.let { subject ->
            EnquiryDialog(
                initialSubject = subject,
                onDismiss = { enquiryDialogSubject = null }
            )
        }
    }
}
