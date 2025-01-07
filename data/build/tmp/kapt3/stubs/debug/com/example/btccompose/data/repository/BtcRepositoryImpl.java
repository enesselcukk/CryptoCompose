package com.example.btccompose.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0096@\u00a2\u0006\u0002\u0010\fJ4\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/example/btccompose/data/repository/BtcRepositoryImpl;", "", "btcApi", "Lcom/example/btccompose/data/api/BtcApi;", "btcGraphApi", "Lcom/example/btccompose/data/api/BtcGraphApi;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lcom/example/btccompose/data/api/BtcApi;Lcom/example/btccompose/data/api/BtcGraphApi;Lkotlinx/coroutines/CoroutineDispatcher;)V", "getBtc", "Lkotlinx/coroutines/flow/Flow;", "error/NonExistentClass", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBtcDetail", "from", "", "resolution", "", "symbol", "", "to", "(JILjava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class BtcRepositoryImpl {
    @org.jetbrains.annotations.NotNull()
    private final com.example.btccompose.data.api.BtcApi btcApi = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.btccompose.data.api.BtcGraphApi btcGraphApi = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher dispatcher = null;
    
    @javax.inject.Inject()
    public BtcRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.btccompose.data.api.BtcApi btcApi, @org.jetbrains.annotations.NotNull()
    com.example.btccompose.data.api.BtcGraphApi btcGraphApi, @error.NonExistentClass()
    @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher dispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getBtc(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<error.NonExistentClass>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getBtcDetail(long from, int resolution, @org.jetbrains.annotations.NotNull()
    java.lang.String symbol, long to, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<error.NonExistentClass>> $completion) {
        return null;
    }
}