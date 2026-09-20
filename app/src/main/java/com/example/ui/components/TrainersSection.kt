package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.config.CommandoFitConfig
import com.example.ui.theme.*

@Composable
fun TrainersSection(
    onEnquireTrainer: (CommandoFitConfig.GymTrainer) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .testTag("section_trainers")
    ) {
        SectionHeader(
            badge = "COACHING STAFF",
            title = "MEET OUR TRAINERS",
            subtitle = "Dedicated fitness coaches committed to your strength, technique, and transformation goals."
        )

        Spacer(modifier = Modifier.height(18.dp))

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            CommandoFitConfig.TRAINERS.forEach { trainer ->
                TrainerCardItem(
                    trainer = trainer,
                    onEnquireClick = { onEnquireTrainer(trainer) }
                )
            }
        }
    }
}

@Composable
fun TrainerCardItem(
    trainer: CommandoFitConfig.GymTrainer,
    onEnquireClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GymCardBg),
        border = androidx.compose.foundation.BorderStroke(1.dp, GymCardBorder),
        modifier = modifier
            .fillMaxWidth()
            .testTag("trainer_card_${trainer.id}")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Trainer Photo Placeholder Box
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(GymDarkCharcoal)
                        .border(1.5.dp, GymRed.copy(alpha = 0.6f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_trainer_placeholder),
                        contentDescription = trainer.photoPlaceholderText,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Overlay label for explicit placeholder clarity
                    Surface(
                        color = Color.Black.copy(alpha = 0.65f),
                        modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                    ) {
                        Text(
                            text = trainer.photoPlaceholderText,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.White,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            maxLines = 1,
                            modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Name, Role and Placeholder Tag
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = trainer.namePlaceholderText,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Black,
                                color = GymTextPrimary
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = GymRedBright,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = trainer.rolePlaceholderText,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = GymGold,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Specialization chips
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        trainer.specializations.take(2).forEach { spec ->
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = GymSurfaceLight,
                                border = androidx.compose.foundation.BorderStroke(0.6.dp, GymDivider)
                            ) {
                                Text(
                                    text = spec,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = GymTextSecondary,
                                        fontSize = 10.sp
                                    ),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            HorizontalDivider(color = GymDivider, thickness = 0.8.dp)

            Spacer(modifier = Modifier.height(12.dp))

            // Bio / Experience editable text
            Text(
                text = trainer.bioPlaceholderText,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = GymTextSecondary,
                    lineHeight = 20.sp
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Enquire with Trainer Button
            OutlinedButton(
                onClick = onEnquireClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .testTag("trainer_enquire_button_${trainer.id}"),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, GymRed.copy(alpha = 0.6f)),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = GymSurfaceLight,
                    contentColor = GymRedBright
                )
            ) {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Enquire for Personal Training",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}
