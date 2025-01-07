package com.example.btccompose.data.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J6\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/example/btccompose/data/api/BtcGraphApi;", "", "getBtcDetail", "Lcom/example/btccompose/data/model/BtcDataResponseGraph;", "from", "", "resolution", "", "symbol", "", "to", "(JILjava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface BtcGraphApi {
    
    @retrofit2.http.GET(value = "v1/klines/history")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBtcDetail(@retrofit2.http.Query(value = "from")
    long from, @retrofit2.http.Query(value = "resolution")
    int resolution, @retrofit2.http.Query(value = "symbol")
    @org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @retrofit2.http.Query(value = "to")
    long to, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.btccompose.data.model.BtcDataResponseGraph> $completion);
}