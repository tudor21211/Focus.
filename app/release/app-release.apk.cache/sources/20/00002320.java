package com.example.focus.Presentation.IndividualPermissions;

import android.content.Context;
import android.content.res.Configuration;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.text.font.FontFamilyKt;
import androidx.compose.ui.text.font.FontKt;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.focus.Presentation.Screens.Landing.SharedFunctionsKt;
import com.example.focus.R;
import com.google.accompanist.systemuicontroller.SystemUiController;
import com.google.accompanist.systemuicontroller.SystemUiControllerKt;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;

/* compiled from: Accessibility.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"accessibilityPermission", "", "(Landroidx/compose/runtime/Composer;I)V", "app_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AccessibilityKt {
    public static final void accessibilityPermission(Composer composer, int i) {
        Composer composer2;
        Composer startRestartGroup = composer.startRestartGroup(-230995771);
        ComposerKt.sourceInformation(startRestartGroup, "C(accessibilityPermission)");
        if (i == 0 && startRestartGroup.getSkipping()) {
            startRestartGroup.skipToGroupEnd();
            composer2 = startRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-230995771, i, -1, "com.example.focus.Presentation.IndividualPermissions.accessibilityPermission (Accessibility.kt:45)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Context context = (Context) consume;
            SystemUiController rememberSystemUiController = SystemUiControllerKt.rememberSystemUiController(null, startRestartGroup, 0, 1);
            Pair[] pairArr = {TuplesKt.to(Float.valueOf(0.2f), Color.m2595boximpl(ColorKt.Color(4279109203L))), TuplesKt.to(Float.valueOf(0.5f), Color.m2595boximpl(ColorKt.Color(4278780737L))), TuplesKt.to(Float.valueOf(1.0f), Color.m2595boximpl(ColorKt.Color(4278649393L)))};
            Pair[] pairArr2 = {TuplesKt.to(Float.valueOf(0.2f), Color.m2595boximpl(ColorKt.Color(4289962987L))), TuplesKt.to(Float.valueOf(0.5f), Color.m2595boximpl(ColorKt.Color(4282531433L))), TuplesKt.to(Float.valueOf(1.0f), Color.m2595boximpl(ColorKt.Color(4283450762L)))};
            startRestartGroup.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation(startRestartGroup, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean changed = startRestartGroup.changed(rememberSystemUiController);
            AccessibilityKt$accessibilityPermission$1$1 rememberedValue = startRestartGroup.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                rememberedValue = new AccessibilityKt$accessibilityPermission$1$1(rememberSystemUiController);
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            startRestartGroup.endReplaceableGroup();
            EffectsKt.SideEffect((Function0) rememberedValue, startRestartGroup, 0);
            List<String> listOf = CollectionsKt.listOf((Object[]) new String[]{"Click the button below to open the Settings app.", "Find Focus. in the list of apps and click on it.", "Activate the permission in the new tab.", "The accesibility setting should be set to \"On\""});
            Modifier verticalScroll$default = ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(BackgroundKt.background$default(Modifier.Companion, Brush.Companion.m2557linearGradientmHitzGk$default(Brush.Companion, (Pair[]) Arrays.copyOf(pairArr, 3), 0L, 0L, 0, 14, (Object) null), null, 0.0f, 6, null), 0.0f, 1, null), ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), false, null, false, 14, null);
            Alignment.Horizontal centerHorizontally = Alignment.Companion.getCenterHorizontally();
            startRestartGroup.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation(startRestartGroup, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, startRestartGroup, 48);
            startRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(startRestartGroup, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object consume2 = startRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Density density = (Density) consume2;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object consume3 = startRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            LayoutDirection layoutDirection = (LayoutDirection) consume3;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object consume4 = startRestartGroup.consume(CompositionLocalsKt.getLocalViewConfiguration());
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ViewConfiguration viewConfiguration = (ViewConfiguration) consume4;
            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf = LayoutKt.materializerOf(verticalScroll$default);
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                startRestartGroup.createNode(constructor);
            } else {
                startRestartGroup.useNode();
            }
            startRestartGroup.disableReusing();
            Composer m2246constructorimpl = Updater.m2246constructorimpl(startRestartGroup);
            Updater.m2253setimpl(m2246constructorimpl, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.m2253setimpl(m2246constructorimpl, density, ComposeUiNode.Companion.getSetDensity());
            Updater.m2253setimpl(m2246constructorimpl, layoutDirection, ComposeUiNode.Companion.getSetLayoutDirection());
            Updater.m2253setimpl(m2246constructorimpl, viewConfiguration, ComposeUiNode.Companion.getSetViewConfiguration());
            startRestartGroup.enableReusing();
            materializerOf.invoke(SkippableUpdater.m2237boximpl(SkippableUpdater.m2238constructorimpl(startRestartGroup)), startRestartGroup, 0);
            startRestartGroup.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 276693704, "C79@4027L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            long m2642getWhite0d7_KjU = Color.Companion.m2642getWhite0d7_KjU();
            float f = 16;
            Modifier m414padding3ABfNKs = PaddingKt.m414padding3ABfNKs(Modifier.Companion, Dp.m5099constructorimpl(f));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume5 = startRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration());
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            String str = "CC:CompositionLocal.kt#9igjgp";
            TextKt.m1639TextfLXpl1I("To offer you a seamless and accessible experience, Focus requires 'Accessibility' permission. This allows us to detect when apps are opened, enabling precise blocking during your focused sessions. Your privacy matters, and we handle this information responsibly, solely for optimizing your productivity. Thank you for trusting us to help you stay focused and achieve your goals!", SizeKt.fillMaxWidth(m414padding3ABfNKs, ((Configuration) consume5).screenWidthDp > 600 ? 0.9f : 1.0f), m2642getWhite0d7_KjU, TextUnitKt.getSp(19), null, null, FontFamilyKt.FontFamily(FontKt.m4704FontYpTlLL0$default(R.font.opensans_res, null, 0, 0, 14, null)), 0L, null, TextAlign.m4986boximpl(TextAlign.Companion.m4995getJustifye0LSkKk()), 0L, 0, false, 0, null, null, startRestartGroup, 3462, 0, 64944);
            SpacerKt.Spacer(SizeKt.m443height3ABfNKs(Modifier.Companion, Dp.m5099constructorimpl(60)), startRestartGroup, 6);
            ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.info, startRestartGroup, 0), "info image", SizeKt.fillMaxSize(Modifier.Companion, 0.1f), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, startRestartGroup, 440, 120);
            Modifier m414padding3ABfNKs2 = PaddingKt.m414padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, null), Dp.m5099constructorimpl(f));
            Alignment bottomCenter = Alignment.Companion.getBottomCenter();
            startRestartGroup.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation(startRestartGroup, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            MeasurePolicy rememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(bottomCenter, false, startRestartGroup, 6);
            startRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(startRestartGroup, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object consume6 = startRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Density density2 = (Density) consume6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object consume7 = startRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            LayoutDirection layoutDirection2 = (LayoutDirection) consume7;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object consume8 = startRestartGroup.consume(CompositionLocalsKt.getLocalViewConfiguration());
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ViewConfiguration viewConfiguration2 = (ViewConfiguration) consume8;
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.Companion.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf2 = LayoutKt.materializerOf(m414padding3ABfNKs2);
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                startRestartGroup.createNode(constructor2);
            } else {
                startRestartGroup.useNode();
            }
            startRestartGroup.disableReusing();
            Composer m2246constructorimpl2 = Updater.m2246constructorimpl(startRestartGroup);
            Updater.m2253setimpl(m2246constructorimpl2, rememberBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.m2253setimpl(m2246constructorimpl2, density2, ComposeUiNode.Companion.getSetDensity());
            Updater.m2253setimpl(m2246constructorimpl2, layoutDirection2, ComposeUiNode.Companion.getSetLayoutDirection());
            Updater.m2253setimpl(m2246constructorimpl2, viewConfiguration2, ComposeUiNode.Companion.getSetViewConfiguration());
            startRestartGroup.enableReusing();
            materializerOf2.invoke(SkippableUpdater.m2237boximpl(SkippableUpdater.m2238constructorimpl(startRestartGroup)), startRestartGroup, 0);
            startRestartGroup.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            TextKt.m1639TextfLXpl1I("How to activate the permission?", null, Color.Companion.m2642getWhite0d7_KjU(), TextUnitKt.getSp(24), null, null, FontFamilyKt.FontFamily(FontKt.m4704FontYpTlLL0$default(R.font.opensans_res, null, 0, 0, 14, null)), 0L, null, null, 0L, 0, false, 0, null, null, startRestartGroup, 3462, 0, 65458);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endReplaceableGroup();
            startRestartGroup.endNode();
            startRestartGroup.endReplaceableGroup();
            startRestartGroup.endReplaceableGroup();
            composer2 = startRestartGroup;
            composer2.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation(composer2, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
            int i2 = 0;
            MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer2, 0);
            int i3 = -1323940314;
            composer2.startReplaceableGroup(-1323940314);
            String str2 = "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh";
            ComposerKt.sourceInformation(composer2, str2);
            String str3 = "C:CompositionLocal.kt#9igjgp";
            int i4 = 2023513938;
            ComposerKt.sourceInformationMarkerStart(composer2, 2023513938, str3);
            Object consume9 = composer2.consume(CompositionLocalsKt.getLocalDensity());
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Density density3 = (Density) consume9;
            ComposerKt.sourceInformationMarkerStart(composer2, 2023513938, str3);
            Object consume10 = composer2.consume(CompositionLocalsKt.getLocalLayoutDirection());
            ComposerKt.sourceInformationMarkerEnd(composer2);
            LayoutDirection layoutDirection3 = (LayoutDirection) consume10;
            ComposerKt.sourceInformationMarkerStart(composer2, 2023513938, str3);
            Object consume11 = composer2.consume(CompositionLocalsKt.getLocalViewConfiguration());
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ViewConfiguration viewConfiguration3 = (ViewConfiguration) consume11;
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.Companion.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf3 = LayoutKt.materializerOf(Modifier.Companion);
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor3);
            } else {
                composer2.useNode();
            }
            composer2.disableReusing();
            Composer m2246constructorimpl3 = Updater.m2246constructorimpl(composer2);
            Updater.m2253setimpl(m2246constructorimpl3, columnMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.m2253setimpl(m2246constructorimpl3, density3, ComposeUiNode.Companion.getSetDensity());
            Updater.m2253setimpl(m2246constructorimpl3, layoutDirection3, ComposeUiNode.Companion.getSetLayoutDirection());
            Updater.m2253setimpl(m2246constructorimpl3, viewConfiguration3, ComposeUiNode.Companion.getSetViewConfiguration());
            composer2.enableReusing();
            materializerOf3.invoke(SkippableUpdater.m2237boximpl(SkippableUpdater.m2238constructorimpl(composer2)), composer2, 0);
            int i5 = 2058660585;
            composer2.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(composer2, 276693704, "C79@4027L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            composer2.startReplaceableGroup(141971155);
            for (String str4 : listOf) {
                Modifier.Companion companion = Modifier.Companion;
                String str5 = str;
                ComposerKt.sourceInformationMarkerStart(composer2, i4, str5);
                Object consume12 = composer2.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration());
                ComposerKt.sourceInformationMarkerEnd(composer2);
                Modifier m418paddingqDBjuR0$default = PaddingKt.m418paddingqDBjuR0$default(companion, Dp.m5099constructorimpl(((Configuration) consume12).screenWidthDp > 600 ? 50 : i2), 0.0f, 0.0f, 0.0f, 14, null);
                composer2.startReplaceableGroup(693286680);
                ComposerKt.sourceInformation(composer2, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.Companion.getTop(), composer2, i2);
                composer2.startReplaceableGroup(i3);
                ComposerKt.sourceInformation(composer2, str2);
                ComposerKt.sourceInformationMarkerStart(composer2, i4, str3);
                Object consume13 = composer2.consume(CompositionLocalsKt.getLocalDensity());
                ComposerKt.sourceInformationMarkerEnd(composer2);
                Density density4 = (Density) consume13;
                ComposerKt.sourceInformationMarkerStart(composer2, i4, str3);
                Object consume14 = composer2.consume(CompositionLocalsKt.getLocalLayoutDirection());
                ComposerKt.sourceInformationMarkerEnd(composer2);
                LayoutDirection layoutDirection4 = (LayoutDirection) consume14;
                ComposerKt.sourceInformationMarkerStart(composer2, i4, str3);
                Object consume15 = composer2.consume(CompositionLocalsKt.getLocalViewConfiguration());
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ViewConfiguration viewConfiguration4 = (ViewConfiguration) consume15;
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.Companion.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf4 = LayoutKt.materializerOf(m418paddingqDBjuR0$default);
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor4);
                } else {
                    composer2.useNode();
                }
                composer2.disableReusing();
                Composer m2246constructorimpl4 = Updater.m2246constructorimpl(composer2);
                Updater.m2253setimpl(m2246constructorimpl4, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.m2253setimpl(m2246constructorimpl4, density4, ComposeUiNode.Companion.getSetDensity());
                Updater.m2253setimpl(m2246constructorimpl4, layoutDirection4, ComposeUiNode.Companion.getSetLayoutDirection());
                Updater.m2253setimpl(m2246constructorimpl4, viewConfiguration4, ComposeUiNode.Companion.getSetViewConfiguration());
                composer2.enableReusing();
                materializerOf4.invoke(SkippableUpdater.m2237boximpl(SkippableUpdater.m2238constructorimpl(composer2)), composer2, Integer.valueOf(i2));
                composer2.startReplaceableGroup(i5);
                ComposerKt.sourceInformationMarkerStart(composer2, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                Composer composer3 = composer2;
                TextKt.m1639TextfLXpl1I("•", SizeKt.m462width3ABfNKs(Modifier.Companion, Dp.m5099constructorimpl(20)), Color.Companion.m2642getWhite0d7_KjU(), TextUnitKt.getSp(19), null, null, null, 0L, null, TextAlign.m4986boximpl(TextAlign.Companion.m4993getCentere0LSkKk()), 0L, 0, false, 0, null, null, composer3, 3510, 0, 65008);
                TextKt.m1639TextfLXpl1I(str4, rowScopeInstance.weight(Modifier.Companion, 1.0f, true), Color.Companion.m2642getWhite0d7_KjU(), TextUnitKt.getSp(16), null, null, FontFamilyKt.FontFamily(FontKt.m4704FontYpTlLL0$default(R.font.opensans_res, null, 0, 0, 14, null)), 0L, null, TextAlign.m4986boximpl(TextAlign.Companion.m4995getJustifye0LSkKk()), 0L, 0, false, 0, null, null, composer3, 3456, 0, 64944);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer3.endReplaceableGroup();
                composer3.endNode();
                composer3.endReplaceableGroup();
                composer3.endReplaceableGroup();
                i2 = 0;
                composer2 = composer3;
                str = str5;
                i5 = i5;
                i4 = 2023513938;
                str2 = str2;
                str3 = str3;
                i3 = i3;
            }
            Composer composer4 = composer2;
            composer4.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd(composer4);
            composer4.endReplaceableGroup();
            composer4.endNode();
            composer4.endReplaceableGroup();
            composer4.endReplaceableGroup();
            float f2 = 20;
            SpacerKt.Spacer(PaddingKt.m414padding3ABfNKs(Modifier.Companion, Dp.m5099constructorimpl(f2)), composer2, 6);
            SharedFunctionsKt.GifImage(SizeKt.m457size3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, null), Dp.m5099constructorimpl(180)), R.drawable.accessibility, composer2, 6, 0);
            float f3 = 10;
            SpacerKt.Spacer(PaddingKt.m414padding3ABfNKs(Modifier.Companion, Dp.m5099constructorimpl(f3)), composer2, 6);
            ButtonKt.Button(new AccessibilityKt$accessibilityPermission$2$3(context, ComposerKt.invocationKey), BorderKt.border(ClipKt.clip(SizeKt.fillMaxWidth(PaddingKt.m418paddingqDBjuR0$default(columnScopeInstance.align(Modifier.Companion, Alignment.Companion.getCenterHorizontally()), 0.0f, Dp.m5099constructorimpl(f2), 0.0f, 0.0f, 13, null), 0.8f), RoundedCornerShapeKt.m688RoundedCornerShape0680j_4(Dp.m5099constructorimpl(f3))), new BorderStroke(Dp.m5099constructorimpl(1), Brush.Companion.m2557linearGradientmHitzGk$default(Brush.Companion, (Pair[]) Arrays.copyOf(pairArr2, 3), 0L, 0L, 0, 14, (Object) null), null), RoundedCornerShapeKt.m688RoundedCornerShape0680j_4(Dp.m5099constructorimpl(100))), false, null, ButtonDefaults.INSTANCE.m1245buttonColorsro_MJ88(ColorKt.Color(4278848005L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14), null, null, null, null, ComposableSingletons$AccessibilityKt.INSTANCE.m5434getLambda1$app_release(), composer2, 805306368, 492);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new AccessibilityKt$accessibilityPermission$3(i));
    }
}