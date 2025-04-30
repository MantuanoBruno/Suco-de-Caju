package com.example.sucodecaju

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sucodecaju.ui.theme.SucoDeCajuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SucoDeCajuTheme {
                SucoDeCajuApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SucoDeCajuApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Caju Juice",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = colorResource(R.color.barra),
                    titleContentColor = Color.Black
                ),
            )
        },
        containerColor = MaterialTheme.colorScheme.tertiaryContainer
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentStep) {
                1 -> CajuTextAndImage(
                    textLabelResourceId = R.string.Caju_tree,
                    drawableResourceId = R.drawable.cajueiro,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
                )
                2 -> CajuTextAndImage(
                    textLabelResourceId = R.string.Caju,
                    drawableResourceId = R.drawable.caju,
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0) currentStep = 3
                    }
                )
                3 -> CajuTextAndImage(
                    textLabelResourceId = R.string.Caju_drink,
                    drawableResourceId = R.drawable.suco,
                    onImageClick = { currentStep = 4 }
                )
                4 -> CajuTextAndImage(
                    textLabelResourceId = R.string.Caju_empty_glass,
                    drawableResourceId = R.drawable.copo_vazio,
                    onImageClick = { currentStep = 1 }
                )
            }
        }
    }
}

@Composable
fun CajuTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = stringResource(textLabelResourceId),
                modifier = Modifier
                    .size(150.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textLabelResourceId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun CajuPreview() {
    SucoDeCajuTheme {
        SucoDeCajuApp()
    }
}
