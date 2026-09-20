package com.example.ui.components

import androidx.compose.foundation.Image
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
fun ContactAndFooterSection(
    onNavigateSection: (NavSection) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxWidth()) {
        // --- 10. CONTACT SECTION ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            SectionHeader(
                badge = "GET IN TOUCH",
                title = "CONTACT COMMANDO FIT",
                subtitle = "Reach out directly for membership enquiries, training, or visits."
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GymCardBg),
                border = androidx.compose.foundation.BorderStroke(1.dp, GymCardBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("contact_card")
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    // Phone display highlight
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(GymSurfaceLight, RoundedCornerShape(12.dp))
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(GymRed.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = null,
                                tint = GymRedBright,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Text(
                                text = "Official Gym Phone",
                                style = MaterialTheme.typography.labelSmall.copy(color = GymTextSecondary)
                            )
                            Text(
                                text = CommandoFitConfig.PHONE_DISPLAY,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Black,
                                    color = GymTextPrimary,
                                    letterSpacing = 0.5.sp
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Buttons: Call Now, WhatsApp, Get Directions
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        // 1. Call Now
                        Button(
                            onClick = { GymIntents.dialPhone(context) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("contact_call_now_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GymRed,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Call Now: ${CommandoFitConfig.PHONE_DISPLAY}",
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                            )
                        }

                        // 2. WhatsApp Button
                        Button(
                            onClick = { GymIntents.openWhatsApp(context) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("contact_whatsapp_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF25D366),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Message on WhatsApp",
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                            )
                        }

                        // 3. Get Directions
                        OutlinedButton(
                            onClick = { GymIntents.openDirections(context) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("contact_directions_button"),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GymDivider),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = GymSurfaceLight,
                                contentColor = GymTextPrimary
                            )
                        ) {
                            Icon(Icons.Default.Navigation, contentDescription = null, modifier = Modifier.size(18.dp), tint = GymRedBright)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Get Directions (Google Maps)",
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // --- 12. FINAL CTA ---
        Card(
            shape = RoundedCornerShape(0.dp),
            colors = CardDefaults.cardColors(containerColor = GymDarkCharcoal),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("final_cta_banner")
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                // Background subtle red glow
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .background(GymRed.copy(alpha = 0.08f))
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = GymRed
                    ) {
                        Text(
                            text = "START TODAY",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "READY TO TRAIN LIKE A COMMANDO?",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Black,
                            color = GymTextPrimary,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Start your fitness journey with Commando Fit Gym.",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = GymTextSecondary,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = { GymIntents.dialPhone(context) },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .testTag("final_cta_call_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GymRed,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Call Now", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
                        }

                        Button(
                            onClick = { GymIntents.openDirections(context) },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .testTag("final_cta_directions_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GymSurfaceLight,
                                contentColor = GymTextPrimary
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.Navigation, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Get Directions", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
                        }
                    }
                }
            }
        }

        // --- 13. FOOTER ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(GymBlack)
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .testTag("footer_section")
        ) {
            // Brand & Location
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(GymCardBg),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_app_icon),
                        contentDescription = "Logo",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = CommandoFitConfig.BUSINESS_NAME,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Black,
                            color = GymTextPrimary
                        )
                    )
                    Text(
                        text = "Patna, Bihar",
                        style = MaterialTheme.typography.labelSmall.copy(color = GymRedBright)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Address: ${CommandoFitConfig.FULL_ADDRESS}",
                style = MaterialTheme.typography.bodySmall.copy(color = GymTextSecondary)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Phone: ${CommandoFitConfig.PHONE_DISPLAY}",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = GymTextPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            HorizontalDivider(color = GymDivider, thickness = 1.dp)

            Spacer(modifier = Modifier.height(16.dp))

            // Quick Navigation Links
            Text(
                text = "QUICK NAVIGATION",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = GymTextMuted,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Home",
                        style = MaterialTheme.typography.bodySmall.copy(color = GymTextSecondary),
                        modifier = Modifier.clickable { onNavigateSection(NavSection.HOME) }
                    )
                    Text(
                        text = "About Us",
                        style = MaterialTheme.typography.bodySmall.copy(color = GymTextSecondary),
                        modifier = Modifier.clickable { onNavigateSection(NavSection.ABOUT) }
                    )
                    Text(
                        text = "Programs",
                        style = MaterialTheme.typography.bodySmall.copy(color = GymTextSecondary),
                        modifier = Modifier.clickable { onNavigateSection(NavSection.PROGRAMS) }
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Membership Plans",
                        style = MaterialTheme.typography.bodySmall.copy(color = GymTextSecondary),
                        modifier = Modifier.clickable { onNavigateSection(NavSection.MEMBERSHIP) }
                    )
                    Text(
                        text = "Gym Gallery",
                        style = MaterialTheme.typography.bodySmall.copy(color = GymTextSecondary),
                        modifier = Modifier.clickable { onNavigateSection(NavSection.GALLERY) }
                    )
                    Text(
                        text = "Reviews (4.8 ★)",
                        style = MaterialTheme.typography.bodySmall.copy(color = GymTextSecondary),
                        modifier = Modifier.clickable { onNavigateSection(NavSection.REVIEWS) }
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Location",
                        style = MaterialTheme.typography.bodySmall.copy(color = GymTextSecondary),
                        modifier = Modifier.clickable { onNavigateSection(NavSection.LOCATION) }
                    )
                    Text(
                        text = "Opening Hours",
                        style = MaterialTheme.typography.bodySmall.copy(color = GymTextSecondary),
                        modifier = Modifier.clickable { onNavigateSection(NavSection.HOURS) }
                    )
                    Text(
                        text = "Contact",
                        style = MaterialTheme.typography.bodySmall.copy(color = GymTextSecondary),
                        modifier = Modifier.clickable { onNavigateSection(NavSection.CONTACT) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Social Media Placeholders
            Text(
                text = "SOCIAL PROFILES",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = GymTextMuted,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = GymSurfaceLight,
                    modifier = Modifier.clickable { GymIntents.openWebUrl(context, CommandoFitConfig.INSTAGRAM_URL) }
                ) {
                    Text(
                        text = "[Add Instagram URL]",
                        style = MaterialTheme.typography.labelSmall.copy(color = GymTextSecondary),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = GymSurfaceLight,
                    modifier = Modifier.clickable { GymIntents.openWebUrl(context, CommandoFitConfig.FACEBOOK_URL) }
                ) {
                    Text(
                        text = "[Add Facebook URL]",
                        style = MaterialTheme.typography.labelSmall.copy(color = GymTextSecondary),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Copyright notice
            Text(
                text = CommandoFitConfig.COPYRIGHT_TEXT,
                style = MaterialTheme.typography.labelSmall.copy(color = GymTextMuted)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Production-ready website & mobile app proposal for Commando Fit Gym, Gardanibagh, Patna.",
                style = MaterialTheme.typography.labelSmall.copy(color = GymTextMuted, fontSize = 11.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
