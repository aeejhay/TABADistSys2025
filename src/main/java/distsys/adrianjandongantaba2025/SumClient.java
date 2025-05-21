/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.adrianjandongantaba2025;

import grpc.sum.SumRequest;
import grpc.sum.SumResponse;
import grpc.sum.SumServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *
 * @author ajandongan Distributed System TABA 2025
 */
public class SumClient {
  public static void main(String[] args) {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
        .usePlaintext()
        .build();

    SumServiceGrpc.SumServiceBlockingStub stub = SumServiceGrpc.newBlockingStub(channel);

    SumRequest request = SumRequest.newBuilder()
        .addNumbers(7)
        .addNumbers(7)
        .addNumbers(7)
        .addNumbers(7)
        .addNumbers(7)
        .addNumbers(7)
        .addNumbers(7)
        .build();

    SumResponse response = stub.calculateSum(request);
    System.out.println("Sum result: " + response.getResult());

    channel.shutdown();
  }
}

