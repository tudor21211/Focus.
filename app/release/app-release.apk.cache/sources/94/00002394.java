package com.example.focus.Presentation.Screens.Landing;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import com.google.accompanist.systemuicontroller.SystemUiController;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TimeSpentScreen.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TimeSpentScreenKt$timeSpentScreen$1$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ SystemUiController $systemUiController;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimeSpentScreenKt$timeSpentScreen$1$1(SystemUiController systemUiController) {
        super(0);
        this.$systemUiController = systemUiController;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        SystemUiController.m5465setSystemBarsColorIv8Zu3U$default(this.$systemUiController, ColorKt.Color(4279309334L), false, false, null, 14, null);
        SystemUiController.m5463setNavigationBarColorIv8Zu3U$default(this.$systemUiController, Color.Companion.m2631getBlack0d7_KjU(), false, false, null, 14, null);
    }
}