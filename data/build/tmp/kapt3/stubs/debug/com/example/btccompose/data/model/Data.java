package com.example.btccompose.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u00c5\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u00a2\u0006\u0002\u0010\u0015J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\u0014H\u00c6\u0003\u00a2\u0006\u0002\u0010$J\u000b\u00104\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010)J\u000b\u00106\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u00ce\u0001\u0010<\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00c6\u0001\u00a2\u0006\u0002\u0010=J\u0013\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010A\u001a\u00020\u0014H\u00d6\u0001J\t\u0010B\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00148\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001dR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001dR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010*\u001a\u0004\b(\u0010)R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0017\u00a8\u0006C"}, d2 = {"Lcom/example/btccompose/data/model/Data;", "", "pair", "", "pairNormalized", "timestamp", "", "last", "high", "low", "bid", "ask", "open", "volume", "average", "daily", "dailyPercent", "denominatorSymbol", "numeratorSymbol", "order", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getAsk", "()Ljava/lang/Object;", "getAverage", "getBid", "getDaily", "getDailyPercent", "getDenominatorSymbol", "()Ljava/lang/String;", "getHigh", "getLast", "getLow", "getNumeratorSymbol", "getOpen", "getOrder", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPair", "getPairNormalized", "getTimestamp", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getVolume", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/example/btccompose/data/model/Data;", "equals", "", "other", "hashCode", "toString", "data_debug"})
public final class Data {
    @com.google.gson.annotations.SerializedName(value = "pair")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String pair = null;
    @com.google.gson.annotations.SerializedName(value = "pairNormalized")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String pairNormalized = null;
    @com.google.gson.annotations.SerializedName(value = "timestamp")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long timestamp = null;
    @com.google.gson.annotations.SerializedName(value = "last")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Object last = null;
    @com.google.gson.annotations.SerializedName(value = "high")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Object high = null;
    @com.google.gson.annotations.SerializedName(value = "low")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Object low = null;
    @com.google.gson.annotations.SerializedName(value = "bid")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Object bid = null;
    @com.google.gson.annotations.SerializedName(value = "ask")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Object ask = null;
    @com.google.gson.annotations.SerializedName(value = "open")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Object open = null;
    @com.google.gson.annotations.SerializedName(value = "volume")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Object volume = null;
    @com.google.gson.annotations.SerializedName(value = "average")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Object average = null;
    @com.google.gson.annotations.SerializedName(value = "daily")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Object daily = null;
    @com.google.gson.annotations.SerializedName(value = "dailyPercent")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Object dailyPercent = null;
    @com.google.gson.annotations.SerializedName(value = "denominatorSymbol")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String denominatorSymbol = null;
    @com.google.gson.annotations.SerializedName(value = "numeratorSymbol")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String numeratorSymbol = null;
    @com.google.gson.annotations.SerializedName(value = "order")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer order = null;
    
    public Data(@org.jetbrains.annotations.Nullable()
    java.lang.String pair, @org.jetbrains.annotations.Nullable()
    java.lang.String pairNormalized, @org.jetbrains.annotations.Nullable()
    java.lang.Long timestamp, @org.jetbrains.annotations.Nullable()
    java.lang.Object last, @org.jetbrains.annotations.Nullable()
    java.lang.Object high, @org.jetbrains.annotations.Nullable()
    java.lang.Object low, @org.jetbrains.annotations.Nullable()
    java.lang.Object bid, @org.jetbrains.annotations.Nullable()
    java.lang.Object ask, @org.jetbrains.annotations.Nullable()
    java.lang.Object open, @org.jetbrains.annotations.Nullable()
    java.lang.Object volume, @org.jetbrains.annotations.Nullable()
    java.lang.Object average, @org.jetbrains.annotations.Nullable()
    java.lang.Object daily, @org.jetbrains.annotations.Nullable()
    java.lang.Object dailyPercent, @org.jetbrains.annotations.Nullable()
    java.lang.String denominatorSymbol, @org.jetbrains.annotations.Nullable()
    java.lang.String numeratorSymbol, @org.jetbrains.annotations.Nullable()
    java.lang.Integer order) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPair() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPairNormalized() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getTimestamp() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLast() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getHigh() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getBid() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAsk() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getOpen() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getVolume() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAverage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getDaily() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getDailyPercent() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDenominatorSymbol() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getNumeratorSymbol() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getOrder() {
        return null;
    }
    
    public Data() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component16() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.btccompose.data.model.Data copy(@org.jetbrains.annotations.Nullable()
    java.lang.String pair, @org.jetbrains.annotations.Nullable()
    java.lang.String pairNormalized, @org.jetbrains.annotations.Nullable()
    java.lang.Long timestamp, @org.jetbrains.annotations.Nullable()
    java.lang.Object last, @org.jetbrains.annotations.Nullable()
    java.lang.Object high, @org.jetbrains.annotations.Nullable()
    java.lang.Object low, @org.jetbrains.annotations.Nullable()
    java.lang.Object bid, @org.jetbrains.annotations.Nullable()
    java.lang.Object ask, @org.jetbrains.annotations.Nullable()
    java.lang.Object open, @org.jetbrains.annotations.Nullable()
    java.lang.Object volume, @org.jetbrains.annotations.Nullable()
    java.lang.Object average, @org.jetbrains.annotations.Nullable()
    java.lang.Object daily, @org.jetbrains.annotations.Nullable()
    java.lang.Object dailyPercent, @org.jetbrains.annotations.Nullable()
    java.lang.String denominatorSymbol, @org.jetbrains.annotations.Nullable()
    java.lang.String numeratorSymbol, @org.jetbrains.annotations.Nullable()
    java.lang.Integer order) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}