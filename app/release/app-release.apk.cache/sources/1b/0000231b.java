package com.example.focus.Model.Permissions;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.example.focus.Data.AppInfoData;
import com.example.focus.Data.AppInfoDataNoTime;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GetAppsFunctions.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\f\u001a\u00020\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\nJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\n2\u0006\u0010\u0012\u001a\u00020\rH\u0007J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u0014H\u0007J\u000e\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0017J\u0006\u0010\u001a\u001a\u00020\u001bJ\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u001a\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00140\u001e2\u0006\u0010\u001f\u001a\u00020\rJ\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00110\nH\u0007J\u001c\u0010!\u001a\u00020\u00172\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00140\u001eH\u0007J\u001c\u0010#\u001a\u00020\u00142\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00140\u001eH\u0007R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/example/focus/Model/Permissions/GetAppsFunctions;", "", "packageManager", "Landroid/content/pm/PackageManager;", "usageStatsManager", "Landroid/app/usage/UsageStatsManager;", "context", "Landroid/content/Context;", "(Landroid/content/pm/PackageManager;Landroid/app/usage/UsageStatsManager;Landroid/content/Context;)V", "nonSystemApps", "", "Landroid/content/pm/ApplicationInfo;", "allAppsLaunchTracker", "", "createAppList", "Lcom/example/focus/Data/AppInfoDataNoTime;", "createAppListWithTimeSpent", "Lcom/example/focus/Data/AppInfoData;", "timeInterval", "formatMillisecondsLong", "", "milliseconds", "formatMillisecondsWithoutSeconds", "", "getAppLaunchCount", "packageName", "getInstalledApps", "", "getNonSystemApps", "getTimeSpent", "", "periodOfTimeInDays", "getTopFiveMostUsedApps", "getTotalTimeSpent", "map", "getTotalTimeSpentAverage", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class GetAppsFunctions {
    public static final int $stable = 8;
    private final Context context;
    private List<? extends ApplicationInfo> nonSystemApps;
    private final PackageManager packageManager;
    private final UsageStatsManager usageStatsManager;

    public final List<ApplicationInfo> getNonSystemApps() {
        return this.nonSystemApps;
    }

    public GetAppsFunctions(PackageManager packageManager, UsageStatsManager usageStatsManager, Context context) {
        Intrinsics.checkNotNullParameter(packageManager, "packageManager");
        Intrinsics.checkNotNullParameter(usageStatsManager, "usageStatsManager");
        Intrinsics.checkNotNullParameter(context, "context");
        this.packageManager = packageManager;
        this.usageStatsManager = usageStatsManager;
        this.context = context;
        this.nonSystemApps = CollectionsKt.emptyList();
    }

    public final void getInstalledApps() {
        List<ApplicationInfo> installedApplications = this.packageManager.getInstalledApplications(1);
        Intrinsics.checkNotNullExpressionValue(installedApplications, "packageManager.getInstal….GET_ACTIVITIES\n        )");
        ArrayList arrayList = new ArrayList();
        for (Object obj : installedApplications) {
            ApplicationInfo applicationInfo = (ApplicationInfo) obj;
            if ((applicationInfo.flags & 1) == 0 || Intrinsics.areEqual(applicationInfo.packageName, "com.google.android.youtube") || Intrinsics.areEqual(applicationInfo.packageName, "com.android.chrome")) {
                arrayList.add(obj);
            }
        }
        this.nonSystemApps = arrayList;
    }

    public final Map<String, Long> getTimeSpent(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -i);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        long timeInMillis = calendar.getTimeInMillis();
        calendar.setTimeInMillis(System.currentTimeMillis());
        long timeInMillis2 = calendar.getTimeInMillis();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Log.d("Debug", "Start Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(timeInMillis)));
        Log.d("Debug", "End Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(timeInMillis2)));
        Log.d("Debug", calendar.toString());
        Object systemService = this.context.getSystemService("usagestats");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.usage.UsageStatsManager");
        Map<String, UsageStats> queryUsageStats = ((UsageStatsManager) systemService).queryAndAggregateUsageStats(timeInMillis, timeInMillis2);
        Intrinsics.checkNotNullExpressionValue(queryUsageStats, "queryUsageStats");
        for (Map.Entry<String, UsageStats> entry : queryUsageStats.entrySet()) {
            String packageName = entry.getKey();
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            linkedHashMap.put(packageName, Long.valueOf(entry.getValue().getTotalTimeInForeground()));
        }
        return linkedHashMap;
    }

    public final String formatMillisecondsWithoutSeconds(long j) {
        Duration ofMillis = Duration.ofMillis(j);
        long hours = ofMillis.toHours();
        long minutes = ofMillis.minusHours(hours).toMinutes();
        long seconds = ofMillis.minusHours(hours).minusMinutes(minutes).getSeconds();
        if (hours == 0 && minutes == 0) {
            return seconds + " s";
        }
        return hours + " h : " + minutes + " m";
    }

    public final long formatMillisecondsLong(long j) {
        return Duration.ofMillis(j).toMillis();
    }

    public final List<AppInfoDataNoTime> createAppList() {
        PackageManager packageManager = this.context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        Object systemService = this.context.getSystemService("usagestats");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.usage.UsageStatsManager");
        GetAppsFunctions getAppsFunctions = new GetAppsFunctions(packageManager, (UsageStatsManager) systemService, this.context);
        getAppsFunctions.getInstalledApps();
        List<ApplicationInfo> nonSystemApps = getAppsFunctions.getNonSystemApps();
        Iterator<ApplicationInfo> it = nonSystemApps.iterator();
        while (it.hasNext()) {
            System.out.println((Object) ("NAME OF THE APPS " + it.next().packageName));
        }
        List<ApplicationInfo> list = nonSystemApps;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (ApplicationInfo applicationInfo : list) {
            Drawable applicationIcon = this.packageManager.getApplicationIcon(applicationInfo.packageName);
            Intrinsics.checkNotNullExpressionValue(applicationIcon, "packageManager.getApplic…Icon(appInfo.packageName)");
            CharSequence applicationLabel = this.packageManager.getApplicationLabel(applicationInfo);
            Intrinsics.checkNotNullExpressionValue(applicationLabel, "packageManager.getApplicationLabel(appInfo)");
            String str = applicationInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(str, "appInfo.packageName");
            arrayList.add(new AppInfoDataNoTime(applicationIcon, applicationLabel, str));
        }
        return arrayList;
    }

    public final List<AppInfoData> createAppListWithTimeSpent(int i) {
        PackageManager packageManager = this.context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        Object systemService = this.context.getSystemService("usagestats");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.usage.UsageStatsManager");
        GetAppsFunctions getAppsFunctions = new GetAppsFunctions(packageManager, (UsageStatsManager) systemService, this.context);
        getAppsFunctions.getInstalledApps();
        List<ApplicationInfo> nonSystemApps = getAppsFunctions.getNonSystemApps();
        Map<String, Long> timeSpent = getAppsFunctions.getTimeSpent(i);
        List<ApplicationInfo> list = nonSystemApps;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (ApplicationInfo applicationInfo : list) {
            Drawable applicationIcon = this.packageManager.getApplicationIcon(applicationInfo.packageName);
            Intrinsics.checkNotNullExpressionValue(applicationIcon, "packageManager.getApplic…Icon(appInfo.packageName)");
            CharSequence applicationLabel = this.packageManager.getApplicationLabel(applicationInfo);
            Intrinsics.checkNotNullExpressionValue(applicationLabel, "packageManager.getApplicationLabel(appInfo)");
            Long l = timeSpent.get(applicationInfo.packageName);
            long j = 0;
            String formatMillisecondsWithoutSeconds = getAppsFunctions.formatMillisecondsWithoutSeconds(l != null ? l.longValue() : 0L);
            Long l2 = timeSpent.get(applicationInfo.packageName);
            if (l2 != null) {
                j = l2.longValue();
            }
            long formatMillisecondsLong = getAppsFunctions.formatMillisecondsLong(j);
            String packageName = applicationInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            arrayList.add(new AppInfoData(applicationIcon, applicationLabel, formatMillisecondsWithoutSeconds, formatMillisecondsLong, packageName));
        }
        return CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.example.focus.Model.Permissions.GetAppsFunctions$createAppListWithTimeSpent$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Long.valueOf(((AppInfoData) t2).getTimeSpentLong()), Long.valueOf(((AppInfoData) t).getTimeSpentLong()));
            }
        });
    }

    public final String getTotalTimeSpent(Map<String, Long> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        long j = 0;
        for (String str : map.keySet()) {
            Long l = map.get(str);
            j += l != null ? l.longValue() : 0L;
        }
        return formatMillisecondsWithoutSeconds(j);
    }

    public final long getTotalTimeSpentAverage(Map<String, Long> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        long j = 0;
        for (String str : map.keySet()) {
            Long l = map.get(str);
            j += l != null ? l.longValue() : 0L;
        }
        return j / 7;
    }

    public final List<AppInfoData> getTopFiveMostUsedApps() {
        PackageManager packageManager = this.context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        Object systemService = this.context.getSystemService("usagestats");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.usage.UsageStatsManager");
        GetAppsFunctions getAppsFunctions = new GetAppsFunctions(packageManager, (UsageStatsManager) systemService, this.context);
        getAppsFunctions.getInstalledApps();
        List<ApplicationInfo> nonSystemApps = getAppsFunctions.getNonSystemApps();
        Map<String, Long> timeSpent = getAppsFunctions.getTimeSpent(7);
        List<ApplicationInfo> list = nonSystemApps;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (ApplicationInfo applicationInfo : list) {
            Drawable applicationIcon = this.packageManager.getApplicationIcon(applicationInfo.packageName);
            Intrinsics.checkNotNullExpressionValue(applicationIcon, "packageManager.getApplic…Icon(appInfo.packageName)");
            CharSequence applicationLabel = this.packageManager.getApplicationLabel(applicationInfo);
            Intrinsics.checkNotNullExpressionValue(applicationLabel, "packageManager.getApplicationLabel(appInfo)");
            Long l = timeSpent.get(applicationInfo.packageName);
            long j = 0;
            String formatMillisecondsWithoutSeconds = getAppsFunctions.formatMillisecondsWithoutSeconds(l != null ? l.longValue() : 0L);
            Long l2 = timeSpent.get(applicationInfo.packageName);
            if (l2 != null) {
                j = l2.longValue();
            }
            long formatMillisecondsLong = getAppsFunctions.formatMillisecondsLong(j);
            String packageName = applicationInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            arrayList.add(new AppInfoData(applicationIcon, applicationLabel, formatMillisecondsWithoutSeconds, formatMillisecondsLong, packageName));
        }
        return CollectionsKt.take(CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.example.focus.Model.Permissions.GetAppsFunctions$getTopFiveMostUsedApps$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Long.valueOf(((AppInfoData) t2).getTimeSpentLong()), Long.valueOf(((AppInfoData) t).getTimeSpentLong()));
            }
        }), 5);
    }

    public final int getAppLaunchCount(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -1);
        long currentTimeMillis = System.currentTimeMillis();
        List<UsageStats> queryUsageStats = this.usageStatsManager.queryUsageStats(0, calendar.getTimeInMillis(), currentTimeMillis);
        Field declaredField = UsageStats.class.getDeclaredField("mLaunchCount");
        int i = 0;
        for (UsageStats usageStats : queryUsageStats) {
            if (Intrinsics.areEqual(usageStats.getPackageName(), packageName)) {
                Object obj = declaredField.get(usageStats);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
                i = ((Integer) obj).intValue();
            }
        }
        return i;
    }

    public final int allAppsLaunchTracker(List<? extends ApplicationInfo> nonSystemApps) {
        Intrinsics.checkNotNullParameter(nonSystemApps, "nonSystemApps");
        int i = 0;
        for (ApplicationInfo applicationInfo : nonSystemApps) {
            String str = applicationInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(str, "info.packageName");
            i += getAppLaunchCount(str);
        }
        return i;
    }
}