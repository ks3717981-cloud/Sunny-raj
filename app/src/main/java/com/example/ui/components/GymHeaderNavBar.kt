package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.config.CommandoFitConfig
import com.example.ui.theme.*
import com.example.util.GymIntents

enum class NavSection(val title: String) {
    HOME("Home"),
    ABOUT("About"),
    WHY_US("Why Us"),
    PROGRAMS("Programs"),
    SCHEDULE("Class Schedule"),
    TRAINERS("Trainers"),
    MEMBERSHIP("Membership"),
    GALLERY("Gallery"),
    REVIEWS("Reviews"),
    LOCATION("Location"),
    HOURS("Hours"),
    CONTACT("Contact")
}

@Composable
fun GymHeaderNavBar(
    selectedSection: NavSection,
    onSelectSection: (NavSection) -> Unit,
    onOpenJoinNow: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(GymBlack.copy(alpha = 0.95f))
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        // Top Branding Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Logo & Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onSelectSection(NavSection.HOME) }
                    .testTag("brand_logo_header")
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(GymCardBg)
                        .border(1.dp, GymRed.copy(alpha = 0.6f), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_app_icon),
                        contentDescription = "Commando Fit Gym Logo",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "COMMANDO",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp,
                                color = GymTextPrimary
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "FIT",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp,
                                color = GymRed
                            )
                        )
                    }
                    Text(
                        text = "PATNA • 4.8 ★",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = GymGold,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    )
                }
            }

            // Action Buttons (Call Now & Join Now)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Call quick button
                FilledTonalIconButton(
                    onClick = { GymIntents.dialPhone(context) },
                    modifier = Modifier
                        .size(38.dp)
                        .testTag("nav_call_button"),
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = GymSurfaceLight,
                        contentColor = GymRedBright
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Call Commando Fit Gym",
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Join Now CTA Button
                Button(
                    onClick = onOpenJoinNow,
                    modifier = Modifier
                        .height(38.dp)
                        .testTag("nav_join_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GymRed,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp)
                ) {
                    Text(
                        text = "Join Now",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.5.sp
                        )
                    )
                }
            }
        }

        // Horizontal Nav Scrolling Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            NavSection.values().forEach { section ->
                val isSelected = selectedSection == section
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = if (isSelected) GymRed else GymSurfaceLight,
                    modifier = Modifier
                        .clickable { onSelectSection(section) }
                        .testTag("nav_tab_${section.name.lowercase()}")
                ) {
                    Text(
                        text = section.title,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.White else GymTextSecondary
                        ),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }

        HorizontalDivider(color = GymDivider, thickness = 1.dp)
    }
}
