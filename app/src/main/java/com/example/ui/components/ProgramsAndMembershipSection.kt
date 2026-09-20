package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.config.CommandoFitConfig
import com.example.ui.theme.*

@Composable
fun ProgramsAndMembershipSection(
    onEnquireProgram: (String) -> Unit,
    onEnquirePlan: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        // --- 5. PROGRAMS SECTION ---
        SectionHeader(
            badge = "SPECIALIZED TRAINING",
            title = "TRAINING PROGRAMS",
            subtitle = "Targeted disciplines tailored to your personal physical objectives."
        )

        Spacer(modifier = Modifier.height(18.dp))

        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            CommandoFitConfig.PROGRAMS.forEach { program ->
                ProgramCard(
                    program = program,
                    onEnquire = { onEnquireProgram(program.title) }
                )
            }
        }

        Spacer(modifier = Modifier.height(44.dp))

        // --- 6. MEMBERSHIP SECTION ---
        SectionHeader(
            badge = "MEMBERSHIP PLANS",
            title = "JOIN COMMANDO FIT",
            subtitle = "Flexible gym access plans. Connect directly for official membership fees."
        )

        Spacer(modifier = Modifier.height(18.dp))

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            CommandoFitConfig.MEMBERSHIP_PLANS.forEach { plan ->
                MembershipCard(
                    plan = plan,
                    onEnquire = { onEnquirePlan(plan.name) }
                )
            }
        }
    }
}

@Composable
fun ProgramCard(
    program: CommandoFitConfig.GymProgram,
    onEnquire: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = GymCardBg),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (program.isEditableNotice) GymRed.copy(alpha = 0.5f) else GymCardBorder
        ),
        modifier = modifier
            .fillMaxWidth()
            .testTag("program_card_${program.id}")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = program.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = GymTextPrimary
                    )
                )

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = if (program.isEditableNotice) GymRed.copy(alpha = 0.2f) else GymSurfaceLight,
                    border = androidx.compose.foundation.BorderStroke(
                        0.8.dp,
                        if (program.isEditableNotice) GymRed else GymDivider
                    )
                ) {
                    Text(
                        text = program.tag,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (program.isEditableNotice) GymRedBright else GymTextSecondary,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = program.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = GymTextSecondary,
                    lineHeight = 20.sp
                )
            )

            if (program.isEditableNotice) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(GymSurfaceLight.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = GymGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Placeholder: Official trainer rosters will be updated as confirmed.",
                        style = MaterialTheme.typography.labelSmall.copy(color = GymGold)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            OutlinedButton(
                onClick = onEnquire,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, GymDivider),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = GymSurfaceLight,
                    contentColor = GymTextPrimary
                )
            ) {
                Text(
                    text = "Enquire About ${program.title}",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }
        }
    }
}

@Composable
fun MembershipCard(
    plan: CommandoFitConfig.MembershipPlan,
    onEnquire: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isPopular = plan.isPopular

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isPopular) GymSurfaceLight else GymCardBg
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = if (isPopular) 1.5.dp else 1.dp,
            color = if (isPopular) GymRed else GymCardBorder
        ),
        modifier = modifier
            .fillMaxWidth()
            .testTag("membership_card_${plan.name.lowercase().replace(" ", "_")}")
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Plan Header with Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = plan.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Black,
                        color = GymTextPrimary
                    )
                )

                if (plan.badge != null) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (isPopular) GymRed else GymDivider
                    ) {
                        Text(
                            text = plan.badge,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Price & Duration Placeholder Box
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = GymBlack.copy(alpha = 0.5f),
                border = androidx.compose.foundation.BorderStroke(1.dp, GymDivider),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = plan.pricePlaceholder,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Black,
                                color = GymRedBright
                            )
                        )
                        Text(
                            text = "Official rate available upon enquiry",
                            style = MaterialTheme.typography.labelSmall.copy(color = GymTextMuted)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = GymSurfaceLight
                    ) {
                        Text(
                            text = plan.durationPlaceholder,
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = GymTextSecondary,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Plan Benefits List
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                plan.benefits.forEach { benefit ->
                    Row(verticalAlignment = Alignment.Top) {
                        Box(
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .size(16.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(GymRed.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = GymRedBright,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = benefit,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = GymTextSecondary
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // "Enquire Now" CTA Button
            Button(
                onClick = onEnquire,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp)
                    .testTag("enquire_plan_${plan.name.lowercase().replace(" ", "_")}"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPopular) GymRed else GymSurfaceLight,
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
                    text = "Enquire Now",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                )
            }
        }
    }
}
