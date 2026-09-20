package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.config.CommandoFitConfig
import com.example.ui.theme.*
import com.example.util.GymIntents

@Composable
fun EnquiryDialog(
    initialSubject: String,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var selectedSubject by remember { mutableStateOf(initialSubject.ifEmpty { "General Membership" }) }

    val options = listOf(
        "General Membership",
        "Basic Membership",
        "Standard Membership",
        "Premium Membership",
        "Strength Training",
        "Muscle Building",
        "Personal Training"
    )

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = GymDarkCharcoal,
            border = androidx.compose.foundation.BorderStroke(1.dp, GymCardBorder),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .testTag("enquiry_dialog")
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "JOIN COMMANDO FIT",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Black,
                                color = GymTextPrimary
                            )
                        )
                        Text(
                            text = "Connect with gym staff in Patna",
                            style = MaterialTheme.typography.bodySmall.copy(color = GymTextSecondary)
                        )
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = GymTextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "SELECT ENQUIRY TYPE:",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = GymRedBright,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Options Chips
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    options.take(5).forEach { opt ->
                        val isSelected = selectedSubject == opt
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (isSelected) GymRed.copy(alpha = 0.2f) else GymSurfaceLight,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) GymRed else GymDivider
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedSubject = opt }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = isSelected,
                                    onClick = { selectedSubject = opt },
                                    colors = RadioButtonDefaults.colors(selectedColor = GymRedBright)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = opt,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = if (isSelected) GymTextPrimary else GymTextSecondary,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Quick Call
                Button(
                    onClick = {
                        GymIntents.dialPhone(context)
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("dialog_call_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GymRed,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Call Gym: ${CommandoFitConfig.PHONE_DISPLAY}", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Quick WhatsApp
                Button(
                    onClick = {
                        val msg = "Hello Commando Fit Gym! I would like to enquire about $selectedSubject at your Gardanibagh center in Patna."
                        GymIntents.openWhatsApp(context, message = msg)
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("dialog_whatsapp_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF25D366),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Enquire via WhatsApp", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
                }
            }
        }
    }
}
