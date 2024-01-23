package com.example.focus;

import android.content.SharedPreferences;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.NavOptionsBuilder;
import androidx.navigation.Navigator;
import androidx.navigation.PopUpToBuilder;
import com.example.focus.Model.Permissions.PermissionFunctions;
import com.example.focus.Presentation.Screens.Landing.Screen;
import com.example.focus.Presentation.Screens.Landing.SetupNavGraphKt;
import com.example.focus.ui.theme.ThemeKt;
import com.google.accompanist.navigation.animation.NavHostControllerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "(Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class MainActivity$onActivityResult$1 extends Lambda implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ int $requestCode;
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$onActivityResult$1(MainActivity mainActivity, int i) {
        super(2);
        this.this$0 = mainActivity;
        this.$requestCode = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: com.example.focus.MainActivity$onActivityResult$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $requestCode;
        final /* synthetic */ MainActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(MainActivity mainActivity, int i) {
            super(2);
            this.this$0 = mainActivity;
            this.$requestCode = i;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 11) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1538433261, i, -1, "com.example.focus.MainActivity.onActivityResult.<anonymous>.<anonymous> (MainActivity.kt:44)");
                }
                NavHostController rememberAnimatedNavController = NavHostControllerKt.rememberAnimatedNavController(new Navigator[0], composer, 8);
                SetupNavGraphKt.SetupNavGraph(rememberAnimatedNavController, this.this$0, composer, 8);
                SharedPreferences sharedPreferences = this.this$0.getSharedPreferences("TutorialPermissionsFinished", 0);
                if (this.$requestCode == 100) {
                    MainActivity mainActivity = this.this$0;
                    MainActivity mainActivity2 = mainActivity;
                    String packageName = mainActivity.getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    if (new PermissionFunctions(mainActivity2, packageName).areAllPermissionsEnabled()) {
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putBoolean("TutorialPermissionsFinished", true);
                        edit.apply();
                        NavController.navigate$default(rememberAnimatedNavController, Screen.QuizScreen.INSTANCE.getRoute(), null, null, 6, null);
                    } else {
                        MainActivity mainActivity3 = this.this$0;
                        MainActivity mainActivity4 = mainActivity3;
                        String string = mainActivity3.getString(R.string.app_package_name);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.app_package_name)");
                        if (new PermissionFunctions(mainActivity4, string).isPackageUsageStatsPermissionEnabled()) {
                            rememberAnimatedNavController.navigate(Screen.PermissionsScreen.INSTANCE.getRoute(), C00651.INSTANCE);
                        } else {
                            rememberAnimatedNavController.navigate(Screen.UsageAccess.INSTANCE.getRoute(), AnonymousClass2.INSTANCE);
                        }
                    }
                }
                if (this.$requestCode == 200) {
                    MainActivity mainActivity5 = this.this$0;
                    MainActivity mainActivity6 = mainActivity5;
                    String packageName2 = mainActivity5.getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    if (new PermissionFunctions(mainActivity6, packageName2).areAllPermissionsEnabled()) {
                        SharedPreferences.Editor edit2 = sharedPreferences.edit();
                        edit2.putBoolean("TutorialPermissionsFinished", true);
                        edit2.apply();
                        NavController.navigate$default(rememberAnimatedNavController, Screen.QuizScreen.INSTANCE.getRoute(), null, null, 6, null);
                    } else {
                        MainActivity mainActivity7 = this.this$0;
                        MainActivity mainActivity8 = mainActivity7;
                        String string2 = mainActivity7.getString(R.string.app_package_name);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.app_package_name)");
                        if (new PermissionFunctions(mainActivity8, string2).isAccessibilityServiceEnabled("MyAccessibilityService")) {
                            rememberAnimatedNavController.navigate(Screen.PermissionsScreen.INSTANCE.getRoute(), AnonymousClass3.INSTANCE);
                        } else {
                            rememberAnimatedNavController.navigate(Screen.Accessibility.INSTANCE.getRoute(), AnonymousClass4.INSTANCE);
                        }
                    }
                }
                if (this.$requestCode == 300) {
                    MainActivity mainActivity9 = this.this$0;
                    MainActivity mainActivity10 = mainActivity9;
                    String packageName3 = mainActivity9.getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                    if (new PermissionFunctions(mainActivity10, packageName3).areAllPermissionsEnabled()) {
                        SharedPreferences.Editor edit3 = sharedPreferences.edit();
                        edit3.putBoolean("TutorialPermissionsFinished", true);
                        edit3.apply();
                        NavController.navigate$default(rememberAnimatedNavController, Screen.QuizScreen.INSTANCE.getRoute(), null, null, 6, null);
                    } else {
                        MainActivity mainActivity11 = this.this$0;
                        MainActivity mainActivity12 = mainActivity11;
                        String string3 = mainActivity11.getString(R.string.app_package_name);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.app_package_name)");
                        if (new PermissionFunctions(mainActivity12, string3).isOverlayPermissionEnabled()) {
                            rememberAnimatedNavController.navigate(Screen.PermissionsScreen.INSTANCE.getRoute(), AnonymousClass5.INSTANCE);
                        } else {
                            rememberAnimatedNavController.navigate(Screen.DisplayOverOtherApps.INSTANCE.getRoute(), AnonymousClass6.INSTANCE);
                        }
                    }
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: MainActivity.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* renamed from: com.example.focus.MainActivity$onActivityResult$1$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C00651 extends Lambda implements Function1<NavOptionsBuilder, Unit> {
            public static final C00651 INSTANCE = new C00651();

            C00651() {
                super(1);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MainActivity.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            /* renamed from: com.example.focus.MainActivity$onActivityResult$1$1$1$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C00661 extends Lambda implements Function1<PopUpToBuilder, Unit> {
                public static final C00661 INSTANCE = new C00661();

                C00661() {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PopUpToBuilder popUpToBuilder) {
                    invoke2(popUpToBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(PopUpToBuilder popUpTo) {
                    Intrinsics.checkNotNullParameter(popUpTo, "$this$popUpTo");
                    popUpTo.setInclusive(true);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NavOptionsBuilder navOptionsBuilder) {
                invoke2(navOptionsBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(NavOptionsBuilder navigate) {
                Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
                navigate.popUpTo(Screen.PermissionsScreen.INSTANCE.getRoute(), C00661.INSTANCE);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: MainActivity.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* renamed from: com.example.focus.MainActivity$onActivityResult$1$1$2  reason: invalid class name */
        /* loaded from: classes.dex */
        public static final class AnonymousClass2 extends Lambda implements Function1<NavOptionsBuilder, Unit> {
            public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

            AnonymousClass2() {
                super(1);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MainActivity.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            /* renamed from: com.example.focus.MainActivity$onActivityResult$1$1$2$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C00671 extends Lambda implements Function1<PopUpToBuilder, Unit> {
                public static final C00671 INSTANCE = new C00671();

                C00671() {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PopUpToBuilder popUpToBuilder) {
                    invoke2(popUpToBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(PopUpToBuilder popUpTo) {
                    Intrinsics.checkNotNullParameter(popUpTo, "$this$popUpTo");
                    popUpTo.setInclusive(true);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NavOptionsBuilder navOptionsBuilder) {
                invoke2(navOptionsBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(NavOptionsBuilder navigate) {
                Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
                navigate.popUpTo(Screen.UsageAccess.INSTANCE.getRoute(), C00671.INSTANCE);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: MainActivity.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* renamed from: com.example.focus.MainActivity$onActivityResult$1$1$3  reason: invalid class name */
        /* loaded from: classes.dex */
        public static final class AnonymousClass3 extends Lambda implements Function1<NavOptionsBuilder, Unit> {
            public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

            AnonymousClass3() {
                super(1);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MainActivity.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            /* renamed from: com.example.focus.MainActivity$onActivityResult$1$1$3$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C00681 extends Lambda implements Function1<PopUpToBuilder, Unit> {
                public static final C00681 INSTANCE = new C00681();

                C00681() {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PopUpToBuilder popUpToBuilder) {
                    invoke2(popUpToBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(PopUpToBuilder popUpTo) {
                    Intrinsics.checkNotNullParameter(popUpTo, "$this$popUpTo");
                    popUpTo.setInclusive(true);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NavOptionsBuilder navOptionsBuilder) {
                invoke2(navOptionsBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(NavOptionsBuilder navigate) {
                Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
                navigate.popUpTo(Screen.PermissionsScreen.INSTANCE.getRoute(), C00681.INSTANCE);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: MainActivity.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* renamed from: com.example.focus.MainActivity$onActivityResult$1$1$4  reason: invalid class name */
        /* loaded from: classes.dex */
        public static final class AnonymousClass4 extends Lambda implements Function1<NavOptionsBuilder, Unit> {
            public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

            AnonymousClass4() {
                super(1);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MainActivity.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            /* renamed from: com.example.focus.MainActivity$onActivityResult$1$1$4$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C00691 extends Lambda implements Function1<PopUpToBuilder, Unit> {
                public static final C00691 INSTANCE = new C00691();

                C00691() {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PopUpToBuilder popUpToBuilder) {
                    invoke2(popUpToBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(PopUpToBuilder popUpTo) {
                    Intrinsics.checkNotNullParameter(popUpTo, "$this$popUpTo");
                    popUpTo.setInclusive(true);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NavOptionsBuilder navOptionsBuilder) {
                invoke2(navOptionsBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(NavOptionsBuilder navigate) {
                Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
                navigate.popUpTo(Screen.Accessibility.INSTANCE.getRoute(), C00691.INSTANCE);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: MainActivity.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* renamed from: com.example.focus.MainActivity$onActivityResult$1$1$5  reason: invalid class name */
        /* loaded from: classes.dex */
        public static final class AnonymousClass5 extends Lambda implements Function1<NavOptionsBuilder, Unit> {
            public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

            AnonymousClass5() {
                super(1);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MainActivity.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            /* renamed from: com.example.focus.MainActivity$onActivityResult$1$1$5$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C00701 extends Lambda implements Function1<PopUpToBuilder, Unit> {
                public static final C00701 INSTANCE = new C00701();

                C00701() {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PopUpToBuilder popUpToBuilder) {
                    invoke2(popUpToBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(PopUpToBuilder popUpTo) {
                    Intrinsics.checkNotNullParameter(popUpTo, "$this$popUpTo");
                    popUpTo.setInclusive(true);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NavOptionsBuilder navOptionsBuilder) {
                invoke2(navOptionsBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(NavOptionsBuilder navigate) {
                Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
                navigate.popUpTo(Screen.PermissionsScreen.INSTANCE.getRoute(), C00701.INSTANCE);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: MainActivity.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* renamed from: com.example.focus.MainActivity$onActivityResult$1$1$6  reason: invalid class name */
        /* loaded from: classes.dex */
        public static final class AnonymousClass6 extends Lambda implements Function1<NavOptionsBuilder, Unit> {
            public static final AnonymousClass6 INSTANCE = new AnonymousClass6();

            AnonymousClass6() {
                super(1);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MainActivity.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            /* renamed from: com.example.focus.MainActivity$onActivityResult$1$1$6$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C00711 extends Lambda implements Function1<PopUpToBuilder, Unit> {
                public static final C00711 INSTANCE = new C00711();

                C00711() {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PopUpToBuilder popUpToBuilder) {
                    invoke2(popUpToBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(PopUpToBuilder popUpTo) {
                    Intrinsics.checkNotNullParameter(popUpTo, "$this$popUpTo");
                    popUpTo.setInclusive(true);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NavOptionsBuilder navOptionsBuilder) {
                invoke2(navOptionsBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(NavOptionsBuilder navigate) {
                Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
                navigate.popUpTo(Screen.DisplayOverOtherApps.INSTANCE.getRoute(), C00711.INSTANCE);
            }
        }
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        if ((i & 11) != 2 || !composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(287208781, i, -1, "com.example.focus.MainActivity.onActivityResult.<anonymous> (MainActivity.kt:43)");
            }
            ThemeKt.FocusTheme(false, false, ComposableLambdaKt.composableLambda(composer, 1538433261, true, new AnonymousClass1(this.this$0, this.$requestCode)), composer, 384, 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        composer.skipToGroupEnd();
    }
}