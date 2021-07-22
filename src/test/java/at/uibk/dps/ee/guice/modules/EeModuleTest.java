package at.uibk.dps.ee.guice.modules;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.opt4j.core.IndividualStateListener;
import org.opt4j.core.optimizer.OptimizerIterationListener;
import org.opt4j.core.optimizer.OptimizerStateListener;

/**
 * Tests that the Opt4J methods result in exceptions.
 * 
 * @author Fedor Smirnov
 *
 */
public class EeModuleTest {

  protected class EeModuleMock extends EeModule {
    @Override
    protected void config() {}
  }

  @Test
  public void testIndiListener() {
    assertThrows(IllegalStateException.class, () -> {
      EeModuleMock tested = new EeModuleMock();
      tested.addIndividualStateListener(IndividualStateListener.class);
    });
  }

  @Test
  public void testIterationListener() {
    assertThrows(IllegalStateException.class, () -> {
      EeModuleMock tested = new EeModuleMock();
      tested.addOptimizerIterationListener(OptimizerIterationListener.class);
    });
  }

  @Test
  public void testOptimizerStateListener() {
    assertThrows(IllegalStateException.class, () -> {
      EeModuleMock tested = new EeModuleMock();
      tested.addOptimizerStateListener(OptimizerStateListener.class);
    });
  }
}
