package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun ClassScheduleSection(
    onBookClass: (CommandoFitConfig.GymClass) -> Unit,
    modifier: Modifier = Modifier
) {
    val filterTypes = listOf("All", "Strength Training", "Cardio", "Conditioning")
    var selectedFilter by remember { mutableStateOf("All") }

    val filteredClasses = remember(selectedFilter) {
        if (selectedFilter == "All") {
            CommandoFitConfig.GYM_CLASSES
        } else {
            CommandoFitConfig.GYM_CLASSES.filter { it.type.equals(selectedFilter, ignoreCase = true) }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .testTag("section_class_schedule")
    ) {
        SectionHeader(
            badge = "DAILY TIMETABLE",
            title = "INTERACTIVE CLASS SCHEDULE",
            subtitle = "Explore scheduled group training, barbell clinics, and high-intensity conditioning sessions."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Filter Chips Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filterTypes.forEach { type ->
                val isSelected = selectedFilter == type
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedFilter = type },
                    label = {
                        Text(
                            text = type,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = GymSurfaceLight,
                        labelColor = GymTextSecondary,
                        selectedContainerColor = GymRed,
                        selectedLabelColor = Color.White
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = if (isSelected) GymRed else GymDivider,
                        selectedBorderColor = GymRedBright,
                        enabled = true,
                        selected = isSelected
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.testTag("filter_chip_${type.lowercase().replace(" ", "_")}")
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Classes List
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            filteredClasses.forEach { gymClass ->
                ClassCardItem(
                    gymClass = gymClass,
                    onBookClick = { onBookClass(gymClass) }
                )
            }
        }
    }
}

@Composable
fun ClassCardItem(
    gymClass: CommandoFitConfig.GymClass,
    onBookClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GymCardBg),
        border = androidx.compose.foundation.BorderStroke(1.dp, GymCardBorder),
        modifier = modifier
            .fillMaxWidth()
            .testTag("class_card_${gymClass.id}")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            // Header Row: Type Badge + Days Group
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = GymRed.copy(alpha = 0.15f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, GymRed.copy(alpha = 0.4f))
                ) {
                    Text(
                        text = gymClass.type.uppercase(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = GymRedBright,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            letterSpacing = 0.5.sp
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = GymSurfaceLight,
                    border = androidx.compose.foundation.BorderStroke(0.8.dp, GymDivider)
                ) {
                    Text(
                        text = gymClass.dayGroup,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = GymGold,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Class Name
            Text(
                text = gymClass.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Black,
                    color = GymTextPrimary
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Time & Duration Chips Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = GymRedBright,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = gymClass.time,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = GymTextPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = null,
                        tint = GymTextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = gymClass.duration,
                        style = MaterialTheme.typography.bodySmall.copy(color = GymTextSecondary)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Description
            Text(
                text = gymClass.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = GymTextSecondary,
                    lineHeight = 20.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Trainer Placeholder Info Tag
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GymDarkCharcoal, RoundedCornerShape(6.dp))
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = GymTextMuted,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Instructor: ${gymClass.trainerPlaceholder}",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = GymTextSecondary,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // "Book Now" Button (clearly marked as placeholder for future integration per prompt)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onBookClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .testTag("book_now_button_${gymClass.id}"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GymRed,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FlashOn,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Book Now",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    )
                }

                // Future Integration Badge Tag
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = GymSurfaceLight,
                    border = androidx.compose.foundation.BorderStroke(1.dp, GymDivider),
                    modifier = Modifier.height(44.dp)
                ) {
                    Box(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Future Integration",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = GymGold,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 10.sp
                            )
                        )
                    }
                }
            }
        }
    }
}
