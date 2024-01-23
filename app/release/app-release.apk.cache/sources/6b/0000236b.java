package com.example.focus.Presentation.Screens.Landing;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.navigation.NavHostController;
import com.example.focus.Presentation.Screens.Landing.Screen;
import com.google.accompanist.navigation.animation.AnimatedNavHostKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SetupNavGraph.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"SetupNavGraph", "", "navController", "Landroidx/navigation/NavHostController;", "context", "Landroid/content/Context;", "(Landroidx/navigation/NavHostController;Landroid/content/Context;Landroidx/compose/runtime/Composer;I)V", "app_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SetupNavGraphKt {
    public static final void SetupNavGraph(NavHostController navController, Context context, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(context, "context");
        Composer startRestartGroup = composer.startRestartGroup(-718913933);
        ComposerKt.sourceInformation(startRestartGroup, "C(SetupNavGraph)P(1)");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-718913933, i, -1, "com.example.focus.Presentation.Screens.Landing.SetupNavGraph (SetupNavGraph.kt:34)");
        }
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = startRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        SharedPreferences sharedPreferences = ((Context) consume).getSharedPreferences("TutorialPermissionsFinished", 0);
        boolean z = sharedPreferences.getBoolean("TutorialPermissionsFinished", false);
        boolean z2 = sharedPreferences.getBoolean("QuizFinished", false);
        System.out.println((Object) ("quizFinished: " + z2));
        System.out.println((Object) ("TutorialPermissionsFinished: " + z));
        AnimatedNavHostKt.AnimatedNavHost(navController, !z2 ? Screen.LandingPage.INSTANCE.getRoute() : Screen.AppNavigation.INSTANCE.getRoute(), null, null, null, null, null, null, null, new SetupNavGraphKt$SetupNavGraph$1(navController, context, sharedPreferences), startRestartGroup, 8, 508);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new SetupNavGraphKt$SetupNavGraph$2(navController, context, i));
    }
}