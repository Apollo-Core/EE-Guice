package at.uibk.dps.ee.guice.container;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonObject;

class ContainerManagerNoneTest {

  ContainerManagerNone tested;
  
  String imageName;
  JsonObject input;
  
  @Test
  void testExceptions() {
    assertThrows(IllegalStateException.class, () ->{
      tested.runImage(imageName, input);
    });
    
    assertThrows(IllegalStateException.class, () ->{
      tested.closeImage(imageName);
    });
    
    assertThrows(IllegalStateException.class, () ->{
      tested.initImage(imageName);
    });
    
  }

  
  @BeforeEach
  void setup() {
    tested = new ContainerManagerNone();
    imageName = "image";
    input = new JsonObject();
  }
  
}
