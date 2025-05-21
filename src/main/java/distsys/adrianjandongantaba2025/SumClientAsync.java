/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.adrianjandongantaba2025;

/**
 *
 * @author ajandongan Distributed System TABA 2025
 */

import grpc.sum.SumRequest;
import grpc.sum.SumResponse;
import grpc.sum.SumServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SumClientAsync {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        SumServiceGrpc.SumServiceStub asyncStub = SumServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(10);

        SumRequest request = SumRequest.newBuilder()
                .addNumbers(7)
                .addNumbers(3)
                .addNumbers(10)
                .build();

        asyncStub.calculateSum(request, new StreamObserver<SumResponse>() {
            @Override
            public void onNext(SumResponse value) {
                System.out.println("[Async] Sum result: " + value.getResult());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Error occurred: " + t.getMessage());
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("[Async] Server completed response.");
                latch.countDown();
            }
        });

        // Wait until the async call completes
        latch.await(5, TimeUnit.SECONDS);
        channel.shutdown();
    }
}
