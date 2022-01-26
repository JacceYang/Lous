package com.iyetoo.mpm.lous.keep.duplix.ext;

/**
 * {@link MemUnit } a helpful memory unit transfer tool.
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 9:26 PM 2019/8/27
 **/
public enum MemUnit {

    /**
     * Memory unit byte.
     */
    B("B") {
        public long toByte(long x) {
            return x;
        }
    },
    /**
     * Memory unit KB.
     */
    KB("KB") {
        public long toByte(long x) {
            return x * UKB;
        }
    },

    M("M") {
        public long toByte(long x) {
            return UM * x;
        }
    },

    G("G") {
        public long toByte(long x) {
            return UG * x;
        }
    };

    MemUnit(String token) {
        this.token = token;
    }

    private static long UKB = 1024;
    private static long UM = UKB * 1204;
    private static long UG = UM * 1204;

    private String token;

    public String getToken() {
        return token;
    }

    public long toByte(long x) {
        throw new AbstractMethodError();
    }

    public static MemUnit getUnit(String unit) {
        for (MemUnit un : MemUnit.values()) {

            if (un.token.equals(unit)) {
                return un;
            }
        }
        throw new IllegalArgumentException("invalid Unit!" + unit);
    }

}
