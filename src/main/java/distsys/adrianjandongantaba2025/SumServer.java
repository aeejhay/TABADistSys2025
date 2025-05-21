/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package distsys.adrianjandongantaba2025;

import grpc.sum.SumRequest;
import grpc.sum.SumResponse;
import grpc.sum.SumServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 *
 * @author ajand
 */
public class SumServer {
  public static void main(String[] args) throws Exception {
    Server server = ServerBuilder.forPort(50052)
        .addService(new SumServiceImpl())
        .build();
    server.start();
    System.out.println("SumServer is running on port 50052");
    server.awaitTermination();
  }

  static class SumServiceImpl extends SumServiceGrpc.SumServiceImplBase {
    @Override
    public void calculateSum(SumRequest request, StreamObserver<SumResponse> responseObserver) {
      int sum = 0;
      for (int num : request.getNumbersList()) {
        sum += num;
      }
      SumResponse response = SumResponse.newBuilder().setResult(sum).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }
}
