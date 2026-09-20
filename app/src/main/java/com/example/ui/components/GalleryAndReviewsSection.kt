package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.window.Dialog
import com.example.R
import com.example.config.CommandoFitConfig
import com.example.ui.theme.*
import com.example.util.GymIntents

/**
 * Gallery Items List.
 * NOTE PER SPEC: Replace these placeholder images with official Commando Fit Gym photos before publishing.
 */
data class GalleryItem(
    val drawableRes: Int,
    val caption: String,
    val tag: String
)

@Composable
fun GalleryAndReviewsSection(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var previewItem by remember { mutableStateOf<GalleryItem?>(null) }

    val galleryItems = listOf(
        GalleryItem(
            drawableRes = R.drawable.img_hero_gym,
            caption = "Heavy Barbells & Power Lifting Station",
            tag = "Power Training"
        ),
        GalleryItem(
            drawableRes = R.drawable.img_gallery_strength,
            caption = "Free Weight Dumbbells & Resistance Zone",
            tag = "Strength"
        ),
        GalleryItem(
            drawableRes = R.drawable.img_gallery_workout,
            caption = "Functional Training & Conditioning Space",
            tag = "Conditioning"
        ),
        GalleryItem(
            drawableRes = R.drawable.img_gallery_cardio,
            caption = "High-End Cardio & Endurance Zone",
            tag = "Cardio"
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        // --- 7. GALLERY SECTION ---
        SectionHeader(
            badge = "FACILITY SHOWCASE",
            title = "GYM GALLERY",
            subtitle = "A glimpse inside the training floor and equipment setup."
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Code and UI Note Banner
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
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = GymGold,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Placeholder Imagery: " + CommandoFitConfig.GALLERY_CODE_NOTE,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = GymTextSecondary,
                        lineHeight = 16.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 2x2 Gallery Grid
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            for (i in galleryItems.indices step 2) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    GalleryImageCard(
                        item = galleryItems[i],
                        onClick = { previewItem = galleryItems[i] },
                        modifier = Modifier.weight(1f)
                    )
                    if (i + 1 < galleryItems.size) {
                        GalleryImageCard(
                            item = galleryItems[i + 1],
                            onClick = { previewItem = galleryItems[i + 1] },
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(44.dp))

        // --- 8. REVIEWS SECTION ---
        SectionHeader(
            badge = "REPUTATION & RATINGS",
            title = "MEMBER REVIEWS",
            subtitle = "Authentic feedback from real fitness enthusiasts in Patna."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Large Rating Highlight Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = GymCardBg),
            border = androidx.compose.foundation.BorderStroke(1.dp, GymCardBorder),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("reviews_highlight_card")
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Star score badge
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = CommandoFitConfig.RATING_STARS,
                        style = MaterialTheme.typography.displayLarge.copy(
                            color = GymGold,
                            fontWeight = FontWeight.Black,
                            fontSize = 44.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Row {
                            repeat(5) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = GymGold,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Text(
                            text = "${CommandoFitConfig.TOTAL_REVIEWS_COUNT} Reviews",
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = GymTextSecondary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Official Factual Statement (No fake individual reviews invented)
                Text(
                    text = CommandoFitConfig.RATING_SUMMARY_TEXT,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = GymTextPrimary
                    )
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Public rating on Google for Commando Fit Gym located at Gardanibagh, Patna. Member experiences consistently reflect a positive, motivating strength workout atmosphere.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = GymTextSecondary,
                        lineHeight = 20.sp
                    ),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                // "View Reviews" button (configurable URL)
                Button(
                    onClick = { GymIntents.openWebUrl(context, CommandoFitConfig.GOOGLE_REVIEWS_URL) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("reviews_view_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GymRed,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.OpenInNew,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "View Reviews on Google",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }

    // Fullscreen Image Lightbox Preview Dialog
    previewItem?.let { item ->
        Dialog(onDismissRequest = { previewItem = null }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = GymDarkCharcoal,
                border = androidx.compose.foundation.BorderStroke(1.dp, GymDivider),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.caption,
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = GymTextPrimary,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = { previewItem = null }) {
                            Icon(Icons.Default.Close, contentDescription = "Close", tint = GymTextSecondary)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Image(
                        painter = painterResource(id = item.drawableRes),
                        contentDescription = item.caption,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Photo Category: ${item.tag} • Commando Fit Gym Showcase",
                        style = MaterialTheme.typography.labelSmall.copy(color = GymTextSecondary)
                    )
                }
            }
        }
    }
}

@Composable
fun GalleryImageCard(
    item: GalleryItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = GymCardBg),
        border = androidx.compose.foundation.BorderStroke(1.dp, GymCardBorder),
        modifier = modifier
            .height(180.dp)
            .clickable(onClick = onClick)
            .testTag("gallery_item_${item.tag.lowercase()}")
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = item.drawableRes),
                contentDescription = item.caption,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Dark bottom gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                GymBlack.copy(alpha = 0.85f)
                            ),
                            startY = 60f
                        )
                    )
            )

            // Bottom Caption & Tag
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(10.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = GymRed.copy(alpha = 0.9f)
                ) {
                    Text(
                        text = item.tag,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        ),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.caption,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = GymTextPrimary,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 14.sp
                    ),
                    maxLines = 2
                )
            }
        }
    }
}
