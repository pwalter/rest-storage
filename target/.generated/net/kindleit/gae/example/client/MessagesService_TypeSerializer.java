package net.kindleit.gae.example.client;

import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.impl.Serializer;

public class MessagesService_TypeSerializer implements Serializer {
  private static final JavaScriptObject methodMap = createMethodMap();
  private static final JavaScriptObject signatureMap = createSignatureMap();
  
  private static native java.util.ArrayList create_com_google_gwt_user_client_rpc_core_java_util_ArrayList_CustomFieldSerializer(SerializationStreamReader streamReader) throws SerializationException /*-{
    return @java.util.ArrayList::new()();
  }-*/;
  
  private static native java.util.HashSet create_com_google_gwt_user_client_rpc_core_java_util_HashSet_CustomFieldSerializer(SerializationStreamReader streamReader) throws SerializationException /*-{
    return @java.util.HashSet::new()();
  }-*/;
  
  private static native java.util.LinkedList create_com_google_gwt_user_client_rpc_core_java_util_LinkedList_CustomFieldSerializer(SerializationStreamReader streamReader) throws SerializationException /*-{
    return @java.util.LinkedList::new()();
  }-*/;
  
  private static native java.util.Vector create_com_google_gwt_user_client_rpc_core_java_util_Vector_CustomFieldSerializer(SerializationStreamReader streamReader) throws SerializationException /*-{
    return @java.util.Vector::new()();
  }-*/;
  
  @SuppressWarnings("restriction")
  private static native JavaScriptObject createMethodMap() /*-{
    return {
    "com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException/3936916533":[
      @com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/rpc/IncompatibleRemoteServiceException;),
      @com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/google/gwt/user/client/rpc/IncompatibleRemoteServiceException;)
    ],
    "java.lang.Boolean/476441737":[
      @com.google.gwt.user.client.rpc.core.java.lang.Boolean_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @com.google.gwt.user.client.rpc.core.java.lang.Boolean_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/Boolean;),
      @com.google.gwt.user.client.rpc.core.java.lang.Boolean_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/lang/Boolean;)
    ],
    "java.lang.Long/4227064769":[
      @com.google.gwt.user.client.rpc.core.java.lang.Long_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @com.google.gwt.user.client.rpc.core.java.lang.Long_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/Long;),
      @com.google.gwt.user.client.rpc.core.java.lang.Long_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/lang/Long;)
    ],
    "java.lang.String/2004016611":[
      @com.google.gwt.user.client.rpc.core.java.lang.String_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @com.google.gwt.user.client.rpc.core.java.lang.String_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/String;),
      @com.google.gwt.user.client.rpc.core.java.lang.String_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/lang/String;)
    ],
    "java.util.ArrayList/3821976829":[
      @net.kindleit.gae.example.client.MessagesService_TypeSerializer::create_com_google_gwt_user_client_rpc_core_java_util_ArrayList_CustomFieldSerializer(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @com.google.gwt.user.client.rpc.core.java.util.ArrayList_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/ArrayList;),
      @com.google.gwt.user.client.rpc.core.java.util.ArrayList_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/ArrayList;)
    ],
    "java.util.Arrays$ArrayList/1243019747":[
      @com.google.gwt.user.client.rpc.core.java.util.Arrays.ArrayList_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @com.google.gwt.user.client.rpc.core.java.util.Arrays.ArrayList_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/List;),
      @com.google.gwt.user.client.rpc.core.java.util.Arrays.ArrayList_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/List;)
    ],
    "java.util.HashSet/1594477813":[
      @net.kindleit.gae.example.client.MessagesService_TypeSerializer::create_com_google_gwt_user_client_rpc_core_java_util_HashSet_CustomFieldSerializer(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @com.google.gwt.user.client.rpc.core.java.util.HashSet_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/HashSet;),
      @com.google.gwt.user.client.rpc.core.java.util.HashSet_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/HashSet;)
    ],
    "java.util.LinkedHashSet/3628722029":[
      @com.google.gwt.user.client.rpc.core.java.util.LinkedHashSet_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @com.google.gwt.user.client.rpc.core.java.util.LinkedHashSet_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/LinkedHashSet;),
      @com.google.gwt.user.client.rpc.core.java.util.LinkedHashSet_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/LinkedHashSet;)
    ],
    "java.util.LinkedList/1060625595":[
      @net.kindleit.gae.example.client.MessagesService_TypeSerializer::create_com_google_gwt_user_client_rpc_core_java_util_LinkedList_CustomFieldSerializer(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @com.google.gwt.user.client.rpc.core.java.util.LinkedList_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/LinkedList;),
      @com.google.gwt.user.client.rpc.core.java.util.LinkedList_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/LinkedList;)
    ],
    "java.util.Stack/1031431137":[
      @com.google.gwt.user.client.rpc.core.java.util.Stack_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @com.google.gwt.user.client.rpc.core.java.util.Stack_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/Stack;),
      @com.google.gwt.user.client.rpc.core.java.util.Stack_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/Stack;)
    ],
    "java.util.TreeMap/1575826026":[
      @com.google.gwt.user.client.rpc.core.java.util.TreeMap_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @com.google.gwt.user.client.rpc.core.java.util.TreeMap_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/TreeMap;),
      @com.google.gwt.user.client.rpc.core.java.util.TreeMap_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/TreeMap;)
    ],
    "java.util.TreeSet/1002270346":[
      @com.google.gwt.user.client.rpc.core.java.util.TreeSet_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @com.google.gwt.user.client.rpc.core.java.util.TreeSet_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/TreeSet;),
      @com.google.gwt.user.client.rpc.core.java.util.TreeSet_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/TreeSet;)
    ],
    "java.util.Vector/3125574444":[
      @net.kindleit.gae.example.client.MessagesService_TypeSerializer::create_com_google_gwt_user_client_rpc_core_java_util_Vector_CustomFieldSerializer(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @com.google.gwt.user.client.rpc.core.java.util.Vector_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/Vector;),
      @com.google.gwt.user.client.rpc.core.java.util.Vector_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/Vector;)
    ],
    "net.kindleit.gae.example.model.Message/2076606070":[
      @net.kindleit.gae.example.model.Message_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @net.kindleit.gae.example.model.Message_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lnet/kindleit/gae/example/model/Message;),
      @net.kindleit.gae.example.model.Message_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lnet/kindleit/gae/example/model/Message;)
    ],
    "[Lnet.kindleit.gae.example.model.Message;/1194613633":[
      @net.kindleit.gae.example.model.Message_Array_Rank_1_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
      @net.kindleit.gae.example.model.Message_Array_Rank_1_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;[Lnet/kindleit/gae/example/model/Message;),
      @net.kindleit.gae.example.model.Message_Array_Rank_1_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;[Lnet/kindleit/gae/example/model/Message;)
    ]
    };
  }-*/;
  
