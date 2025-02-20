package com.realProject.payload;

public class OTPDetails {
    private final String otp;
    private final long timeStamp;

    public OTPDetails(String otp, long timeStamp)
    {
        this.otp = otp;
        this.timeStamp = timeStamp;
    }

    public String getOtp() {
        return otp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
