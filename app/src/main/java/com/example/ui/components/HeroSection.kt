package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Place
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

@Composable
fun HeroSection(
    onJoinNowClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(520.dp)
    ) {
        // Background Gym Image
        Image(
            painter = painterResource(id = R.drawable.img_hero_gym),
            contentDescription = "Commando Fit Gym Interior",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Dark cinematic overlay gradient (ensuring perfect readability)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            GymBlack.copy(alpha = 0.75f),
                            GymBlack.copy(alpha = 0.85f),
                            GymBlack
                        )
                    )
                )
        )

        // Subtle Red Accent Ambient Glow in top right
        Box(
            modifier = Modifier
                .size(240.dp)
                .align(Alignment.TopEnd)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            GymRed.copy(alpha = 0.25f),
                            Color.Transparent
                        )
                    )
                )
        )

        // Hero Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            // Live Status & Location Badges
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                // Status pill
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = GymCardBg.copy(alpha = 0.9f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, GymDivider)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(GymRedBright)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "${CommandoFitConfig.CURRENT_STATUS_TITLE} • ${CommandoFitConfig.CURRENT_STATUS_SUBTITLE}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = GymTextPrimary,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }

                // Rating Pill
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = GymCardBg.copy(alpha = 0.9f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, GymDivider)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = GymGold,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${CommandoFitConfig.RATING_STARS} (43 Reviews)",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = GymGold,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

            // Location Tag
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null,
                    tint = GymRedBright,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Gardanibagh, Patna, Bihar",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = GymTextSecondary,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            // Headline
            Text(
                text = CommandoFitConfig.TAGLINE,
                style = MaterialTheme.typography.displayMedium.copy(
                    color = GymTextPrimary,
                    fontWeight = FontWeight.Black,
                    lineHeight = 32.sp,
                    letterSpacing = (-0.5).sp
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Subheading
            Text(
                text = CommandoFitConfig.SUB_TAGLINE,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = GymTextSecondary,
                    lineHeight = 22.sp
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // CTA Buttons Row: "Join Now" and "Call Now"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // "Join Now"
                Button(
                    onClick = onJoinNowClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .testTag("hero_join_now_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GymRed,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FlashOn,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Join Now",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp,
                            letterSpacing = 0.5.sp
                        )
                    )
                }

                // "Call Now"
                OutlinedButton(
                    onClick = { GymIntents.dialPhone(context) },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .testTag("hero_call_now_button"),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = GymCardBg.copy(alpha = 0.9f),
                        contentColor = Color.White
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, GymRed.copy(alpha = 0.7f)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        tint = GymRedBright,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Call Now",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            letterSpacing = 0.5.sp
                        )
                    )
                }
            }
        }
    }
}