  private static native JavaScriptObject createSignatureMap() /*-{
    return {
    "com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException":"3936916533",
    "java.lang.Boolean":"476441737",
    "java.lang.Long":"4227064769",
    "java.lang.String":"2004016611",
    "java.util.ArrayList":"3821976829",
    "java.util.Arrays$ArrayList":"1243019747",
    "java.util.HashSet":"1594477813",
    "java.util.LinkedHashSet":"3628722029",
    "java.util.LinkedList":"1060625595",
    "java.util.Stack":"1031431137",
    "java.util.TreeMap":"1575826026",
    "java.util.TreeSet":"1002270346",
    "java.util.Vector":"3125574444",
    "net.kindleit.gae.example.model.Message":"2076606070",
    "[Lnet.kindleit.gae.example.model.Message;":"1194613633"
    };
  }-*/;
  
  private static void raiseSerializationException(String msg) throws SerializationException {
    throw new SerializationException(msg);
  }
  
  public native void deserialize(SerializationStreamReader streamReader, Object instance, String typeSignature) throws SerializationException /*-{
    var methodTable = @net.kindleit.gae.example.client.MessagesService_TypeSerializer::methodMap[typeSignature];
    if (!methodTable) {
      @net.kindleit.gae.example.client.MessagesService_TypeSerializer::raiseSerializationException(Ljava/lang/String;)(typeSignature);
    }
    methodTable[1](streamReader, instance);
  }-*/;
  
  public native String getSerializationSignature(String typeName) /*-{
    return @net.kindleit.gae.example.client.MessagesService_TypeSerializer::signatureMap[typeName];
  }-*/;
  
  public native Object instantiate(SerializationStreamReader streamReader, String typeSignature) throws SerializationException /*-{
    var methodTable = @net.kindleit.gae.example.client.MessagesService_TypeSerializer::methodMap[typeSignature];
    if (!methodTable) {
      @net.kindleit.gae.example.client.MessagesService_TypeSerializer::raiseSerializationException(Ljava/lang/String;)(typeSignature);
    }
    return methodTable[0](streamReader);
  }-*/;
  
  public native void serialize(SerializationStreamWriter streamWriter, Object instance, String typeSignature) throws SerializationException /*-{
    var methodTable = @net.kindleit.gae.example.client.MessagesService_TypeSerializer::methodMap[typeSignature];
    if (!methodTable) {
      @net.kindleit.gae.example.client.MessagesService_TypeSerializer::raiseSerializationException(Ljava/lang/String;)(typeSignature);
    }
    methodTable[2](streamWriter, instance);
  }-*/;
  
}
