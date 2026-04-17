package com.plcoding.bookpedia.book.presentation.book_list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import cmp_bookpedia.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.vector.ImageVector
import com.plcoding.bookpedia.core.presentation.DesertWhite
import cmp_bookpedia.composeapp.generated.resources.search_hint
import com.plcoding.bookpedia.core.presentation.DarkBlue

@Composable
fun BookSearchBar(
    searchQuery: String,
    searchQueryChange: (String) -> Unit,
    onImeSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = searchQueryChange,
        shape = RoundedCornerShape(90),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Blue,
            focusedBorderColor = DesertWhite,
            cursorColor = DarkBlue,
        ),
        placeholder = {
            Text(
                text = stringResource(Res.string.search_hint)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                onImeSearch()
            }
        ),
        trailingIcon = {
            AnimatedVisibility(
                visible = searchQuery.isNotBlank(),
            ) {
                IconButton(
                    onClick = {
                        onSearchQueryChange()
                    }
                ) {
                    Icon(
                        ImageVector = Icons.Default.Close,
                        contentDescription = stringResource(Res.string.close_hint)
                    )
                }
            }
        }
    )
}