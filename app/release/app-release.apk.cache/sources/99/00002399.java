package com.example.focus.Presentation.Screens.Landing;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Dp;
import com.example.focus.Data.AppInfoData;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TimeSpentScreen.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TimeSpentScreenKt$timeSpentScreen$2$3 extends Lambda implements Function3<ColumnScope, Composer, Integer, Unit> {
    final /* synthetic */ MutableState<List<AppInfoData>> $appInfoList$delegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimeSpentScreenKt$timeSpentScreen$2$3(MutableState<List<AppInfoData>> mutableState) {
        super(3);
        this.$appInfoList$delegate = mutableState;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
        invoke(columnScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r15v0, types: [androidx.compose.runtime.Composer] */
    public final void invoke(ColumnScope ElevatedCard, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(ElevatedCard, "$this$ElevatedCard");
        if ((i & 81) != 16 || !composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-625953473, i, -1, "com.example.focus.Presentation.Screens.Landing.timeSpentScreen.<anonymous>.<anonymous> (TimeSpentScreen.kt:130)");
            }
            Modifier m415paddingVpY3zN4 = PaddingKt.m415paddingVpY3zN4(Modifier.Companion, Dp.m5099constructorimpl(6), Dp.m5099constructorimpl(3));
            MutableState<List<AppInfoData>> mutableState = this.$appInfoList$delegate;
            composer.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean changed = composer.changed(mutableState);
            TimeSpentScreenKt$timeSpentScreen$2$3$1$1 rememberedValue = composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                rememberedValue = new TimeSpentScreenKt$timeSpentScreen$2$3$1$1(mutableState);
                composer.updateRememberedValue(rememberedValue);
            }
            composer.endReplaceableGroup();
            LazyDslKt.LazyColumn(m415paddingVpY3zN4, null, null, false, null, null, null, false, rememberedValue, composer, 6, 254);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        composer.skipToGroupEnd();
    }
}