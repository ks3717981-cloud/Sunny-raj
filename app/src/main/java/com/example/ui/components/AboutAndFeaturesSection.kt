package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.config.CommandoFitConfig
import com.example.ui.theme.*
import com.example.util.GymIntents

@Composable
fun AboutAndFeaturesSection(
    onContactClick: () -> Unit,
    onDirectionsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 28.dp)
    ) {
        // --- 3. ABOUT SECTION ---
        SectionHeader(
            badge = "WHO WE ARE",
            title = "ABOUT COMMANDO FIT GYM",
            subtitle = "A dedicated fitness and strength center in Gardanibagh, Patna."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Factual About Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = GymCardBg),
            border = androidx.compose.foundation.BorderStroke(1.dp, GymCardBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Welcome to Commando Fit Gym, located at Labour Court, Mithapur Farm Area in Gardanibagh, Patna. We provide a rigorous, focused fitness environment built for individuals committed to strength training, conditioning, and physical self-improvement.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = GymTextPrimary,
                        lineHeight = 24.sp
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Whether your priority is building strength, improving muscular stamina, shedding fat, or following a disciplined workout routine, our facility offers the space and atmosphere to train with dedication. We invite you to visit our center or connect with our team directly.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = GymTextSecondary,
                        lineHeight = 20.sp
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Factual Quick Highlights
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FactChip(
                        icon = Icons.Default.Place,
                        label = "Gardanibagh",
                        detail = "Patna, Bihar",
                        modifier = Modifier.weight(1f)
                    )
                    FactChip(
                        icon = Icons.Default.Star,
                        label = "4.8 / 5.0",
                        detail = "43 Reviews",
                        tint = GymGold,
                        modifier = Modifier.weight(1f)
                    )
                    FactChip(
                        icon = Icons.Default.Schedule,
                        label = "5:00 AM",
                        detail = "Morning Open",
                        tint = GymRedBright,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Actions: Visit / Get Directions & Contact
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onDirectionsClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GymSurfaceLight,
                            contentColor = GymTextPrimary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp)
                            .testTag("about_visit_button")
                    ) {
                        Icon(Icons.Default.Navigation, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Visit Gym", style = MaterialTheme.typography.labelLarge)
                    }

                    Button(
                        onClick = onContactClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GymRed,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp)
                            .testTag("about_contact_button")
                    ) {
                        Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Contact Gym", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // --- 4. WHY CHOOSE US ---
        SectionHeader(
            badge = "WHY COMMANDO FIT",
            title = "WHY CHOOSE US",
            subtitle = "Built on disciplined training, progressive equipment, and motivating energy."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4 Modern Feature Cards (Grid 2x2 on mobile)
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            for (i in CommandoFitConfig.FEATURE_CARDS.indices step 2) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    FeatureCardItem(
                        card = CommandoFitConfig.FEATURE_CARDS[i],
                        modifier = Modifier.weight(1f)
                    )
                    if (i + 1 < CommandoFitConfig.FEATURE_CARDS.size) {
                        FeatureCardItem(
                            card = CommandoFitConfig.FEATURE_CARDS[i + 1],
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun SectionHeader(
    badge: String,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = GymRed.copy(alpha = 0.15f),
            border = androidx.compose.foundation.BorderStroke(1.dp, GymRed.copy(alpha = 0.4f)),
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Text(
                text = badge.uppercase(),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = GymRedBright,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                ),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Black,
                color = GymTextPrimary,
                letterSpacing = 0.sp
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = GymTextSecondary
            )
        )
    }
}

@Composable
fun FactChip(
    icon: ImageVector,
    label: String,
    detail: String,
    tint: Color = GymTextPrimary,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = GymSurfaceLight,
        border = androidx.compose.foundation.BorderStroke(1.dp, GymDivider),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = GymTextPrimary
                )
            )
            Text(
                text = detail,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = GymTextSecondary
                )
            )
        }
    }
}

@Composable
fun FeatureCardItem(
    card: CommandoFitConfig.FeatureCard,
    modifier: Modifier = Modifier
) {
    val icon = when (card.iconType) {
        "fitness_center" -> Icons.Default.FitnessCenter
        "bolt" -> Icons.Default.FlashOn
        "format_list_bulleted" -> Icons.Default.FormatListBulleted
        else -> Icons.Default.Groups
    }

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = GymCardBg),
        border = androidx.compose.foundation.BorderStroke(1.dp, GymCardBorder),
        modifier = modifier
            .fillMaxHeight()
            .testTag("feature_card_${card.title.lowercase().replace(" ", "_")}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(GymRed.copy(alpha = 0.15f))
                    .border(1.dp, GymRed.copy(alpha = 0.4f), RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = GymRedBright,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = card.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = GymTextPrimary
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = card.subtitle,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = GymTextSecondary,
                    lineHeight = 18.sp
                )
            )
        }
    }
}
