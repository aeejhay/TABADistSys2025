# Question 2 – Sum RPC using gRPC (Java)

This mini-project implements a simple gRPC service in Java where the client sends an array of integers and the server returns the sum.
It includes both **synchronous** and **asynchronous** client versions to demonstrate different RPC interaction styles.

## 📦 Project Structure

```
├── proto/
│   └── sum.proto
├── src/grpc/sum/
│   ├── SumServer.java
│   ├── SumClient.java (Synchronous)
│   └── SumClientAsync.java (Asynchronous)
```

---

## 📄 Proto Definition

**File:** `sum.proto`

```proto
syntax = "proto3";

option java_package = "grpc.sum";
option java_multiple_files = true;

service SumService {
  rpc CalculateSum (SumRequest) returns (SumResponse);
}

message SumRequest {
  repeated int32 numbers = 1;
}

message SumResponse {
  int32 result = 1;
}
```

---

## 🚀 How to Run

1. Compile the proto file using `protoc`
2. Start the server:

   ```bash
   java grpc.sum.SumServer
   ```
3. Run the client:

   ```bash
   java grpc.sum.SumClient        # for synchronous call
   java grpc.sum.SumClientAsync   # for async version
   ```

---

## 🔁 RPC Types Demonstrated

* `SumClient.java`: Uses `SumServiceBlockingStub` – **Synchronous (blocking)**
* `SumClientAsync.java`: Uses `SumServiceStub` with `StreamObserver` – **Asynchronous (non-blocking)**

---

## 💬 Notes

* This example is based on Question 2 from the Distributed Systems Terminal Assignment (TABA).
* The project shows how `StreamObserver` works in async mode, and how blocking stubs behave in sync mode.
* The proto and Java classes are all kept minimal and easy to follow.

---

## 📚 Reference

Nic Lughadha, C. (2025). *Week 3 – Introduction to gRPC*. Distributed Systems \[PowerPoint presentation]. Dublin: Higher Diploma in Science in Computing.

---

Feel free to reuse or expand this as a template for other RPC-based examples.
