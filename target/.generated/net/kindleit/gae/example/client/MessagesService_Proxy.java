package net.kindleit.gae.example.client;

import com.google.gwt.user.client.rpc.impl.RemoteServiceProxy;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.impl.RequestCallbackAdapter.ResponseReader;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.impl.ClientSerializationStreamWriter;

public class MessagesService_Proxy extends RemoteServiceProxy implements net.kindleit.gae.example.client.MessagesServiceAsync {
  private static final String REMOTE_SERVICE_INTERFACE_NAME = "net.kindleit.gae.example.client.MessagesService";
  private static final String SERIALIZATION_POLICY ="3A790F140E833658CF3F6C057094B330";
  private static final net.kindleit.gae.example.client.MessagesService_TypeSerializer SERIALIZER = new net.kindleit.gae.example.client.MessagesService_TypeSerializer();
  
  public MessagesService_Proxy() {
    super(GWT.getModuleBaseURL(),
      "messages", 
      SERIALIZATION_POLICY, 
      SERIALIZER);
  }
  
  public void create(net.kindleit.gae.example.model.Message message, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("MessagesService_Proxy.create", getRequestId(), "begin"));
    ClientSerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
    try {
      streamWriter.writeString("create");
      streamWriter.writeInt(1);
      streamWriter.writeString("net.kindleit.gae.example.model.Message");
      streamWriter.writeObject(message);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("MessagesService_Proxy.create", getRequestId(), "requestSerialized"));
      doInvoke(ResponseReader.VOID, "MessagesService_Proxy.create", getRequestId(), payload, callback);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void deleteById(java.lang.Long id, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("MessagesService_Proxy.deleteById", getRequestId(), "begin"));
    ClientSerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
    try {
      streamWriter.writeString("deleteById");
      streamWriter.writeInt(1);
      streamWriter.writeString("java.lang.Long");
      streamWriter.writeObject(id);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("MessagesService_Proxy.deleteById", getRequestId(), "requestSerialized"));
      doInvoke(ResponseReader.VOID, "MessagesService_Proxy.deleteById", getRequestId(), payload, callback);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void getAll(com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("MessagesService_Proxy.getAll", getRequestId(), "begin"));
    ClientSerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
    streamWriter.writeString("getAll");
    streamWriter.writeInt(0);
    String payload = streamWriter.toString();
    toss = isStatsAvailable() && stats(timeStat("MessagesService_Proxy.getAll", getRequestId(), "requestSerialized"));
    doInvoke(ResponseReader.OBJECT, "MessagesService_Proxy.getAll", getRequestId(), payload, callback);
  }
}
