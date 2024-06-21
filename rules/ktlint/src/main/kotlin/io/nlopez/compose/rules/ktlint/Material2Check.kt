// Copyright 2024 Nacho Lopez
// SPDX-License-Identifier: Apache-2.0
package io.nlopez.compose.rules.ktlint

import io.nlopez.compose.core.ComposeKtConfig
import io.nlopez.compose.core.ComposeKtVisitor
import io.nlopez.compose.core.Emitter
import io.nlopez.compose.rules.KtlintRule
import io.nlopez.compose.rules.Material2
import org.jetbrains.kotlin.psi.KtFile

class Material2Check :
    KtlintRule(
        id = "compose:material-two",
        editorConfigProperties = setOf(allowedFromM2, disallowMaterial2),
    ),
    ComposeKtVisitor {
    private val visitor = Material2()

    override fun visitFile(file: KtFile, emitter: Emitter, config: ComposeKtConfig) {
        // ktlint allows all rules by default, so we'll add an extra param to make sure it's disabled by default
        if (config.getBoolean("disallowMaterial2", false)) {
            visitor.visitFile(file, emitter, config)
        }
    }
}
