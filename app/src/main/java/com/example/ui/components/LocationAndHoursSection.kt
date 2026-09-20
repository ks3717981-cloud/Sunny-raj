package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.config.CommandoFitConfig
import com.example.ui.theme.*
import com.example.util.GymIntents

@Composable
fun LocationAndHoursSection(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        // --- 9. LOCATION SECTION ---
        SectionHeader(
            badge = "VISIT THE GYM",
            title = "FIND COMMANDO FIT GYM",
            subtitle = "Conveniently situated in Gardanibagh, Patna."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Address & Map Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = GymCardBg),
            border = androidx.compose.foundation.BorderStroke(1.dp, GymCardBorder),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("location_address_card")
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.Top) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(GymRed.copy(alpha = 0.15f))
                            .border(1.dp, GymRed.copy(alpha = 0.4f), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = GymRedBright,
                            modifier = Modifier.size(26.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = CommandoFitConfig.BUSINESS_NAME,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = GymTextPrimary
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = CommandoFitConfig.ADDRESS_LINE_1,
                            style = MaterialTheme.typography.bodyMedium.copy(color = GymTextSecondary)
                        )
                        Text(
                            text = CommandoFitConfig.ADDRESS_LINE_2,
                            style = MaterialTheme.typography.bodyMedium.copy(color = GymTextSecondary)
                        )
                        Text(
                            text = CommandoFitConfig.ADDRESS_CITY_STATE_PIN,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = GymTextPrimary,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Interactive Map Preview Card
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = GymSurfaceLight,
                    border = androidx.compose.foundation.BorderStroke(1.dp, GymDivider),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clickable { GymIntents.openDirections(context) }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Stylized Grid/Map Representation
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(GymRed),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DirectionsRun,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Commando Fit Gym • Gardanibagh",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = GymTextPrimary
                                )
                            )
                            Text(
                                text = "Tap to open in Google Maps",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = GymGold,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }

                        // Corner Badge
                        Surface(
                            shape = RoundedCornerShape(bottomStart = 8.dp),
                            color = GymCardBg,
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Map, contentDescription = null, tint = GymRedBright, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Google Maps Live", style = MaterialTheme.typography.labelSmall.copy(color = GymTextSecondary))
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // "Get Directions" Button
                Button(
                    onClick = { GymIntents.openDirections(context) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("location_directions_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GymRed,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Navigation,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Get Directions to Gym",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(44.dp))

        // --- 11. OPENING HOURS ---
        SectionHeader(
            badge = "FACILITY SCHEDULE",
            title = "OPENING HOURS",
            subtitle = "Official weekly gym hours: Monday to Saturday 5:00 AM – 10:00 PM."
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = GymCardBg),
            border = androidx.compose.foundation.BorderStroke(1.dp, GymCardBorder),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("opening_hours_card")
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                // Confirmed Timings Banner
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = GymSurfaceLight,
                    border = androidx.compose.foundation.BorderStroke(1.dp, GymDivider),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = GymRedBright,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = CommandoFitConfig.OPENING_HOURS_DISCLAIMER,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = GymTextPrimary,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 18.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Weekly Schedule Table (centralized from config)
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    CommandoFitConfig.WEEKLY_SCHEDULE.forEach { schedule ->
                        val isSunday = schedule.day.equals("Sunday", ignoreCase = true)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(GymDarkCharcoal, RoundedCornerShape(8.dp))
                                .padding(horizontal = 14.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = schedule.day,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = GymTextPrimary
                                )
                            )
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = if (isSunday) GymRed.copy(alpha = 0.2f) else GymSurfaceLight
                            ) {
                                Text(
                                    text = schedule.hours,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = if (isSunday) GymRedBright else GymGold,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Call to Confirm Button
                OutlinedButton(
                    onClick = { GymIntents.dialPhone(context) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .testTag("hours_call_confirm_button"),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, GymDivider),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = GymSurfaceLight,
                        contentColor = GymTextPrimary
                    )
                ) {
                    Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp), tint = GymRedBright)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Call Gym to Confirm Today's Timings", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                }
            }
        }
    }
}
