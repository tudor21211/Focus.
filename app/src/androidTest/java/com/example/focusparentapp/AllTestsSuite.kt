package com.example.focusparentapp

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

// Adaugă toate clasele de test în această suită
@RunWith(Suite::class)
@SuiteClasses(
    PackageEntityTest::class,
    PackageStatsEntityTest::class,
    RestrictedKeywordsEntityTest::class,
    ScreenTimeTrackerEntityTest::class,
    UserEntityTest::class,
    LocationCoordinatesEntityTest::class,
    UserPackageCrossRefTest::class
)
class AllTestsSuite