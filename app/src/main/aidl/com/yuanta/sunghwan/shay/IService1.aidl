// IService1.aidl
package com.yuanta.sunghwan.shay;

// Declare any non-default types here with import statements

interface IService1 {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     int getCurNumber();
     int sum(int a, int b);

    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
