package net.kindleit.gae.example.model;

public class Message_FieldSerializer {
  private static native java.lang.Long getId(net.kindleit.gae.example.model.Message instance) /*-{
    return instance.@net.kindleit.gae.example.model.Message::id;
  }-*/;
  
  private static native void  setId(net.kindleit.gae.example.model.Message instance, java.lang.Long value) /*-{
    instance.@net.kindleit.gae.example.model.Message::id = value;
  }-*/;
  
  private static native java.lang.String getText(net.kindleit.gae.example.model.Message instance) /*-{
    return instance.@net.kindleit.gae.example.model.Message::text;
  }-*/;
  
  private static native void  setText(net.kindleit.gae.example.model.Message instance, java.lang.String value) /*-{
    instance.@net.kindleit.gae.example.model.Message::text = value;
  }-*/;
  
  public static void deserialize(com.google.gwt.user.client.rpc.SerializationStreamReader streamReader, net.kindleit.gae.example.model.Message instance) throws com.google.gwt.user.client.rpc.SerializationException{
    setId(instance, (java.lang.Long) streamReader.readObject());
    setText(instance, streamReader.readString());
    
  }
  
  public static native net.kindleit.gae.example.model.Message instantiate(com.google.gwt.user.client.rpc.SerializationStreamReader streamReader) throws com.google.gwt.user.client.rpc.SerializationException/*-{
    return @net.kindleit.gae.example.model.Message::new()();
  }-*/;
  
  public static void serialize(com.google.gwt.user.client.rpc.SerializationStreamWriter streamWriter, net.kindleit.gae.example.model.Message instance) throws com.google.gwt.user.client.rpc.SerializationException {
    streamWriter.writeObject(getId(instance));
    streamWriter.writeString(getText(instance));
    
  }
  
}
